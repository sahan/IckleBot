package com.lonepulse.icklebot.injector.explicit;

/*
 * #%L
 * IckleBot
 * %%
 * Copyright (C) 2013 - 2014 Lonepulse
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
import java.lang.reflect.ParameterizedType;
import java.util.Set;

import android.util.Log;

import com.lonepulse.icklebot.injector.InjectionMode;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;

/**
 * <p>An abstract {@link Injector} which supports injection with {@link InjectionMode#EXPLICIT}.</p>
 * 
 * <p><b>Note</b> that all implementations need to be final.</p>
 * 
 * @param <T>
 * 			the {@link Annotation} which identifies dependency requests served by this provider implementation
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @since 1.2.1
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
public abstract class ExplicitInjectionProvider<T extends Annotation> implements Injector {

	
	private InjectionCategory injectionCategory;
	private Class<T> annotationType;
	
	
	/**
	 * <p>Instantiates a new {@link ExplicitInjectionProvider} with the given {@link InjectionCategory} 
	 * which is used to collect .</p>
	 *
	 * @param injectionCategory
	 *
	 * @since 1.2.4
	 */
	@SuppressWarnings("unchecked") //safe cast from a known generic type
	protected ExplicitInjectionProvider(InjectionCategory injectionCategory) {
		
		this.injectionCategory = injectionCategory;
		
		this.annotationType = (Class<T>)((ParameterizedType)
			getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * <p>Delegates to {@link #inject(Configuration, Annotation, Field)} for each field injection. Any 
	 * {@link RuntimeException}s are caught and logged with {@link Log#e(String, String, Throwable)} 
	 * with information field and context names.</p>
	 * 
	 * <p>Implementations that wish to alter the default behaviour may override this method and invoke 
	 * {@link #inject(Configuration, Annotation, Field)} as they see fit.</p> 
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void run(Configuration config) {
		
		Object context = config.getContext();
		Set<Field> fields = config.getInjectionTargets(injectionCategory);
		
		for (Field field : fields) {
			
			try {
				
				if(!field.isAccessible()) {
					
					field.setAccessible(true);
				}
				
				inject(config, field.getAnnotation(annotationType), field);
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), new StringBuilder("Injection failed for field <").append(field.getName())
					  .append("> on context <").append(context.getClass().getSimpleName()).append(">").toString(), e);
			}
		}
	}
	
	/**
	 * <p>This callback will be invoked for every {@link Field} in the context which requests a dependency 
	 *  served by this provider implementation.</p>
	 *
	 * @param config
	 * 			the {@link Injector.Configuration} which supplies all the information for injection
	 * <br><br> 
	 * @param annotation
	 * 			the annotation on the {@link Field} which explicitly identifies the dependency request 
	 * 			or provides additional metadata required for the injection 
	 * <br><br> 
	 * @param field
	 * 			the {@link Field} which requested a dependency served by this provider implementation; 
	 * 			if the {@link Field} is inaccessible an attempt will be made to assert accessibility 
	 * 			with {@link Field#setAccessible(true)}
	 * <br><br> 
	 * @since 1.2.1
	 */
	protected abstract void inject(Configuration config, T annotation, Field field);
}
