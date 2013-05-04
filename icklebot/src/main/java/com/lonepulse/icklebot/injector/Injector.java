package com.lonepulse.icklebot.injector;

/*
 * #%L
 * IckleBot
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;

import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.event.resolver.EventCategory;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.injector.resolver.InjectionResolver;
import com.lonepulse.icklebot.injector.resolver.InjectionResolvers;

/**
 * <p>This is the common contract which all injectors must implement.</p>
 * 
 * @version 1.2.0 - update {@link Injector.Configuration} to support {@link Fragment}s.  
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface Injector {
	
	
	/**
	 * <p>Stores information about the injection process; such as the 
	 * context which has requested injection and the target {@link Field}s 
	 * in this context grouped into their categories by {@link InjectionCategory}.</p>
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Configuration {
		
		/**
		 * <p>A cache of all {@link Injector.Configuration}s processed. Configurations 
		 * are keyed by the {@link Class} of their context implementations.</p> 
		 * 
		 * @version 1.1.0
		 */
		private enum CACHE {
			
			/**
			 * <p>The single instance of this cache.</p>
			 * 
			 * @since 1.1.0
			 */
			INSTANCE;
			
			
			/**
			 * <p>Stores processed {@link Configuration}s, keyed by their context 
			 * {@link Class}. This map is <i>weak</i>, i.e. cached {@link Configuration}s 
			 * may be silently removed if they are rarely reused.</p>
			 * 
			 * @since 1.2.0
			 */
			private Map<Class<?>, Configuration>  cache
				= new WeakHashMap<Class<?>, Injector.Configuration>();
			
			/**
			 * <p>A delegate for {@link Map#put(Object, Object)} which wraps the 
			 * {@link #cache}.</p>
			 * 
			 * @param key
			 * 			the requesting instance of context
			 * <br><br>
			 * @param value
			 * 			the {@link Configuration} for the context extension
			 * <br><br>
			 * @return the previous {@link Configuration} keyed by this context's 
			 * 		   {@link Class}, <b>if any</b>
			 * <br><br>
			 * @since 1.2.0
			 */
			public Configuration put(Object context, Configuration value) {
				
				return cache.put(context.getClass(), value);
			}
			
			/**
			 * <p>A delegate for {@link Map#get(Object)} which wraps the {@link #cache}. 
			 * It takes the new instance of {@link Context} and updates the 
			 * {@link Configuration#context} property as well.</p>
			 * 
			 * @param key
			 * 			the requesting instance of context
			 * <br><br>
			 * @return the {@link Configuration} keyed by by this context's 
			 * 		   {@link Class}; else {@code null} if not found
			 * <br><br>
			 * @since 1.2.0
			 */
			public Configuration get(Object context) {
				
				Configuration configuration = cache.get(context.getClass());
				
				if(configuration != null)
					configuration.setContext(context);
				
				return configuration;
			}
		}
		
		/**
		 * <p>The <i>mode</i> of injection identified by 
		 * {@link InjectionMode}.</p>
		 * 
		 * @since 1.1.0
		 */
		private InjectionMode injectionMode;
		
		
		/**
		 * <p>The context which has requested dependency injection.</p>
		 * 
		 * @since 1.2.0
		 */
		private Object context;
		
		
		/**
		 * <p>The target {@link Field}s in the {@link #context} grouped into their 
		 * categories by {@link InjectionCategory}.</p>
		 * 
		 * @since 1.1.0
		 */
		private Map<InjectionCategory, Set<Field>> injectionTargets;

		
		/**
		 * <p>Creates a <b>new</b> instance of {@link Injector.Configuration} 
		 * using the passed context.</p>
		 * 
		 * <p>This is to be used in an <i>instantiated context</i>.</p>
		 * 
		 * @param injectionContext
		 * 			the {@link Context} which has requested dependency injection
		 * <br><br>
		 * @return a new instance of {@link Injector.Configuration}
		 * <br><br>
		 * @throws InjectionException
		 * 			if the supplied context is {@code null}
		 * <br><br>
		 * @throws IllegalContextException
		 * 			if the given context is not of type {@link Activity} or {@link Fragment}
		 * <br><br>
		 * @since 1.1.0
		 */
		public static Configuration newInstance(Object injectionContext) {
			
			if(injectionContext == null)
				throw new InjectionException(new IllegalArgumentException("An injection context must be supplied."));

			if(!Activity.class.isAssignableFrom(injectionContext.getClass())
				&& Fragment.class.isAssignableFrom(injectionContext.getClass())) { 
				
				Set<Class<?>> applicableContexts = new HashSet<Class<?>>();
				applicableContexts.add(Activity.class);
				applicableContexts.add(Fragment.class);
				
				throw new IllegalContextException(injectionContext, applicableContexts);
			}
			
			
			Configuration config = new Configuration();
			
			config.setContext(injectionContext);
		
			if(injectionContext.getClass().isAnnotationPresent(InjectAll.class))
				config.setInjectionMode(InjectionMode.IMPLICIT);
			
			else 
				config.setInjectionMode(InjectionMode.EXPLICIT);
				
		
			Field[] fields = injectionContext.getClass().getDeclaredFields();

			InjectionResolver injectionResolver = null;
			
			switch (config.getInjectionMode()) {
			
				case EXPLICIT:
					injectionResolver = InjectionResolvers.EXPLICIT;
					break;
					
				case IMPLICIT: default:  
					injectionResolver = InjectionResolvers.IMPLICIT;
			}
			
			for (Field field : fields) 
				config.putInjectionTarget(injectionResolver.resolve(injectionContext, field), field);
			
			return config;
		}
		
		/**
		 * <p>Retrieves the <b>cached</b> instance of {@link Injector.Configuration} 
		 * using the passed context. If cached instance is not found, a new instance 
		 * is created, via {@link #newInstance(Object)}, and cached.</p>
		 * 
		 * @param injectionContext
		 * 			the context which has requested dependency injection
		 * <br><br>
		 * @return the <b>cached</b> instance of {@link Injector.Configuration}
		 * <br><br>
		 * @since 1.1.0
		 */
		public static Configuration getInstance(Object injectionContext) {
		
			Configuration config = CACHE.INSTANCE.get(injectionContext);
			
			if(config != null) {

				config.setContext(injectionContext);
			}
			else {
				
				config = newInstance(injectionContext);
				CACHE.INSTANCE.put(injectionContext, config);
			}
			
			return config;
		}
		
		/**
		 * <p>Constructor visibility restricted to prevent instantiation. 
		 * Please use the factory method {@link #newInstance(Object)}.</p>
		 * 
		 * <p>Initializes {@link #injectionTargets} to an empty {@link Map}.</p>
		 * 
		 * @since 1.1.0
		 */
		private Configuration() {
			
			this.injectionTargets = new HashMap<InjectionCategory, Set<Field>>(); 
		}
		
		/**
		 * <p>Accessor for {@link #injectionMode}.</p>
		 * 
		 * @return {@link #injectionMode}
		 * <br><br>
		 * @since 1.1.0
		 */		
		public InjectionMode getInjectionMode() {
			
			return injectionMode;
		}
		
		/**
		 * <p>Mutator for {@link #injectionMode}.</p>
		 * 
		 * @param injectionMode
		 * 			the {@link InjectionMode} to populate 
		 * 			{@link #injectionMode} 
		 * <br><br>
		 * @since 1.1.0
		 */
		private void setInjectionMode(InjectionMode injectionMode) {
			
			this.injectionMode = injectionMode;
		}

		/**
		 * <p>Accessor for {@link #context}.</p>
		 * 
		 * @return {@link #context}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Object getContext() {
			
			return context;
		}

		/**
		 * <p>Mutator for {@link #context}.</p>
		 * 
		 * @param activity
		 * 			the context to populate {@link #context} 
		 * <br><br>
		 * @since 1.1.0
		 */
		private void setContext(Object context) {
			
			this.context = context;
		}

		/**
		 * <p>Accessor for {@link #injectionTargets}.</p>
		 * 
		 * @return {@link #injectionTargets}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Map<InjectionCategory, Set<Field>> getInjectionTargets() {
			
			return injectionTargets;
		}

		/**
		 * <p>Mutator for {@link #injectionTargets}. Takes a {@link Field} 
		 * along with its associated {@link EventCategory} and puts it 
		 * to the appropriate {@link Set} in {@link #injectionTargets}.</p>
		 * 
		 * @param injectionCategory
		 * 			the {@link EventCategory} to which the 
		 * 			{@link Field} belongs to	
		 * <br><br>
		 * @param field
		 * 			the {@link Field} to be categorized into an 
		 * 			{@link EventCategory}
		 * <br><br>
		 * @since 1.1.0
		 */
		private void putInjectionTarget(InjectionCategory injectionCategory, Field field) {
			
			Set<Field> fields = injectionTargets.get(injectionCategory);
			
			if(fields == null) {
				
				fields = new HashSet<Field>();
				injectionTargets.put(injectionCategory, fields);
			}
			
			fields.add(field);
		}
		
		/**
		 * <p>Takes an {@link EventCategory} and retrieves 
		 * the {@link Set} of {@link Field}s under that category 
		 * as mapped in {@link #injectionTargets}.</p> 
		 * 
		 * @param injectionCategory
		 * 			the fields are to be retrieved for this 
		 * 			{@link EventCategory}			
		 * <br><br>
		 * @return the {@link Set} of {@link Field}s  under 
		 * 		   the category, else an empty {@link Set}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Set<Field> getInjectionTargets(InjectionCategory injectionCategory) {
			
			Set<Field> targets = injectionTargets.get(injectionCategory);
			
			return (targets == null)? 
						new HashSet<Field>() :Collections.unmodifiableSet(targets);
		}
	 }

	/**
	 * <p>Specifies the contract for performing injection related to a 
	 * particular {@link InjectionCategory} over the given set of {@link Field}s.
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static interface InjectionStrategy {
		
		/**
		 * <p>Performs injection over the given set of {@link Field}s in 
		 * the target {@link Context}.
		 * 
		 * @param config
		 * 			the {@link Injector.Configuration} associated with the 
		 * 			injection operation
		 * 
		 * @return the value which is to be injected
		 * 
		 * @since 1.1.0
		 */
		public abstract void run(Injector.Configuration config);
	}
	
	
	/**
	 * <p>Takes an {@link Injector.Configuration} and injects the <i>resources</i> 
	 * which this injector is responsible for.</p>
	 * 
	 * @param config
	 * 			the {@link Injector.Configuration} which for this injector
	 * <br><br>
	 * @throws InjectionException
	 * 			if the injection operation failed
	 * <br><br>
	 * @since 1.1.0
	 */
	public abstract void inject(final Injector.Configuration config) 
	throws InjectionException;
}
