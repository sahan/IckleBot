package com.lonepulse.icklebot.injector;

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
 * <p>A {@link RuntimeException} which signals an unexpected failure 
 * in an {@link InjectionProvider}. These are usually unrecoverable and as such 
 * must be notified to the user in a polite manner.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class InjectionException extends IckleBotRuntimeException {


	private static final long serialVersionUID = -119897488609677998L;

	
	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException()} 
	 */
	public InjectionException() {
	}

	/**
	 * See {@link RuntimIckleBotRuntimeExceptioneException#IckleBotRuntimeException(String)} 
	 */
	public InjectionException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(Throwable)} 
	 */
	public InjectionException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(String, Throwable)} 
	 */
	public InjectionException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
