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

import com.lonepulse.icklebot.event.resolver.EventCategory;
import com.lonepulse.icklebot.event.resolver.EventResolvers;

/**
 * <p>This is the common contract which all listener linkers must implement.</p>
 * 
 * @version 1.1.0 
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
			 * <p>Stores processed {@link Configuration}s, keyed by their {@link activity} 
			 * {@link Class}.
			 * 
			 * @since 1.1.0
			 */
			private Map<Class<? extends Activity>, Configuration>  cache
				= new HashMap<Class<? extends Activity>, EventLinker.Configuration>();
			
			/**
			 * <p>A delegate for {@link Map#put(Object, Object)} which wraps the 
			 * {@link #cache}.</p>
			 * 
			 * @param key
			 * 			the requesting instance of {@link activity}
			 * <br><br>
			 * @param value
			 * 			the {@link Configuration} for the {@link activity} 
			 * 			extension
			 * <br><br>
			 * @return the previous {@link Configuration} keyed by this 
			 * 		   {@link activity} {@link Class}, <b>if any</b>
			 * <br><br>
			 * @since 1.1.0
			 */
			public Configuration put(Activity key, Configuration value) {
				
				return cache.put(key.getClass(), value);
			}
			
			/**
			 * <p>A delegate for {@link Map#get(Object)} which wraps the {@link #cache}. 
			 * It takes the new instance of {@link activity} and updates the 
			 * {@link Configuration#activity} property as well.</p>
			 * 
			 * @param key
			 * 			the requesting instance of {@link activity}
			 * <br><br>
			 * @return the {@link Configuration} keyed by by this {@link activity} 
			 * 		   {@link Class}; else {@code null} if not found
			 * <br><br>
			 * @since 1.1.0
			 */
			public Configuration get(Activity key) {
				
				Configuration configuration = cache.get(key.getClass());
				
				if(configuration != null)
					configuration.setActivity(key);
				
				return configuration;
			}
		}
		

		/**
		 * <p>The {@link activity} which has requested 
		 * listener linking.</p>
		 * 
		 * @since 1.1.0
		 */
		private Activity activity;
		
		
		/**
		 * <p>The target {@link Method}s in the {@link #activity} 
		 * activity grouped into their categories by {@link EventCategory}.</p>
		 * 
		 * @since 1.1.0
		 */
		private Map<EventCategory, Set<Method>> listenerTargets;

		
		/**
		 * <p>Creates a <b>new</b> instance of {@link EventLinker.Configuration} 
		 * using the passed {@link activity}.</p>
		 * 
		 * <p>This is to be used in an <i>instantiated context</i>.</p>
		 * 
		 * @param activity
		 * 			the {@link activity} which has requested event 
		 * 			listener linking
		 * <br><br>
		 * @return a new instance of {@link EventLinker.Configuration}
		 * <br><br>
		 * @since 1.1.0
		 */
		public static Configuration newInstance(Activity Activity) {
			
			Configuration config = new Configuration();
			
			config.setActivity(Activity);
		
			Method[] methods = Activity.getClass().getDeclaredMethods();
			
			for (Method method : methods) {
			
				Set<EventCategory> listenerCategories = EventResolvers.BASIC.resolve(method);
				
				for (EventCategory listenerCategory : listenerCategories) {
					
					config.putListenerTarget(listenerCategory, method);
				}
			}
			
			return config;
		}
		
		/**
		 * <p>Retrieves the <b>cached</b> instance of {@link EventLinker.Configuration} 
		 * using the passed {@link activity}. If cached instance is not 
		 * found, a new instance is created, via {@link #newInstance(activity)}, 
		 * and cached.</p>
		 * 
		 * @param activity
		 * 			the {@link activity} which has requested event 
		 * 			listener linking
		 * <br><br>
		 * @return the <b>cached</b> instance of {@link EventLinker.Configuration}
		 * <br><br>
		 * @since 1.1.0
		 */
		public static Configuration getInstance(Activity Activity) {
		
			Configuration config = CACHE.INSTANCE.get(Activity);
			
			if(config != null) {

				config.setActivity(Activity);
			}
			else {
				
				config = newInstance(Activity);
				CACHE.INSTANCE.put(Activity, config);
			}
			
			return config;
		}
		
		/**
		 * <p>Constructor visibility restricted to prevent instantiation. 
		 * Please use the factory method {@link #newInstance(activity)}.</p>
		 * 
		 * <p>Initializes {@link #listenerTargets} to an empty {@link Map}.</p>
		 * 
		 * @since 1.1.0
		 */
		private Configuration() {
			
			this.listenerTargets = new HashMap<EventCategory, Set<Method>>(); 
		}

		/**
		 * <p>Accessor for {@link #activity}.</p>
		 * 
		 * @return {@link #activity}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Activity getActivity() {
			
			return activity;
		}

		/**
		 * <p>Mutator for {@link #activity}.</p>
		 * 
		 * @param activity
		 * 			the {@link activity} to populate 
		 * 			{@link #activity} 
		 * <br><br>
		 * @since 1.1.0
		 */
		private void setActivity(Activity activity) {
			
			this.activity = activity;
		}

		/**
		 * <p>Accessor for {@link #listenerTargets}.</p>
		 * 
		 * @return {@link #listenerTargets}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Map<EventCategory, Set<Method>> getListenerTargets() {
			
			return listenerTargets;
		}

		/**
		 * <p>Mutator for {@link #listenerTargets}. Takes a {@link Method} 
		 * along with its associated {@link EventCategory} and puts it 
		 * to the appropriate {@link Set} in {@link #listenerTargets}.</p>
		 * 
		 * @param listenerCategory
		 * 			the {@link EventCategory} to which the 
		 * 			{@link Method} belongs
		 * <br><br>
		 * @param method
		 * 			the {@link Method} to be categorized into an 
		 * 			{@link EventCategory}
		 * <br><br>
		 * @since 1.1.0
		 */
		private void putListenerTarget(EventCategory listenerCategory, Method method) {
			
			Set<Method> methods = listenerTargets.get(listenerCategory);
			
			if(methods == null) {
				
				methods = new HashSet<Method>();
				listenerTargets.put(listenerCategory, methods);
			}
			
			methods.add(method);
		}
		
		/**
		 * <p>Takes an {@link EventCategory} and retrieves 
		 * the {@link Set} of {@link Field}s under that category 
		 * as mapped in {@link #listenerTargets}.</p> 
		 * 
		 * @param listenerCategory
		 * 			the fields are to be retrieved for this 
		 * 			{@link EventCategory}			
		 * <br><br>
		 * @return the {@link Set} of {@link Field}s  under 
		 * 		   the category, else an empty {@link Set}
		 * <br><br>
		 * @since 1.1.0
		 */
		public Set<Method> getListenerTargets(EventCategory listenerCategory) {
			
			Set<Method> targets = listenerTargets.get(listenerCategory);
			
			return (targets == null)? 
						new HashSet<Method>() :Collections.unmodifiableSet(targets);
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
