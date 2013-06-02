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


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>A concrete implementation of {@link NetworkManager} which offers 
 * services for querying network information.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class NetworkService implements NetworkManager {

	
	private static volatile NetworkManager instance;
	
	
	/**
	 * <p>The {@link ConnectivityManager} which is used to 
	 * discover network information.
	 */
	private ConnectivityManager connectivityManager;
	
	
	/**
	 * <p>Retrieves a new instance of {@link NetworkService}. Use 
	 * {@link #getInstance()} if your accessing the {@link NetworkService} 
	 * from an application {@link Context}.
	 */
	public NetworkService(Context context) {
		
		this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
	}
	
	/**
	 * <p>Creates a new instance of an implementation of {@link NetworkManager}.
	 * 
	 * @param context
	 * 			the {@link Context} of the {@link Application} instance via 
	 * 			{@link Activity#getApplication()} or {@link Activity#getApplicationContext()}
	 * 
	 * @return a new instance of {@link NetworkManager}.
	 * 
	 * @since 1.1.1
	 */
	public static final synchronized NetworkManager getInstance(Object context) {
		
		return (instance == null)? 
				(instance = new NetworkService(ContextUtils.asApplication(context))) :instance;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public NetworkInfo isAvailable() {
		
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		
		return (networkInfo == null)? null :(networkInfo.isAvailable())? networkInfo: null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isConnecting() {
		
		NetworkInfo networkInfo = isAvailable();
		
		return (networkInfo == null)? false 
				:(networkInfo.getState().equals(State.CONNECTING))? true :false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isConnected() {
		
		NetworkInfo networkInfo = isAvailable();
		
		return (networkInfo == null)? false 
				:(networkInfo.getState().equals(State.CONNECTED))? true :false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSuspended() {
		
		NetworkInfo networkInfo = isAvailable();
		
		return (networkInfo == null)? false 
				:(networkInfo.getState().equals(State.SUSPENDED))? true :false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDisconnecting() {
		
		NetworkInfo networkInfo = isAvailable();
		
		return (networkInfo == null)? false 
				:(networkInfo.getState().equals(State.DISCONNECTING))? true :false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDisconnected() {
		
		NetworkInfo networkInfo = isAvailable();
		
		return (networkInfo == null)? false 
				:(networkInfo.getState().equals(State.DISCONNECTED))? true :false;
	}
}
