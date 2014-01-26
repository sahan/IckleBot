package com.lonepulse.icklebot;

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

import com.lonepulse.icklebot.injector.InjectionProvider;

/**
 * <p>A {@link RuntimeException} which signals an unexpected failure 
 * in an {@link InjectionProvider}. These are usually unrecoverable and as such 
 * must be notified to the user in a polite manner.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IckleBotRuntimeException extends RuntimeException {


	private static final long serialVersionUID = -7738465174547587835L;

	
	/**
	 * See {@link RuntimeException#RuntimeException()} 
	 */
	public IckleBotRuntimeException() {
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String))} 
	 */
	public IckleBotRuntimeException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(Throwable)} 
	 */
	public IckleBotRuntimeException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link RuntimeException#RuntimeException(String, Throwable)} 
	 */
	public IckleBotRuntimeException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
