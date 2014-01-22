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
import android.content.Context;
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
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>The {@link Injector} responsible for the <b>explicit injection</b>.</p>
 * 
 * @version 1.2.0 
 * <br><br>
 * @since 1.0.0 
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitResourceInjector implements Injector {
	

	private static final Map<InjectionCategory, Injector.InjectionProvider> EXPLICIT_INJECTION_STRATEGIES;
	
	
	static
	{
		EXPLICIT_INJECTION_STRATEGIES = new HashMap<InjectionCategory, Injector.InjectionProvider>();
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_VIEW, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {

				Object context = config.getContext();
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_VIEW);
				
				for (Field field : fields) {
				
					try {
					
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectView.class).value();
						
						if(ContextUtils.isActivity(context))
							field.set(context, ContextUtils.asActivity(context).findViewById(id));
						
						else if(ContextUtils.isFragment(context)) 
							field.set(context, ContextUtils.asFragment(context).getView().findViewById(id));
						
						else if(ContextUtils.isSupportFragment(context)) 
							field.set(context, ContextUtils.asSupportFragment(context).getView().findViewById(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectView.class.getSimpleName())
						.append(" failed on ")
						.append(context.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						e.printStackTrace();
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_STRING, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_STRING);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						field.set(config.getContext(), 
								baseContext.getString(field.getAnnotation(InjectString.class).value()));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectString.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");

						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DRAWABLE, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DRAWABLE);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectDrawable.class).value();
						field.set(config.getContext(), baseContext.getResources().getDrawable(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectDrawable.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});

		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_COLOR, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_COLOR);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectColor.class).value();
						field.set(config.getContext(), baseContext.getResources().getColor(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectColor.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_INTEGER, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_INTEGER);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectInteger.class).value();
						field.set(config.getContext(), baseContext.getResources().getInteger(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectInteger.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_DIMENSION, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_DIMENSION);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectDimension.class).value();
						field.set(config.getContext(), baseContext.getResources().getDimension(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectDimension.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_BOOLEAN, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_BOOLEAN);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectBoolean.class).value();
						field.set(config.getContext(), baseContext.getResources().getBoolean(id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectBoolean.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ARRAY, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ARRAY);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectArray.class).value();
						
						if(field.getType().equals(String[].class)) {
							
							field.set(config.getContext(), baseContext.getResources().getStringArray(id));
						}
						else if(field.getType().equals(int[].class) || field.getType().equals(Integer[].class)) {
							
							field.set(config.getContext(), baseContext.getResources().getIntArray(id));
						}
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectArray.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ANIMATION, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ANIMATION);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectAnimation.class).value();
						field.set(config.getContext(), AnimationUtils.loadAnimation(baseContext, id));
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectAnimation.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
						.append(" for ")
						.append(field.getName())
						.append(". ");
						
						Log.e(getClass().getName(), errorContext.toString(), e);
					}
				}
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.put(InjectionCategory.RESOURCE_ANIMATOR, new Injector.InjectionProvider() {
			
			@Override
			public void run(Injector.Configuration config) {
				
				Context baseContext = ContextUtils.discover(config.getContext());
				Set<Field> fields = config.getInjectionTargets(InjectionCategory.RESOURCE_ANIMATOR);
				
				for (Field field : fields) {
					
					try {
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						int id = field.getAnnotation(InjectAnimator.class).value();
						field.set(config.getContext(), AnimatorInflater.loadAnimator(baseContext, id)); 
					}
					catch (Exception e) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("Explicit resource injection with ")
						.append(InjectAnimator.class.getSimpleName())
						.append(" failed on ")
						.append(baseContext.getClass().getName())
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
		
		Collection<Injector.InjectionProvider> strategies = EXPLICIT_INJECTION_STRATEGIES.values();
		
		for (InjectionProvider strategy : strategies) {
			
			strategy.run(config);
		}
	}
}
