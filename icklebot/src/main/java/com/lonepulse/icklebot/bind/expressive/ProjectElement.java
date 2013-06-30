package com.lonepulse.icklebot.bind.expressive;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

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


/**
 * <p>This {@link Operator} exposes an element in an <b>array</b> or a 
 * {@link Collection}. However, only <i>ordered collections</i> are eligible 
 * for selection of an element in proper sequence. If used on a {@link Map} 
 * it exposes an {@link Entry}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ProjectElement extends AbstractOperator {


	/**
	 * <p>Instantiates a new {@link ProjectElement} operator. 
	 *
	 * @since 1.1.0
	 */
	public ProjectElement() {
		
		super(new Symbol("[", "]"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object onEvaluate(Object target, Object... args) {

		if(args == null || args.length != 1) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(getClass().getName())
			.append(" requires a single integer or long parameter as an argument. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		if(!(args[0] instanceof Integer) && !(args[0] instanceof Long)) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(getClass().getName())
			.append(" was given a parameter of type ")
			.append(args[0].getClass().getName())
			.append(", but requires a ")
			.append(int.class.getName())
			.append(", ")
			.append(Integer.class.getName())
			.append(", ")
			.append(long.class.getName())
			.append(" or a ")
			.append(Long.class.getName());
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		int index = (args[0] instanceof Integer)? 
					  ((Integer)args[0]).intValue() :((Long)args[0]).intValue();
		
		try {
			
			if(target instanceof Collection<?>)
				return ((Collection<?>) target).toArray()[index];
			
			if(target instanceof Map<?, ?>)
				return ((Map<?, ?>) target).entrySet().toArray()[index];
			
			else if(target instanceof byte[])
				return ((byte[]) target)[index];
			
			else if(target instanceof short[])
				return ((short[]) target)[index];
			
			else if(target instanceof int[])
				return ((int[]) target)[index];
			
			else if(target instanceof long[])
				return ((long[]) target)[index];
			
			else if(target instanceof float[])
				return ((float[]) target)[index];
			
			else if(target instanceof double[])
				return ((double[]) target)[index];
			
			else if(target instanceof char[])
				return ((char[]) target)[index];
			
			else if(target instanceof boolean[])
				return ((boolean[]) target)[index];
			
			else if(target instanceof Object[])
				return ((Object[]) target)[index];
		}
		catch(ArrayIndexOutOfBoundsException aioobe) {
			
			throw new OperationFailedException(this, target, index, aioobe);
		}
		
		throw new OperationFailedException(this, target, index);
	}
}
