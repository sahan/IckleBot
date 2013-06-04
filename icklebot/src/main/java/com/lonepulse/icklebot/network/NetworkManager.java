package com.lonepulse.icklebot.network;

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


import android.Manifest;
import android.net.NetworkInfo;

import com.lonepulse.icklebot.IckleBotRuntimeException;
import com.lonepulse.icklebot.annotation.inject.IckleService;

/**
 * <p>This contract specifies the services offered for querying network information.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@IckleService(NetworkService.class)
public interface NetworkManager {

	
	/**
	 * <p>Retrieves the {@link NetworkInfo} for an available and active network connection. 
	 * This service requires the manifest permission <b>ACCESS_NETWORK_STATE</b>.</p> 
	 * 
	 * @return {@code NetworkInfo} of an available network connection exists, 
	 * 		   else {@code null} if no active network is available
	 * 
	 * @throws IckleBotRuntimeException
	 * 			if the {@link Manifest.permission#ACCESS_NETWORK_STATE} 
	 * 			permission has not been granted 
	 * 
	 * @since 1.1.0
	 */
	NetworkInfo isAvailable();
	
	/**
	 * <p>Returns {@code true} if a network connection is being established.
	 * 
	 * @return {@code true} if a network connection is being established.
	 * 
	 * @since 1.1.0
	 */
	boolean isConnecting();
	
	/**
	 * <p>Returns {@code true} if a network connection is established.
	 * 
	 * @return {@code true} if a network connection is established.
	 * 
	 * @since 1.1.0
	 */
	boolean isConnected();
	
	/**
	 * <p>Returns {@code true} if a network connection is suspended.
	 * 
	 * @return {@code true} if a network connection is suspended.
	 * 
	 * @since 1.1.0
	 */
	boolean isSuspended();
	
	/**
	 * <p>Returns {@code true} if a network connection is being terminated.
	 * 
	 * @return {@code true} if a network connection is being terminated.
	 * 
	 * @since 1.1.0
	 */
	boolean isDisconnecting();
	
	/**
	 * <p>Returns {@code true} if the network is disconnected.
	 * 
	 * @return {@code true} if the network is disconnected.
	 * 
	 * @since 1.1.0
	 */
	boolean isDisconnected();
}
