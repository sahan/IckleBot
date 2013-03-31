package com.lonepulse.icklebot.injector.explicit;

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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import android.animation.AnimatorInflater;
import android.app.Activity;
import android.util.Log;
import android.view.animation.AnimationUtils;

import com.lonepulse.icklebot.annotation.inject.InjectAnimation;
import com.lonepulse.icklebot.annotation.inject.InjectAnimator;
import com.lonepulse.icklebot.annotation.inject.InjectArray;
import com.lonepulse.icklebot.annotation.inject.InjectBoolean;
import com.lonepulse.icklebot.annotation.inject.InjectColor;
import com.lonepulse.icklebot.annotation.inject.InjectDimension;
import com.lonepulse.icklebot.annotation.inject.InjectDrawable;
import com.lonepulse.icklebot.annotation.inject.InjectInteger;
import com.lonepulse.icklebot.annotation.inject.InjectString;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;

/**
 * <p>An implementation of {@link Injector} which is responsible for 
 * the <b>explicit injection of resources</b>.
 * 
 * @version 1.1.0 
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitResourceInjector implements Injector {
	

	/**
	 * <p>Maintains all the {@link InjectionStrategy}s which are used for <b>explicit injection</b>. 
	 */
	private static final Map<InjectionCategory, Injector.InjectionStrategy> EXPLICIT_INJECTION_STRATEGIES;
	
	static
	{
		EXPLICIT_INJECTION_STRATEGIES = new HashMap<InjectionCategory, Injector.InjectionStrategy>();
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_VIEW, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {

				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_VIEW);
				
				for (Field field : fields) {
				
					try {
					
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectView.class).value();
						field.set(context, context.findViewById(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectView.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_STRING, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_STRING);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						field.set(context, context.getString(field.getAnnotation(InjectString.class).value()));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectString.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DRAWABLE, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DRAWABLE);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectDrawable.class).value();
						field.set(context, context.getResources().getDrawable(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectDrawable.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});

		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_COLOR, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_COLOR);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectColor.class).value();
						field.set(context, context.getResources().getColor(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectColor.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_INTEGER, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_INTEGER);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectInteger.class).value();
						field.set(context, context.getResources().getInteger(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectInteger.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DIMENSION, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DIMENSION);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectDimension.class).value();
						field.set(context, context.getResources().getDimension(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectDimension.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_BOOLEAN, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_BOOLEAN);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectBoolean.class).value();
						field.set(context, context.getResources().getBoolean(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectBoolean.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ARRAY, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ARRAY);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectArray.class).value();
						
						if(field.getType().equals(String[].class)) {
							
							field.set(context, context.getResources().getStringArray(id));
						}
						else if(field.getType().equals(int[].class) || field.getType().equals(Integer[].class)) {
							
							field.set(context, context.getResources().getIntArray(id));
						}
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectArray.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ANIMATION, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ANIMATION);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectAnimation.class).value();
						field.set(context, AnimationUtils.loadAnimation(config.getActivity(), id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectAnimation.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ANIMATOR, new Injector.InjectionStrategy() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Activity context = config.getActivity();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ANIMATOR);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectAnimator.class).value();
						field.set(context, AnimatorInflater.loadAnimator(config.getActivity(), id)); 
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectAnimator.class.getSimpleName())
						.append(" failed on ")
						.append(context.getLocalClassName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {
		
		Collection<Injector.InjectionStrategy> strategies = EXPLICIT_INJECTION_STRATEGIES.values();
		
		for (InjectionStrategy strategy : strategies) {
			
			strategy.run(config);
		}
	}
}
