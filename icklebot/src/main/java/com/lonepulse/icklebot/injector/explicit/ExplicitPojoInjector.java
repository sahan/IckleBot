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

import com.lonepulse.icklebot.annotation.inject.InjectPojo;
import com.lonepulse.icklebot.annotation.inject.Pojo;
import com.lonepulse.icklebot.injector.InjectionException;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting <i>POJOs</i>.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitPojoInjector extends ExplicitInjectionProvider<InjectPojo> {
	
	
	protected ExplicitPojoInjector() {
		
		super(InjectionCategory.POJO);
	}
	
	@Override
	protected void inject(Configuration config, InjectPojo annotation, Field field) {
	
		try {
		
			Class<?> fieldType = field.getType();
			Class<? extends Object> pojoType = null;
			
			if(annotation.value().equals(Void.class)) {
			
				if(!fieldType.isAnnotationPresent(Pojo.class)) {
					
					StringBuilder builder = new StringBuilder()
					.append("Pojo injection failed on ")
					.append(field.getName())
					.append(". Please provide the missing class attribute on @InjectPojo. ")
					.append(" Or else specify the default implementation using @Pojo on ")
					.append(fieldType.getName())
					.append(". ");
					
					throw new InjectionException(builder.toString());
				}
				
				pojoType = fieldType.getAnnotation(Pojo.class).value();
			}
			else {
				
				pojoType = annotation.value();
			}
			
			field.set(config.getContext(), pojoType.newInstance());
		}
		catch(Exception e) {
			
			throw new InjectionException(e);
		}
	}
}
