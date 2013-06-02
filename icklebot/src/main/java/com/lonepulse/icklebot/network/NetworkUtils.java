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


import android.Manifest;
import android.content.Context;

import com.lonepulse.icklebot.IckleBotRuntimeException;
import com.lonepulse.icklebot.PermissionDeniedException;
import com.lonepulse.icklebot.annotation.profile.Profile;
import com.lonepulse.icklebot.util.ContextUtils;
import com.lonepulse.icklebot.util.PermissionUtils;

/**
 * <p>This class offers a set of utility operations for network management.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class NetworkUtils {
	
	
	/**
	 * <p>Constructor visibility. Instantiation is nonsensical.</p>
	 */
	private NetworkUtils() {}

	/**
	 * <p>Retrieves an instance of {@link NetworkManager} which can be used to 
	 * discover additional information about the network.</p>
	 * 
	 * <p>This service is restricted by <b>profiles</b>. It can be used even if 
	 * {@link Profile#NETWORK} is not activated.</p>
	 * 
	 * <p>This service requires the following permission:
	 * <ul>
	 * 	<li>ACCESS_NETWORK_STATE: to discover network information.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param context
	 * 			the invocation context
	 * 
	 * @return an instance of {@link NetworkManager}
	 * 
	 * @throws PermissionDeniedException
	 * 			if {@link Manifest.permission#ACCESS_NETWORK_STATE} is denied
	 * 
	 * @since 1.1.0
	 */
	public static NetworkManager getNetworkManager(Object context) {
		
		if(!PermissionUtils.isGranted(context, Manifest.permission.ACCESS_NETWORK_STATE))
			throw new IckleBotRuntimeException(
				new PermissionDeniedException(Manifest.permission.ACCESS_NETWORK_STATE, Profile.NETWORK));
		
		if(ContextUtils.isApplication(context))
			return NetworkService.getInstance(context);
		
		else
			return new NetworkService(ContextUtils.discover(context));
	}
	
	/**
	 * <p>Returns an instance of {@link NetworkState} to describe the connectivity state 
	 * of the available network.
	 *
	 * @param context
	 *			the {@link Context} which is requesting the network state 
	 * 
	 * @return a {@link String} which represents the network state
	 * 
	 * @since 1.1.1
	 */
	public static final NetworkState getNetworkState(Context context) {
		
		NetworkManager networkManager = NetworkUtils.getNetworkManager(context);
		
		if(networkManager.isConnecting()) return NetworkState.CONNECTING;
		else if(networkManager.isConnected()) return NetworkState.CONNECTED;
		else if(networkManager.isSuspended()) return NetworkState.SUSPENDED;
		else if(networkManager.isDisconnecting()) return NetworkState.DISCONNECTING;
		else if(networkManager.isDisconnected()) return NetworkState.DISCONNECTED;
		else return NetworkState.UNKNOWN;
	}
}
