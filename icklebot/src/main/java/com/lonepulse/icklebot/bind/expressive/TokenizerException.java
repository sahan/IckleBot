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
 * <p>An {@link IckleBotRuntimeException} which signals a failure in performing 
 * tokenization for a {@link Symbol} on some target content. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class TokenizerException extends IckleBotRuntimeException {


	private static final long serialVersionUID = 2914642704185985938L;

	
	/**
	 * <p>Prints a detailed message using the {@link Symbol} for which 
	 * tokenization failed on some content.
	 * 
	 * @param symbol
	 * 			the {@link Symbol} for which tokenization failed
	 *
	 * @since 1.1.0
	 */
	public TokenizerException(Symbol symbol) {
		
		this("Tokenization failed for symbol " + symbol);
	}
	
	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException()}
	 */
	public TokenizerException() {}

	/**
	 * See {@link RuntimIckleBotRuntimeExceptioneException#IckleBotRuntimeException(String)} 
	 */
	public TokenizerException(String detailMessage) {
		
		super(detailMessage);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(Throwable)} 
	 */
	public TokenizerException(Throwable throwable) {
		
		super(throwable);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(String, Throwable)} 
	 */
	public TokenizerException(String detailMessage, Throwable throwable) {
		
		super(detailMessage, throwable);
	}
}
