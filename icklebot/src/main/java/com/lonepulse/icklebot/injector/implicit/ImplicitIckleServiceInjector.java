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

import android.content.Context;

import com.lonepulse.icklebot.annotation.inject.IckleService;
import com.lonepulse.icklebot.injector.InjectionException;
import com.lonepulse.icklebot.injector.InjectionProvider;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;

/**
 * <p>An implementation of {@link InjectionProvider} which is responsible 
 * for injecting <i>Ickle Services</i>.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ImplicitIckleServiceInjector extends ImplicitInjectionProvider {

	
	protected ImplicitIckleServiceInjector() {
		
		super(InjectionCategory.ICKLE_SERVICE);
	}
	
	@Override
	protected Object inject(Configuration config, Field field) {
		
		Class<?> contractClass = field.getType();
		IckleService ickleService = contractClass.getAnnotation(IckleService.class);
		Class<?> implementationClass = ickleService.value();
		
		try {
			
			try {
			
				return implementationClass.newInstance();
			}
			catch(InstantiationException ie) {
			
				Constructor<?> constructor = implementationClass.getConstructor(Context.class);
				
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
				
					return constructor.newInstance(config.getContext());
				}
			}
		}
		catch (Exception e) {
			
			throw (e instanceof InjectionException)? (InjectionException)e :new InjectionException(e);
		}
	}
}
