package com.lonepulse.icklebot.util;

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

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * <p>A utility class which performs some common operations involved 
 * in discovering {@link Context}s.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class ContextUtils {
	
	/**
	 * <p>The common contract which takes an {@link Object} and 
	 * discovers the {@link Context} to which it falls.
	 * 
	 * @version 1.1.0
	 */
	private static interface ContextResolver {
		
		/**
		 * <p>Takes an {@link Object} and returns the {@link Context} which it 
		 * is associated with. 
		 *
		 * @param context
		 * 			the {@link Object} whose {@link Context} is to be resolved
		 * <br><br>
		 * @return the resolved {@link Context}
		 * <br><br>
		 * @since 1.1.0
		 */
		Context resolve(Object context);
	}
	
	/**
	 * <p>A set of all top level applicable {@link Context}s. 
	 */
	private static final Map<Class<?>, ContextResolver> contexts;
	
	static 
	{
		contexts = new HashMap<Class<?>, ContextResolver>();
		
		contexts.put(Activity.class, new ContextResolver() { //identical for 'FragmentActivity'
			
			@Override
			public Context resolve(Object context) {
				
				return (Activity)context;
			}
		});
		
		contexts.put(Fragment.class, new ContextResolver() {
			
			@Override
			public Context resolve(Object context) {
				
				return ((Fragment)context).getActivity();
			}
		});
		
		contexts.put(android.support.v4.app.Fragment.class, new ContextResolver() {
			
			@Override
			public Context resolve(Object context) {
				
				return ((android.support.v4.app.Fragment)context).getActivity();
			}
		});
		
		contexts.put(Application.class, new ContextResolver() {
			
			@Override
			public Context resolve(Object context) {
				
				return (Application)context;
			}
		});
	}
	
	
	/**
	 * <p>Constructor visibility is restricted to prevent 
	 * nonsensical instantiation.</p>
	 */
	private ContextUtils() {}
	
	/**
	 * <p>Takes the generic {@link Object} of a context and discovers 
	 * the actual {@link Context} instance. It takes an additional set 
	 * of {@link Class} and limits context discovery to those.
	 * 
	 * @param context
	 * 			the {@link Object} whose {@link Context} instance is 
	 * 			to be discovered
	 * <br><br>
	 * @param subset
	 * 			the {@link Class}es which are to be considered for this 
	 * 			context discovery. If this array is {@code null} or empty 
	 * 			all entries in {@link #contexts} are considered applicable
	 * <br><br>
	 * @return the {@link Context} instance of the given {@link Object}
	 * <br><br>
	 * @throws ContextNotFoundException
	 * 			if the {@link Context} cannot be resolved from the given object 
	 * <br><br>
	 * @since 1.0.0
	 */
	public static Context discover(Object context, Class<?>... subset) {
		
		Set<Class<?>> contextSubset = (subset == null || subset.length == 0)? 
			contexts.keySet() :new HashSet<Class<?>>(Arrays.asList(subset));
		
		Set<Class<?>> contextClasses = contexts.keySet();
		
		for (Class<?> contextClass : contextClasses) {
			
			if(!contextSubset.contains(contextClass)) continue;
			
			if(contextClass.isAssignableFrom(context.getClass())) {
				
				return contexts.get(contextClass).resolve(context);
			}
		}
		
		throw new ContextNotFoundException(context.getClass());
	}
	
	/**
	 * <p>Returns {@code true} if the given context is an {@link Activity}.
	 * 
	 * @param context
	 * 			the {@link Object} whose context type is to be clarified
	 * <br><br>
	 * @return {@code true} if the context is of type {@link Activity}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static boolean isActivity(Object context) {
		
		return (Activity.class.isAssignableFrom(context.getClass())
				|| FragmentActivity.class.isAssignableFrom(context.getClass()));
	}
	
	/**
	 * <p>Returns {@code true} if the given context is a {@link Fragment}.
	 * 
	 * @param context
	 * 			the {@link Object} whose context type is to be clarified
	 * <br><br>
	 * @return {@code true} if the context is of type {@link Fragment}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static boolean isFragment(Object context) {
		
		return Fragment.class.isAssignableFrom(context.getClass());
	}
	
	/**
	 * <p>Returns {@code true} if the given context is a {@link android.support.v4.app.Fragment}.
	 * 
	 * @param context
	 * 			the {@link Object} whose context type is to be clarified
	 * <br><br>
	 * @return {@code true} if the context is of type {@link android.support.v4.app.Fragment}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static boolean isSupportFragment(Object context) {
		
		return android.support.v4.app.Fragment.class.isAssignableFrom(context.getClass());
	}
	
	/**
	 * <p>Returns {@code true} if the given context is an {@link Application}.
	 * 
	 * @param context
	 * 			the {@link Object} whose context type is to be clarified
	 * <br><br>
	 * @return {@code true} if the context is of type {@link Application}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static boolean isApplication(Object context) {
		
		return Application.class.isAssignableFrom(context.getClass());
	}
	
	/**
	 * <p>Takes the generic {@link Object} of a context and returns 
	 * the actual {@link Context} instance as an {@link Activity} if 
	 * it conforms.
	 * 
	 * @param context
	 * 			the {@link Object} whose {@link Context} instance is 
	 * 			to be discovered
	 * <br><br>
	 * @return the {@link Activity} instance of the given {@link Object}, 
	 * 		   of a {@link FragmentActivity} if the supplied context is that 
	 * 		   of a {@link android.support.v4.app.Fragment}
	 * <br><br>
	 * @throws ContextNotFoundException
	 * 			if the {@link Context} does not conform to an {@link Activity}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static Activity asActivity(Object context) {
		
		if(ContextUtils.isActivity(context))
			return Activity.class.cast(contexts.get(Activity.class).resolve(context));
		
		if(ContextUtils.isFragment(context)) 
			return Activity.class.cast(contexts.get(Fragment.class).resolve(context));
		
		if(ContextUtils.isSupportFragment(context)) 
			return Activity.class.cast(contexts.get(android.support.v4.app.Fragment.class).resolve(context));
		
		throw new ContextNotFoundException(context.getClass(), Activity.class);
	}
	
	/**
	 * <p>Takes the generic {@link Object} of a context and returns 
	 * the actual {@link Context} instance as a {@link Fragment} if 
	 * it conforms.
	 * 
	 * @param context
	 * 			the {@link Object} whose {@link Context} instance is 
	 * 			to be discovered
	 * <br><br>
	 * @return the {@link Fragment} instance of the given {@link Object}
	 * <br><br>
	 * @throws ContextNotFoundException
	 * 			if the {@link Context} does not conform to a {@link Fragment}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static Fragment asFragment(Object context) {
		
		if(ContextUtils.isFragment(context)) 
			return Fragment.class.cast(context);
		
		throw new ContextNotFoundException(context.getClass(), Fragment.class);
	}
	
	/**
	 * <p>Takes the generic {@link Object} of a context and returns the actual 
	 * {@link Context} instance as a {@link android.support.v4.app.Fragmentnt} 
	 * if it conforms.
	 * 
	 * @param context
	 * 			the {@link Object} whose {@link Context} instance is 
	 * 			to be discovered
	 * <br><br>
	 * @return the {@link android.support.v4.app.Fragment} instance of the context
	 * <br><br>
	 * @throws ContextNotFoundException
	 * 			if the {@link Context} does not conform to a {@link Fragment}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static android.support.v4.app.Fragment asSupportFragment(Object context) {
		
		if(ContextUtils.isSupportFragment(context)) 
			return android.support.v4.app.Fragment.class.cast(context);
		
		throw new ContextNotFoundException(
			context.getClass(), android.support.v4.app.Fragment.class);
	}
	
	/**
	 * <p>Takes the generic {@link Object} of a context and returns 
	 * the actual {@link Context} instance as an {@link Application} if 
	 * it conforms.
	 * 
	 * @param context
	 * 			the {@link Object} whose {@link Context} instance is 
	 * 			to be discovered
	 * <br><br>
	 * @return the {@link Application} instance of the given {@link Object}
	 * <br><br>
	 * @throws ContextNotFoundException
	 * 			if the {@link Context} does not conform to a {@link Application}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static Application asApplication(Object context) {
		
		if(ContextUtils.isApplication(context))
			return Application.class.cast(context);
		
		else 
			return ContextUtils.asActivity(context).getApplication();
	}
	
	/**
	 * <p>Resolves the context of the given target object and find the {@link View} identified by the 
	 * specified ID. The given target's context should be an {@link Activity}, {@link FragmentActivity}, 
	 * {@link Fragment} or support {@link android.support.v4.app.Fragment}.</p>
	 *
	 * @param target
	 * 			the target object from which the specified {@link View} is to be retrieved
	 * <br><br>
	 * @param id
	 * 			the integer ID of the {@link View} to retrieve from the given target
	 * <br><br> 			
	 * @return the {@link View} on the given target identified by the ID, else {@code null} if the 
	 * 		   {@link View} was not found
	 * <br><br>
	 * @since 1.2.1
	 */
	public static View findViewById(Object target, int id) {
		
		return isActivity(target)? asActivity(target).findViewById(id) : 
			   isFragment(target)? asFragment(target).getView().findViewById(id) : 
			   isSupportFragment(target)? asSupportFragment(target).getView().findViewById(id) : null;
	}
}
