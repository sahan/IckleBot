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

import java.io.FileNotFoundException;

/**
 * <p>This {@link RuntimeException} is thrown when the <b>ickle.orm.properties</b> 
 * does not exist in the <b>assets directory</b>. 
 * 
 * @version 1.1.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class PropertyFileNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 412578574206793676L;

	
	/**
	 * <p>See {@link RuntimeException#RuntimeException()}.
	 * 
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(FileNotFoundException fnfe) {
		
		this("File orm.properties is missing in the assets directory. " + 
			  "Please create the file and add fill the required properties.", fnfe);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
