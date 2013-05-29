package com.lonepulse.icklebot.bind;

import android.view.View;

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
 * when binding a view to it's model data.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BindException extends IckleBotRuntimeException {


	private static final long serialVersionUID = 2914642704185985938L;

	
	/**
	 * <p>Prints a detailed message with the data and view whose binding failed.
	 * 
	 * @param data
	 * 			the data which failed to be bound to the view
	 * 
	 * @param view
	 * 			the which to which the data couldn't be bound
	 * 
	 * @since 1.1.0
	 */
	public BindException(Object data, View view) {
		
		this("Binding data " + data + " to view with " + view + " failed!");
	}
	
	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException()}
	 */
	public BindException() {
	}

	/**
	 * See {@link RuntimIckleBotRuntimeExceptioneException#IckleBotRuntimeException(String)} 
	 */
	public BindException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(Throwable)} 
	 */
	public BindException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * See {@link IckleBotRuntimeException#IckleBotRuntimeException(String, Throwable)} 
	 */
	public BindException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
