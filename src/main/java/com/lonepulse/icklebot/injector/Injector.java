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

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotations.InjectAll;
import com.lonepulse.icklebot.resolver.InjectionCategory;
import com.lonepulse.icklebot.resolver.InjectionResolver;
import com.lonepulse.icklebot.resolver.InjectionResolvers;

/**
 * <p>This is the common contract which all injectors must implement.</p>
 * 
 * @version 1.0.0 
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface Injector {
	
	/**
	 * <p>Stores information about the injection process; such as the 
	 * {@link IckleActivity} which has requested injection and the 
	 * target {@link Field}s in this activity grouped into their categories
	 * by {@link InjectionCategory}.</p>
	 * 
	 * @version 1.0.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Configuration {
		
		/**
		 * <p>A cache of all {@link Injector.Configuration}s processed. Configurations 
		 * are keyed by the {@link Class} of their {@link IckleActivity} implementations.</p> 
		 * 
		 * @version 1.0.0
		 */
		private enum CACHE {
			
			/**
			 * <p>The single instance of this cache.</p>
			 * 
			 * @since 1.0.0
			 */
			INSTANCE;
			
			
			/**
			 * <p>Stores processed {@link Configuration}s, keyed by their {@link IckleActivity} 
			 * {@link Class}. This map is <i>weak</i>, i.e. cached {@link Configuration}s may be 
			 * silently removed if they are rarely reused.</p>
			 * 
			 * @since 1.0.0
			 */
			private Map<Class<? extends IckleActivity>, Configuration>  cache
				= new WeakHashMap<Class<? extends IckleActivity>, Injector.Configuration>();
			
			/**
			 * <p>A delegate for {@link Map#put(Object, Object)} which wraps the 
			 * {@link #cache}.</p>
			 * 
			 * @param key
			 * 			the requesting instance of {@link IckleActivity}
			 * <br><br>
			 * @param value
			 * 			the {@link Configuration} for the {@link IckleActivity} 
			 * 			extension
			 * <br><br>
			 * @return the previous {@link Configuration} keyed by this 
			 * 		   {@link IckleActivity} {@link Class}, <b>if any</b>
			 * <br><br>
			 * @since 1.0.0
			 */
			public Configuration put(IckleActivity key, Configuration value) {
				
				return cache.put(key.getClass(), value);
			}
			
			/**
			 * <p>A delegate for {@link Map#get(Object)} which wraps the {@link #cache}. 
			 * It takes the new instance of {@link IckleActivity} and updates the 
			 * {@link Configuration#injectionActivity} property as well.</p>
			 * 
			 * @param key
			 * 			the requesting instance of {@link IckleActivity}
			 * <br><br>
			 * @return the {@link Configuration} keyed by by this {@link IckleActivity} 
			 * 		   {@link Class}; else {@code null} if not found
			 * <br><br>
			 * @since 1.0.0
			 */
			public Configuration get(IckleActivity key) {
				
				Configuration configuration = cache.get(key.getClass());
				
				if(configuration != null)
					configuration.setInjectionActivity(key);
				
				return configuration;
			}
		}
		
		
		/**
		 * <p>The <i>mode</i> of injection identified by 
		 * {@link InjectionMode}.</p>
		 * 
		 * @since 1.0.0
		 */
		private InjectionMode injectionMode;
		
		
		/**
		 * <p>The {@link IckleActivity} which has requested 
		 * dependency injection.</p>
		 * 
		 * @since 1.0.0
		 */
		private IckleActivity injectionActivity;
		
		
		/**
		 * <p>The target {@link Field}s in the {@link #injectionActivity} 
		 * activity grouped into their categories by {@link InjectionCategory}.</p>
		 * 
		 * @since 1.0.0
		 */
		private Map<InjectionCategory, Set<Field>> injectionTargets;

		
		/**
		 * <p>Creates a <b>new</b> instance of {@link Injector.Configuration} 
		 * using the passed {@link IckleActivity}.</p>
		 * 
		 * <p>This is to be used in an <i>instantiated context</i>.</p>
		 * 
		 * @param injectionActivity
		 * 			the {@link IckleActivity} which has requested 
		 * 			dependency injection
		 * <br><br>
		 * @return a new instance of {@link Injector.Configuration}
		 * <br><br>
		 * @since 1.0.0
		 */
		public static Configuration newInstance(IckleActivity injectionActivity) {
			
			Configuration config = new Configuration();
			
			config.setInjectionActivity(injectionActivity);
		
			if(injectionActivity.getClass().isAnnotationPresent(InjectAll.class))
				config.setInjectionMode(InjectionMode.IMPLICIT);
			
			else 
				config.setInjectionMode(InjectionMode.EXPLICIT);
				
		
			Field[] fields = injectionActivity.getClass().getDeclaredFields();

			InjectionResolver injectionResolver = null;
			
			switch (config.getInjectionMode()) {
			
				case EXPLICIT:
					injectionResolver = InjectionResolvers.EXPLICIT;
					break;
					
				case IMPLICIT: default:  
					injectionResolver = InjectionResolvers.IMPLICIT;
			}
			
			for (Field field : fields) 
				config.putInjectionTarget(injectionResolver.resolve(field), field);
			
			return config;
		}
		
		/**
		 * <p>Retrieves the <b>cached</b> instance of {@link Injector.Configuration} 
		 * using the passed {@link IckleActivity}. If cached instance is not 
		 * found, a new instance is created, via {@link #newInstance(IckleActivity)}, 
		 * and cached.</p>
		 * 
		 * @param injectionActivity
		 * 			the {@link IckleActivity} which has requested 
		 * 			dependency injection
		 * <br><br>
		 * @return the <b>cached</b> instance of {@link Injector.Configuration}
		 * <br><br>
		 * @since 1.0.0
		 */
		public static Configuration getInstance(IckleActivity injectionActivity) {
		
			Configuration config = CACHE.INSTANCE.get(injectionActivity);
			
			if(config != null) {

				config.setInjectionActivity(injectionActivity);
			}
			else {
				
				config = newInstance(injectionActivity);
				CACHE.INSTANCE.put(injectionActivity, config);
			}
			
			return config;
		}
		
		/**
		 * <p>Constructor visibility restricted to prevent instantiation. 
		 * Please use the factory method {@link #newInstance(IckleActivity)}.</p>
		 * 
		 * <p>Initializes {@link #injectionTargets} to an empty {@link Map}.</p>
		 * 
		 * @since 1.0.0
		 */
		private Configuration() {
			
			this.injectionTargets = new HashMap<InjectionCategory, Set<Field>>(); 
		}
		
		/**
		 * <p>Accessor for {@link #injectionMode}.</p>
		 * 
		 * @return {@link #injectionMode}
		 * <br><br>
		 * @since 1.0.0
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
		 * @since 1.0.0
		 */
		private void setInjectionMode(InjectionMode injectionMode) {
			
			this.injectionMode = injectionMode;
		}

		/**
		 * <p>Accessor for {@link #injectionActivity}.</p>
		 * 
		 * @return {@link #injectionActivity}
		 * <br><br>
		 * @since 1.0.0
		 */
		public IckleActivity getInjectionActivity() {
			
			return injectionActivity;
		}

		/**
		 * <p>Mutator for {@link #injectionActivity}.</p>
		 * 
		 * @param injectionActivity
		 * 			the {@link IckleActivity} to populate 
		 * 			{@link #injectionActivity} 
		 * <br><br>
		 * @since 1.0.0
		 */
		private void setInjectionActivity(IckleActivity injectionActivity) {
			
			this.injectionActivity = injectionActivity;
		}

		/**
		 * <p>Accessor for {@link #injectionTargets}.</p>
		 * 
		 * @return {@link #injectionTargets}
		 * <br><br>
		 * @since 1.0.0
		 */
		public Map<InjectionCategory, Set<Field>> getInjectionTargets() {
			
			return injectionTargets;
		}

		/**
		 * <p>Mutator for {@link #injectionTargets}. Takes a {@link Field} 
		 * along with its associated {@link InjectionCategory} and puts it 
		 * to the appropriate {@link Set} in {@link #injectionTargets}.</p>
		 * 
		 * @param injectionCategory
		 * 			the {@link InjectionCategory} to which the 
		 * 			{@link Field} belongs to	
		 * <br><br>
		 * @param field
		 * 			the {@link Field} to be categorized into an 
		 * 			{@link InjectionCategory}
		 * <br><br>
		 * @since 1.0.0
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
		 * <p>Takes an {@link InjectionCategory} and retrieves 
		 * the {@link Set} of {@link Field}s under that category 
		 * as mapped in {@link #injectionTargets}.</p> 
		 * 
		 * @param injectionCategory
		 * 			the fields are to be retrieved for this 
		 * 			{@link InjectionCategory}			
		 * <br><br>
		 * @return the {@link Set} of {@link Field}s  under 
		 * 		   the category, else an empty {@link Set}
		 * <br><br>
		 * @since 1.0.0
		 */
		public Set<Field> getInjectionTargets(InjectionCategory injectionCategory) {
			
			Set<Field> targets = injectionTargets.get(injectionCategory);
			
			return (targets == null)? 
						new HashSet<Field>() :Collections.unmodifiableSet(targets);
		}
	 }
	
	/**
	 * <p>Takes an {@link Injector.Configuration} and injects the <i>resources</i> 
	 * which this injector is responsible for.</p>
	 * 
	 * @param config
	 * 			the {@link Injector.Configuration} which for this injector
	 * <br><br>
	 * @since 1.0.0
	 */
	public abstract void inject(final Injector.Configuration config);
}
