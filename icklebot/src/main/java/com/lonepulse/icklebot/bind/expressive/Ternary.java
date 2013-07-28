package com.lonepulse.icklebot.bind.expressive;

import java.lang.reflect.Field;
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
 * <p>This is an implementation of {@link AbstractOperator} which emulates a <b>Ternary Operator</b>.</p>
 * <p>See {@link Ternary#evaluate(Object, Object...)}.</p>
 *  
 * @version 1.1.0
 * <br><br>
 * @since 1.2.1
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class Ternary extends AbstractOperator {


	/**
	 * <p>Instantiates a new {@link Ternary} operator. 
	 *
	 * @since 1.2.1
	 */
	public Ternary() {
		
		super(new Symbol("?", ""));
	}

	/**
	 * <p>Evaluates the target object to determine if it's essentially <b>void</b> or {@code false}. 
	 * If the target is void or false, the first argument is returned - else the second argument 
	 * is returned.</p>
	 * 
	 * <p>This operator is similar to the {@link Elvis} operator; but unlike the {@link Elvis} 
	 * operator it acts on two arguments (strings). The current version of the {@link Ternary} 
	 * operator does not accept <b>logical expressions</b> for evaluation.</p> 
	 * 
	 * <p>The operator evaluates to the first argument if the target is:</p>
	 * 
	 * <ul>
	 * 	<li> {@code null}</li>
	 * 	<li> of type {@code boolean} or {@link Boolean} and is {@code false}</li>
	 * 	<li> of type {@link CharSequence} and is "{@code false}" or ""</li>
	 * 	<li> of type Object[], primitive array, or {@link Collection} and is empty</li>
	 * 	<li> of type {@link Map} and its entry set is empty</li>
	 * </ul>
	 *  
	 * <p>See {@link AbstractOperator#onEvaluate(Object, Object...)}.</p>
	 * 
	 * @since 1.2.1
	 */
	@Override
	public Object onEvaluate(Field attribute, Object target, Object... args) {
		
		if(args == null || args.length != 2) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(getClass().getName())
			.append(" requires two String arguments. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		Object pass = args[0];
		Object fail = args[1];
		
		if(target == null) {

			return fail;
		}
		else if(target instanceof Boolean) {
			
			return ((Boolean)target)? pass :fail;
		}
		else if(target instanceof CharSequence) {

			String targetString = ((CharSequence)target).toString();
			return (targetString.equalsIgnoreCase("false") || targetString.equals(""))? fail :pass;
		}
		else if(target instanceof Object[]) {
			
			return (((Object[])target).length == 0)? fail :pass;
		}
		else if(target instanceof Collection<?>) {
			
			return (((Collection<?>)target).isEmpty())? fail :pass;
		}
		else if(target instanceof Map<?, ?>) {
			
			return ((Map<?, ?>)target).entrySet().isEmpty()? fail :pass;
		}
		else if(target instanceof char[]) {
			
			return (((char[])target).length == 0)? fail :pass;
		}
		else if(target instanceof byte[]) {
			
			return (((byte[])target).length == 0)? fail :pass;
		}
		else if(target instanceof short[]) {
			
			return (((short[])target).length == 0)? fail :pass;
		}
		else if(target instanceof int[]) {
			
			return(((int[])target).length == 0)? fail :pass;
		}
		else if(target instanceof long[]) {
			
			return (((long[])target).length == 0)? fail :pass;
		}
		else if(target instanceof float[]) {
			
			return (((float[])target).length == 0)? fail :pass;
		}
		else if(target instanceof double[]) {
			
			return (((double[])target).length == 0)? fail :pass;
		}
		else if(target instanceof boolean[]) {
			
			return (((boolean[])target).length == 0)? fail :pass;
		}
		else {
			
			return fail;
		}
	}
}
