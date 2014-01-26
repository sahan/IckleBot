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
 * <p>This {@link Exception} is thrown when injection processing fails 
 * in an {@link InjectionProvider}. These may be handled and an alternative 
 * solution provided - in some cases the failure may be recoverable.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IckleBotException extends Exception {

	
	private static final long serialVersionUID = 3496883791312392475L;

	
	/**
	 * See {@link Exception#Exception()} 
	 */
	public IckleBotException() {
	}

	/**
	 * See {@link Exception#Exception(String)} 
	 */
	public IckleBotException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link Exception#Exception(Throwable)} 
	 */
	public IckleBotException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link Exception#Exception(String, Throwable)} 
	 */
	public IckleBotException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
