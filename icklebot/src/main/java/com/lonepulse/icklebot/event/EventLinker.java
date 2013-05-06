package com.lonepulse.icklebot.event;

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
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.Fragment;

import com.lonepulse.icklebot.event.resolver.EventCategory;
import com.lonepulse.icklebot.event.resolver.EventResolvers;
import com.lonepulse.icklebot.injector.IllegalContextException;
import com.lonepulse.icklebot.injector.InjectionException;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>This is the common contract which all listener linkers must implement.</p>
 * 
 * @version 1.2.1 
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface EventLinker {
	
	/**
	 * <p>Stores information about the event listener linking process; such 
	 * as the {@link activity} which has linking event listeners and the 
	 * target {@link Method}s in this activity grouped into their listener 
	 * categories by {@link EventCategory}.</p>
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Configuration {
		
		/**
		 * <p>A cache of all {@link EventLinker.Configuration}s processed. Configurations 
		 * are keyed by the {@link Class} of their {@link activity} implementations.</p> 
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
			 * <p>Stores processed {@link Configuration}s, keyed by their context {@link Class}.
			 * 
			 * @since 1.1.0
			 */
			private Map<Class<?>, Configuration>  cache
				= new HashMap<Class<?>, EventLinker.Configuration>();
			
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
			 * @return the previous {@link Configuration} keyed by this 
			 * 		   {@link activity} {@link Class}, <b>if any</b>
			 * <br><br>
			 * @since 1.1.0
			 */
			public Configuration put(Object key, Configuration value) {
				
				return cache.put(key.getClass(), value);
			}
			
			/**
			 * <p>A delegate for {@link Map#get(Object)} which wraps the {@link #cache}.
			 * 
			 * @param key
			 * 			the requesting instance of context
			 * <br><br>
			 * @return the {@link Configuration} keyed by by this context
			 * 		   {@link Class}; else {@code null} if not found
			 * <br><br>
			 * @since 1.1.0
			 */
			public Configuration get(Object key) {
				
				return cache.get(key.getClass());
			}
		}
		

		/**
		 * <p>The context which has requested listener linking.</p>
		 * 
		 * @since 1.1.0
		 */
		private Object context;
		
		
		/**
		 * <p>The target {@link Method}s in the {@link #context} 
		 * activity grouped into their categories by {@link EventCategory}.</p>
		 * 
		 * @since 1.1.0
		 */
		private final Map<EventCategory, Set<Method>> listenerTargets;

		
		/**
		 * <p>Creates a <b>new</b> instance of {@link EventLinker.Configuration} 
		 * using the passed context.</p>
		 * 
		 * <p>This is to be used in an <i>instantiated context</i>.</p>
		 * 
		 * @param context
		 * 			the context which has requested event listener linking
		 * <br><br>
		 * @return a new instance of {@link EventLinker.Configuration}
		 * <br><br>
		 * @since 1.1.0
		 */
		public static Configuration newInstance(Object context) {
			
			if(context == null)
				throw new InjectionException(new IllegalArgumentException("A context must be supplied."));
			
			if(!ContextUtils.isActivity(context)
				&& !ContextUtils.isFragment(context)
				&& !ContextUtils.isSupportFragment(context)) { 
					
				Set<Class<?>> applicableContexts = new HashSet<Class<?>>();
				applicableContexts.add(Activity.class);
				applicableContexts.add(Fragment.class);
				applicableContexts.add(android.support.v4.app.Fragment.class);
					
				throw new IllegalContextException(context, applicableContexts);
			}
			
			return new Configuration(context);
		}
		
		/**
		 * <p>Retrieves the <b>cached</b> instance of {@link EventLinker.Configuration} 
		 * using the passed context. If cached instance is not found, a new instance is 
		 * created, via {@link #newInstance(Object)}, and cached.</p>
		 * 
		 * @param activity
		 * 			the {@link activity} which has requested event 
		 * 			listener linking
		 * <br><br>
		 * @return the <b>cached</b> instance of {@link EventLinker.Configuration}
		 * <br><br>
		 * @since 1.1.0
		 */
		public static Configuration getInstance(Object context) {
		
			if(context == null)
				throw new InjectionException(new IllegalArgumentException("A context must be supplied."));
			
			Configuration config = CACHE.INSTANCE.get(context);
			
			if(config != null) {

				config.updateContext(context);
			}
			else {
				
				config = newInstance(context);
				CACHE.INSTANCE.put(context, config);
			}
			
			return config;
		}
		
		/**
		 * <p>Constructor visibility restricted to prevent instantiation. 
		 * Please use the factory method {@link #newInstance(Object)}.</p>
		 * 
		 * <p>Initializes {@link #listenerTargets} to an empty {@link Map}.</p>
		 * 
		 * @since 1.1.0
		 */
		private Configuration(Object context) {
			
			this.context = context;
			this.listenerTargets = new HashMap<EventCategory, Set<Method>>();
			
			for (EventCategory eventCategory : EventCategory.values()) 
				this.listenerTargets.put(eventCategory, new HashSet<Method>());
			
			Method[] methods = context.getClass().getDeclaredMethods();
			
			for (Method method : methods) {
			
				Set<EventCategory> listenerCategories = EventResolvers.BASIC.resolve(method);
				
				for (EventCategory listenerCategory : listenerCategories)
					this.listenerTargets.get(listenerCategory).add(method);
			}
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
		 * <p>Updates the context for this {@link EventLinker.Configuration}.
		 *
		 * @param context
		 * 			the context to update
		 * 
		 * @since 1.2.1
		 */
		private void updateContext(Object context) {
			
			this.context = context;
		}

		/**
		 * <p>Accessor for {@link #listenerTargets}.</p>
		 * 
		 * @return {@link #listenerTargets}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Map<EventCategory, Set<Method>> getListenerTargets() {
			
			return Collections.unmodifiableMap(listenerTargets);
		}
		
		/**
		 * <p>Takes an {@link EventCategory} and retrieves the {@link Set} of 
		 * {@link Field}s under that category as mapped in {@link #listenerTargets}. 
		 * 
		 * @param listenerCategory
		 * 			the fields are to be retrieved for this {@link EventCategory}			
		 * <br><br>
		 * @return the {@link Set} of {@link Field}s  under the category, else an empty {@link Set}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Set<Method> getListenerTargets(EventCategory listenerCategory) {
			
			return Collections.unmodifiableSet(listenerTargets.get(listenerCategory));
		}
	 }
	
	/**
	 * <p>Takes an {@link EventLinker.Configuration} and links the event listener 
	 * handled by this implementation of {@link EventLinker} to the given views.
	 * 
	 * @param config
	 * 			the {@link EventLinker.Configuration} which for this {@link EventLinker}
	 * <br><br>
	 * @since 1.1.0
	 */
	public abstract void link(final EventLinker.Configuration config);
}
