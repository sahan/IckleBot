package com.lonepulse.icklebot.network;

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
 * <p>This enum represents the state of an <i>available</i> network.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum NetworkState {
	
	/**
	 * <p>Attempting to establish the connection.
	 * 
	 * @since 1.1.0
	 */
	CONNECTING,
	
	/**
	 * <p>The connection has been established.
	 * 
	 * @since 1.1.0
	 */
	CONNECTED,
	
	/**
	 * <p>The connection has been suspended.
	 * 
	 * @since 1.1.0
	 */
	SUSPENDED,

	/**
	 * <p>Attempting to terminate the connection.
	 * 
	 * @since 1.1.0
	 */
	DISCONNECTING,
	
	/**
	 * <p>The connection has been terminated.
	 * 
	 * @since 1.1.0
	 */
	DISCONNECTED,
	
	/**
	 * <p>The connectivity state cannot be determined.
	 * 
	 * @since 1.1.0
	 */
	UNKNOWN;
}
