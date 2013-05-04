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

import java.util.Set;

import android.content.Context;

import com.lonepulse.icklebot.IckleBotRuntimeException;

/**
 * <p>This {@link IckleBotRuntimeException} is thrown when a supplied context is not 
 * processed by the recipient.
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IllegalContextException extends InjectionException {


	private static final long serialVersionUID = 3708197873850709116L;

	
	/**
	 * <p>Creates the message to be used in {@link #IllegalContextException(Object, Set)}.
	 * 
	 * @since 1.0.0
	 */
	private static final String createMessage(Object illegalContext, Set<Class<?>> applicableContexts) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("The given context ");
		stringBuilder.append(illegalContext.getClass().getName());
		stringBuilder.append(" is illegal");
		
		if(applicableContexts != null && applicableContexts.size() > 0) {
			
			stringBuilder.append(". The only applicable contexts are");
			
			for (Class<?> contextClass : applicableContexts) {
				
				stringBuilder.append(", ");
				stringBuilder.append(contextClass.getSimpleName());
			}
		}
		
		stringBuilder.append(". ");

		return stringBuilder.toString();
	}

	/**
	 * <p>Logs additional information about the illegal context and 
	 * the set of {@link Context}s which are actually accepted. 
	 * 
	 * @param illegalContext
	 * 			the context which is illegal and cannot be processed by 
	 * 			the recipient 
	 *  
	 * <br><br>
	 * @param applicableContexts
	 * 			the set of applicable {@link Context}s which are accepted 
	 * 			by the recipient
	 * <br><br>
	 * @since 1.0.0
	 */
	public IllegalContextException(Object illegalContext, Set<Class<?>> applicableContexts) {
		
		this(IllegalContextException.createMessage(illegalContext, applicableContexts));
	}
	
	/**
	 * See {@link InjectionException#InjectionException()} 
	 */
	public IllegalContextException() {
	}

	/**
	 * See {@link InjectionException#InjectionException(String)} 
	 */
	public IllegalContextException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link InjectionException#InjectionException(Throwable)} 
	 */
	public IllegalContextException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link InjectionException#InjectionException(String, Throwable)} 
	 */
	public IllegalContextException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
