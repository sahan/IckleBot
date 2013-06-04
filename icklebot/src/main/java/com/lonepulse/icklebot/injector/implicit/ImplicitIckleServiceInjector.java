package com.lonepulse.icklebot.injector.implicit;

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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

import android.content.Context;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.IckleService;
import com.lonepulse.icklebot.injector.InjectionException;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting <i>Ickle Services</i>.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ImplicitIckleServiceInjector implements Injector {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {

		Set<Field> fields = config.getInjectionTargets(InjectionCategory.ICKLE_SERVICE);
		Class<? extends Object> implementationClass = null;
		
		for (Field field : fields) {
			
			try {
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				Class<? extends Object> contractClass = field.getType();
				IckleService ickleService = contractClass.getAnnotation(IckleService.class);
				implementationClass = ickleService.value();
				
				try {
					
					field.set(config.getContext(), implementationClass.newInstance());
				}
				catch(InstantiationException ie) {
					
					Constructor<? extends Object> constructor = implementationClass.getConstructor(Context.class);
					
					if(constructor == null) {
						
						StringBuilder errorContext = new StringBuilder()
						.append("The Ickle Service implementation ")
						.append(implementationClass.getSimpleName())
						.append(" must expose a public no-argument constructor ")
						.append("or a constructor which takes only a single ")
						.append(Context.class.getName())
						.append(". ");
						
						throw new InjectionException(new InstantiationException(errorContext.toString()));
					}
					else {
					
						Context baseContext = ContextUtils.discover(config.getContext());
						field.set(config.getContext(), constructor.newInstance(baseContext));
					}
				}
			} 
			catch (Exception e) {
				
				StringBuilder errorContext = new StringBuilder()
				.append("Ickle Service injection failed");
				
				if(implementationClass != null) {
					
					errorContext.append(" for ")
					.append(implementationClass.getName())
					.append(". ");
				}
				else {
					
					errorContext.append(". ");
				}
				
				Log.e(getClass().getName(), errorContext.toString(), e);
			}
		}
	}
}
