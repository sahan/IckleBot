package com.lonepulse.icklebot.orm;

/*
 * #%L
 * IckleBot Library
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
 * <p>This {@link RuntimeException} is thrown when a particular property 
 * is not found in <b>ickle.orm.properties</b>. 
 * 
 * @version 1.1.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class PropertyNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 2314517192321917002L;
	

	/**
	 * <p>Takes the missing property and prints a detailed 
	 * message.
	 *
	 * @param property
	 * 			the property which was missing
	 * 
	 * @since 1.1.0
	 */
	public PropertyNotFoundException(String property) {
		
		super("Property " + property + " is missing in the assets directory. ");
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyNotFoundException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyNotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
