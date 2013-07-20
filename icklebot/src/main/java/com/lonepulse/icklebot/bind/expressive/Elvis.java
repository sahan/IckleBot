package com.lonepulse.icklebot.bind.expressive;

import java.util.Collection;
import java.util.Map;

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
 * <p>This is an implementation of {@link AbstractOperator} which emulates a b>Elvis Operator</b>.</p>
 * <p>See {@link Elvis#evaluate(Object, Object...)}.</p>
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
	 * <p>Evaluates the target object to determine if it's essentially <b>void</b>. If the 
	 * target is void, the supplied argument is returned - else the target is returned as is.</p>
	 * 
	 * <p>The operator evaluates to the argument if the target is:</p>
	 * 
	 * <ul>
	 * 	<li> {@code null}</li>
	 * 	<li> of type {@code boolean} or {@link Boolean} and is {@code false}</li>
	 * 	<li> of type {@link CharSequence} and is "{@code false}" or ""</li>
	 * 	<li> of type Object[], primitive array, or {@link Collection} and is empty</li>
	 * 	<li> of type {@link Map} and its entry set is empty</li>
	 * </ul> 
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
		else if(target instanceof CharSequence) {

			String targetString = ((CharSequence)target).toString();
			return (targetString.equalsIgnoreCase("false") || targetString.equals(""))? arg :target;
		}
		else if(target instanceof Object[]) {
			
			return (((Object[])target).length == 0)? arg :target;
		}
		else if(target instanceof Collection<?>) {
			
			return (((Collection<?>)target).isEmpty())? arg :target;
		}
		else if(target instanceof Map<?, ?>) {
			
			return ((Map<?, ?>)target).entrySet().isEmpty()? arg :target;
		}
		else if(target instanceof char[]) {
			
			return (((char[])target).length == 0)? arg :target;
		}
		else if(target instanceof byte[]) {
			
			return (((byte[])target).length == 0)? arg :target;
		}
		else if(target instanceof short[]) {
			
			return (((short[])target).length == 0)? arg :target;
		}
		else if(target instanceof int[]) {
			
			return(((int[])target).length == 0)? arg :target;
		}
		else if(target instanceof long[]) {
			
			return (((long[])target).length == 0)? arg :target;
		}
		else if(target instanceof float[]) {
			
			return (((float[])target).length == 0)? arg :target;
		}
		else if(target instanceof double[]) {
			
			return (((double[])target).length == 0)? arg :target;
		}
		else if(target instanceof boolean[]) {
			
			return (((boolean[])target).length == 0)? arg :target;
		}
		else {
			
			return target;
		}
	}
}
