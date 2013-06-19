package com.lonepulse.icklebot.bind.expressive;

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


/**
 * <p>This {@link Operation} exposes member attributes of a given model 
 * for consumption by further operators or for use as a result.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Projection extends AbstractOperation {


	/**
	 * <p>Instantiates a new {@link Projection} operator. 
	 *
	 * @since 1.1.0
	 */
	public Projection() {
		
		super(new Symbol(".", ""));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object onEvaluate(Object target, Object... args) {

		if(args == null || args.length != 1 || !(args[0] instanceof String)) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(getClass().getName())
			.append(" requires a single String parameter as an argument. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		String attribute = (String)args[0];
		
		Field[] fields = target.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			if(field.getName().equals(attribute)) {
				
				try {
					
					if(!field.isAccessible()) 
						field.setAccessible(true);
					
					return field.get(target);
				}
				catch(Exception e) {
					
					throw new OperationFailedException(e);
				}
			}
		}
		
		throw new OperationFailedException(this, target, attribute);
	}
}
