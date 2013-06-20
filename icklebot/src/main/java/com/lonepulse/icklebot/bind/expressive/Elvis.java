package com.lonepulse.icklebot.bind.expressive;

import java.util.Collection;

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
 * <p>This an implementation of {@link AbstractOperator} which emulates an 
 * <b>Elvis Operator</b>. It determines if the input is {@code null}, <b>empty
 * </b> (for a {@link Collection} or {@code Object[]}) or {@code false} (for 
 * {@code boolean}s) and if so outputs the given argument as text, else the 
 * input is returned as is. 
 *  
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Elvis extends AbstractOperator {


	/**
	 * <p>Instantiates a new {@link Elvis} operator. 
	 *
	 * @since 1.1.0
	 */
	public Elvis() {
		
		super(new Symbol("?:", ""));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object onEvaluate(Object target, Object... args) {

		if(args == null || args.length != 1) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(getClass().getName())
			.append(" requires a single argument. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		Object arg = args[0];
		
		if(target == null) {

			return arg;
		}
		else if(target instanceof Boolean) {
			
			return ((Boolean)target)? target :arg;
		}
		else if(target instanceof String) {

			String targetString = (String)target;
			
			if(targetString.equalsIgnoreCase("true")) {
				
				return target;
			}
			else if(targetString.equalsIgnoreCase("false")) {
				
				return arg;
			}
			else if(targetString.equals("")) {
				
				return arg;
			}
			else {
				
				return target;
			}
		}
		else if(target instanceof Object[]) {
			
			return (((Object[])target).length == 0)? arg :target;
		}
		else if(target instanceof Collection<?>) {
			
			return (((Collection<?>)target).isEmpty())? arg :target;
		}
		else {
			
			return target;
		}
	}
}
