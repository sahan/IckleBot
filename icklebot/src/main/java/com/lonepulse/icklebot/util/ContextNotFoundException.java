package com.lonepulse.icklebot.util;

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


import android.content.Context;

import com.lonepulse.icklebot.IckleBotRuntimeException;


/**
 * <p>This {@link IckleBotRuntimeException} is thrown when a {@link Context} 
 * cannot be resolved from a given {@link Object}. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ContextNotFoundException extends IckleBotRuntimeException {


	private static final long serialVersionUID = -817344371107370454L;

	
	/**
	 * <p>Prints a detailed message with information about the failed context resolution.
	 * 
	 * @param objectClass
	 * 			the {@link Class} of the {@link Object} whose {@link Context} failed 
	 * 			to be resolved.
	 * 
	 * @since 1.1.0
	 */
	public ContextNotFoundException(Class<?> objectClass) {
		
		super("Failed to resolve a context from " + objectClass.getName() + ". ");
	}
	
	/**
	 * <p>Prints a detailed message with information about the failed context resolution.
	 * 
	 * @param objectClass
	 * 			the {@link Class} of the {@link Object} whose {@link Context} failed 
	 * 			to be resolved.
	 * 
	 * @param targetClass
	 * 			the {@link Class} of the target context to which the given object 
	 * 			failed to conform
	 * 
	 * @since 1.1.0
	 */
	public ContextNotFoundException(Class<?> objectClass, Class<?> targetClass) {
		
		super("Failed to resolve " + objectClass.getName() + " into a context  of type " + 
		      targetClass.getName() + ". ");
	}
	
	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException()} 
	 */
	public ContextNotFoundException() {
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(String)}
	 */
	public ContextNotFoundException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(Throwable)}
	 */
	public ContextNotFoundException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(String, Throwable)}
	 */
	public ContextNotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
