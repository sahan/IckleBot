package com.lonepulse.icklebot.bind.expressive;

import com.lonepulse.icklebot.IckleBotRuntimeException;

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
 * <p>An {@link IckleBotRuntimeException} which signals a failure in executing 
 * an {@link Operation} strategy. 
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class OperationFailedException extends IckleBotRuntimeException {


	private static final long serialVersionUID = 2914642704185985938L;

	
	/**
	 * <p>Prints a detailed message using the {@link Operation} which failed 
	 * and the arguments it was supposed to act on.
	 * 
	 * @param operator
	 * 			the {@link Operation} which failed
	 *
	 * @param target
	 *			the target of this {@link Operation} 		
	 * 
	 * @param arg
	 * 			the arguments which the {@link Operation} acted on
	 * 
	 * @since 1.1.0
	 */
	public OperationFailedException(Operation operator, Object target, Object arg) {
		
		this(operator.getClass().getName() + " failed on " + 
			 target.getClass().getName() + " for argument " + arg);
	}
	
	/**
	 * <p>Prints a detailed message using the {@link Operation} which failed 
	 * and the arguments it was supposed to act on.
	 * 
	 * @param operator
	 * 			the {@link Operation} which failed
	 *
	 * @param target
	 *			the target of this {@link Operation} 		
	 * 
	 * @param arg
	 * 			the arguments which the {@link Operation} acted on
	 * 
	 * @param cause
	 * 			the root cause of this exception
	 * 
	 * 
	 * @since 1.1.1
	 */
	public OperationFailedException(Operation operator, Object target, Object arg, Throwable cause) {
		
		this(operator.getClass().getName() + " failed on " + 
				target.getClass().getName() + " for argument " + arg, cause);
	}
	
	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException()}
	 */
	public OperationFailedException() {}

	/**
	 * See {@link RuntimIckleBotRuntimeExceptioneException#IckleBotRuntimeException(String)} 
	 */
	public OperationFailedException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(Throwable)} 
	 */
	public OperationFailedException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(String, Throwable)} 
	 */
	public OperationFailedException(String detailMessage, Throwable throwable) {
		
		super(detailMessage, throwable);
	}
}
