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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import android.content.Context;

import com.lonepulse.icklebot.annotation.inject.IckleService;
import com.lonepulse.icklebot.annotation.inject.InjectIckleService;
import com.lonepulse.icklebot.injector.IllegalValueTypeException;
import com.lonepulse.icklebot.injector.InjectionException;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>An implementation of {@link Injector} which is responsible for injecting 
 * <b>Ickle Services</b>.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitIckleServiceInjector extends ExplicitInjectionProvider<InjectIckleService> {


	protected ExplicitIckleServiceInjector() {
		
		super(InjectionCategory.ICKLE_SERVICE);
	}

	@Override
	protected void inject(Configuration config, InjectIckleService annotation, Field field) {
		
		Class<? extends Object> contractClass = field.getType();
		
		if(!contractClass.isAnnotationPresent(IckleService.class)) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(contractClass.getName())
			.append(" is not an Ickle Service. Please remove the @")
			.append(InjectIckleService.class.getSimpleName())
			.append(" annotation or else change the type to a valid Ickle Service. ");
			
			throw new InjectionException(new IllegalValueTypeException(errorContext.toString()));
		}
		
		IckleService ickleService = contractClass.getAnnotation(IckleService.class);
		Class<? extends Object> implementationClass = ickleService.value();
		
		try {
			
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
			
			throw (e instanceof InjectionException)? (InjectionException)e :new InjectionException(e);
		}
	}
}
