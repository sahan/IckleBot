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

import com.lonepulse.icklebot.annotation.bind.Model;


/**
 * <p>This {@link Operator} identifies the root target for an expression and 
 * exposes the target attribute of the model on which the expression is executed 
 * for usage as a result or for consumption by further operators.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Evaluate extends AbstractOperator {
	
	
	/**
	 * <p>Instantiates a new {@link Evaluate} operator. 
	 *
	 * @since 1.1.0
	 */
	public Evaluate() {
		
		super(new Symbol("${", "}"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object onEvaluate(Field attribute, Object target, Object... args) {

		if(args == null || args.length != 1 || !(args[0] instanceof String)) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(getClass().getName())
			.append(" requires an expression String as an argument. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		StringBuilder xp = new StringBuilder((String)args[0]);
		
		String tag = "";
		int nextHeadIndex = -1;
		
		try {
			
			try {
				
				nextHeadIndex = ParserUtils.indexOfNextHead(xp);
				tag = xp.substring(0, nextHeadIndex);
			}
			catch(IndexNotFoundException infe) {
				
				tag = xp.toString();
			}
			
			if(attribute.getName().equalsIgnoreCase(tag)) {
				
				return target;
			}
			
			Class<?> attributeClass = target.getClass();
			boolean malformedTag = false;
			Model model = null;
			
			if(!attributeClass.getSimpleName().equalsIgnoreCase(tag)) {
				
				if(attributeClass.isAnnotationPresent(Model.class)) {
					
					model = attributeClass.getAnnotation(Model.class);
					malformedTag = model.value().equals(tag)? false :true; 
				}
				else {
					
					malformedTag = true;
				}
			}
			
			if(malformedTag) {
				
				StringBuilder errorContext = new StringBuilder()
				.append("The expression #{")
				.append(xp.toString())
				.append("} is meant to target \"")
				.append(tag)
				.append("\" but was used on a type named ")
				.append(target.getClass().getName())
				.append(model == null? " without an @Model annotation. " :" with an @Model annotation having " + 
						(model.value().equals("")? "no identifier. " :"the identifier \"" + model.value() + "\". "))
				.append("Please rewrite the expression so that it begins with ")
				.append(target.getClass().getSimpleName())
				.append(" (ignore case), or change the type of the target attribute to ")
				.append(target.getClass().getName())
				.append(", or annotate ")
				.append(target.getClass().getName())
				.append(" with @Model(\"")
				.append(tag)
				.append("\")");
				
				throw new OperationFailedException(errorContext.toString());
			}
			
			return target;
		}
		catch(Exception e) {
			
			throw (e instanceof OperationFailedException)? 
					(OperationFailedException)e :new OperationFailedException(this, target, xp.toString(), e);
		}
	}
}
