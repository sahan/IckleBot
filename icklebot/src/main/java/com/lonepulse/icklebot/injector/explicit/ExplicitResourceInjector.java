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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.animation.AnimatorInflater;
import android.content.res.Resources;
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
import com.lonepulse.icklebot.injector.InjectionProvider;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>The {@link InjectionProvider} responsible for the <b>explicit injection</b>.</p>
 * 
 * @version 1.2.0 
 * <br><br>
 * @since 1.0.0 
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitResourceInjector implements InjectionProvider {
	

	private static final List<ExplicitInjectionProvider<? extends Annotation>> EXPLICIT_INJECTION_STRATEGIES;
	
	static
	{
		EXPLICIT_INJECTION_STRATEGIES = new ArrayList<ExplicitInjectionProvider<? extends Annotation>>();
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectView>(InjectionCategory.RESOURCE_VIEW) {
			
			@Override
			protected Object inject(Configuration config, InjectView annotation, Field field) {
			
				Object target = config.getTarget();
				return ContextUtils.findViewById(target, annotation.value()); 			
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectString>(InjectionCategory.RESOURCE_STRING) {
			
			@Override
			protected Object inject(Configuration config, InjectString annotation, Field field) {
				
				return config.getContext().getString(annotation.value());
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectDrawable>(InjectionCategory.RESOURCE_DRAWABLE) {
			
			@Override
			protected Object inject(Configuration config, InjectDrawable annotation, Field field) {
				
				return config.getContext().getResources().getDrawable(annotation.value());
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectColor>(InjectionCategory.RESOURCE_COLOR) {
			
			@Override
			protected Object inject(Configuration config, InjectColor annotation, Field field) {
				
				return config.getContext().getResources().getColor(annotation.value());
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectInteger>(InjectionCategory.RESOURCE_INTEGER) {
			
			@Override
			protected Object inject(Configuration config, InjectInteger annotation, Field field) {
				
				return config.getContext().getResources().getInteger(annotation.value());
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectDimension>(InjectionCategory.RESOURCE_DIMENSION) {
			
			@Override
			protected Object inject(Configuration config, InjectDimension annotation, Field field) {
				
				return config.getContext().getResources().getDimension(annotation.value());
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectBoolean>(InjectionCategory.RESOURCE_BOOLEAN) {
			
			@Override
			protected Object inject(Configuration config, InjectBoolean annotation, Field field) {
				
				return config.getContext().getResources().getBoolean(annotation.value());
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectArray>(InjectionCategory.RESOURCE_ARRAY) {
			
			@Override
			protected Object inject(Configuration config, InjectArray annotation, Field field) {
				
				int id = annotation.value();
				Class<?> type = field.getType();
				Resources res = config.getContext().getResources();
				
				return type.equals(String[].class)? res.getStringArray(id) : 
					   type.equals(int[].class) || type.equals(Integer[].class)? res.getIntArray(id) :null;  
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectAnimation>(InjectionCategory.RESOURCE_ANIMATION) {
			
			@Override
			protected Object inject(Configuration config, InjectAnimation annotation, Field field) {
				
				return AnimationUtils.loadAnimation(config.getContext(), annotation.value());
			}
		});
		
		EXPLICIT_INJECTION_STRATEGIES.add(new ExplicitInjectionProvider<InjectAnimator>(InjectionCategory.RESOURCE_ANIMATOR) {
			
			@Override
			protected Object inject(Configuration config, InjectAnimator annotation, Field field) {
				
				return AnimatorInflater.loadAnimator(config.getContext(), annotation.value());
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(Configuration config) {
		
		for (ExplicitInjectionProvider<?> strategy : EXPLICIT_INJECTION_STRATEGIES) {
			
			strategy.run(config);
		}
	}
}
