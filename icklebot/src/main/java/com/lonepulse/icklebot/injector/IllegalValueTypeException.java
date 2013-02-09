package com.lonepulse.icklebot.injector;

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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.IckleBotRuntimeException;

/**
 * <p>This {@link IckleBotRuntimeException} is thrown when the 
 * type of a field is different from that of the expected type 
 * for the {@link Annotation}.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IllegalValueTypeException extends RuntimeException {


	private static final long serialVersionUID = 8618674483928650559L;
	
	
	/**
	 * <p>Creates the message to be used in 
	 * {@link #IllegalValueTypeException(Class, Class, Set, Class, Field)}.
	 * 
	 * @since 1.0.0
	 */
	private static final String createMessage(Class<? extends IckleActivity> injectorActivity,
											  Class<? extends Annotation> annotation,
											  Class<? extends Object> expectedType,
											  Class<? extends Object> valueType,
											  Field field) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Annotation ");
		stringBuilder.append(annotation.getName() );
		stringBuilder.append(" is illegally used on field ");
		stringBuilder.append(field.getName());
		stringBuilder.append(" of type ");
		stringBuilder.append(valueType.getName());
		stringBuilder.append(" in ");
		stringBuilder.append(injectorActivity.getName());
		stringBuilder.append(". The expected field type is ");
		stringBuilder.append(expectedType.getName());
		stringBuilder.append( ". ");
	
		return stringBuilder.toString();
	}

	/**
	 * <p>Logs additional information about the {@link IckleActivity} 
	 * whose injection failed.</p>
	 * 
	 * @param injectorActivity
	 * 			the {@link Class} of the {@link IckleActivity} which 
	 * 			contains duplicate injections 
	 * <br><br>
	 * @param annotation
	 * 			the {@link Annotation} which cannot be used multiple 
	 * 			times on the same {@link IckleActivity}
	 * <br><br>
	 * @param expectedType
	 * 			the expected type for fields annotated
	 * <br><br>
	 * @param valueType
	 * 			the type of the field which is annotated
	 * <br><br>
	 * @param field
	 * 			the {@link Field} which was annotated
	 * <br><br>
	 * @since 1.0.0
	 */
	public IllegalValueTypeException(Class<? extends IckleActivity> injectorActivity,
									 Class<? extends Annotation> annotation,
									 Class<? extends Object> expectedType,
									 Class<? extends Object> valueType,
									 Field field) {
		
		this(IllegalValueTypeException.createMessage(injectorActivity, annotation, 
													  expectedType, valueType, field));
	}
	
	/**
	 * See {@link RuntimeException#RuntimeException()} 
	 */
	public IllegalValueTypeException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String)} 
	 */
	public IllegalValueTypeException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)} 
	 */
	public IllegalValueTypeException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)} 
	 */
	public IllegalValueTypeException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
