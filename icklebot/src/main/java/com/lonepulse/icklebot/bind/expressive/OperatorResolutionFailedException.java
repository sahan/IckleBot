package com.lonepulse.icklebot.bind.expressive;

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


import com.lonepulse.icklebot.IckleBotException;

/**
 * <p>This exception is thrown due to a recoverable failure which occurs 
 * while resolving an {@link Operation} from a given {@link String}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class OperatorResolutionFailedException extends IckleBotException {


	private static final long serialVersionUID = 5121244556533214509L;

	
	/**
	 * <p>See {@link IckleBotException#IckleBotException()}.
	 *
	 * @since 1.1.0
	 */
	public OperatorResolutionFailedException() {}

	/**
	 * <p>See {@link IckleBotException#IckleBotException(String)}.
	 *
	 * @since 1.1.0
	 */
	public OperatorResolutionFailedException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * <p>See {@link IckleBotException#IckleBotException(Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public OperatorResolutionFailedException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * <p>See {@link IckleBotException#IckleBotException(String, Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public OperatorResolutionFailedException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
