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
 * <p>This {@link Operator} identifies the root target for an expression and 
 * exposes the target attribute of the model on which the expression is executed 
 * for usage as a result or for consumption by further operators.</p>
 *  
 * <p><b>Note</b> that this operator expects an expression string with the 
 * placeholders stripped.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Evaluate extends AbstractOperator {


	/**
	 * <p>The {@link Parser} which is used for parsing the expression document. 
	 */
	private Parser<StringBuilder> parser;
	
	/**
	 * <p>Instantiates a new {@link Evaluate} operator. 
	 *
	 * @since 1.1.0
	 */
	public Evaluate() {
		
		super(new Symbol("${", "}"));
		this.parser = new TextParser();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object onEvaluate(Object model, Object... args) {

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
				
				tag = xp.substring(0);
			}
			
			Field[] fields = model.getClass().getDeclaredFields();
			
			for (Field field : fields) {
				
				if(field.getName().equals(tag)) {
					
					try {
						
						if(!field.isAccessible()) 
							field.setAccessible(true);
						
						Object target = field.get(model);
						
						if(nextHeadIndex < 0) {
							
							return target;
						}
						else {

							return parser.parse(target, new StringBuilder(xp.substring(nextHeadIndex)));
						}
					}
					catch(Exception e) {
						
						throw new OperationFailedException(e);
					}
				}
			}
		}
		catch(Exception e) {
			
			throw new OperationFailedException(this, model, xp.toString());
		}
		
		throw new OperationFailedException(this, model, xp.toString());
	}
}
