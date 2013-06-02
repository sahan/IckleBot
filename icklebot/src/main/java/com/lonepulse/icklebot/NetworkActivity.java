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

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.util.Log;

import com.lonepulse.icklebot.annotation.profile.Profile;
import com.lonepulse.icklebot.network.NetworkManager;
import com.lonepulse.icklebot.network.NetworkUtils;
import com.lonepulse.icklebot.profile.ProfileService;
import com.lonepulse.icklebot.util.PermissionUtils;

/**
 * <p>This profile detects changes in the data connection and provides 
 * callbacks to handle them. In addition it can be used to discover 
 * additional network information via {@link NetworkActivity#network()}.</p>
 * 
 * <p>This profile requires the following permission:
 * <ul>
 *  <li>READ_PHONE_STATE: to register for data state changes.</li>
 * </ul>
 * </p>
 *  
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class NetworkActivity extends Activity {

	
	/**
	 * <p>This {@link BroadcastReceiver} is used to detect changes in network state.
	 */
	BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
		 
	    @Override
	    public void onReceive(final Context context, final Intent intent) {

	    	try {

	    		NetworkInfo networkInfo = 
	    			(NetworkInfo)intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
	    		
	    		if(networkInfo != null) {
	    		
			    	if(networkInfo.getState().equals(State.CONNECTING)) onNetworkConnecting();
			    	else if(networkInfo.getState().equals(State.CONNECTED)) onNetworkConnected();
			    	else if(networkInfo.getState().equals(State.SUSPENDED)) onNetworkSuspended();
			    	else if(networkInfo.getState().equals(State.DISCONNECTING)) onNetworkDisconnecting();
			    	else if(networkInfo.getState().equals(State.DISCONNECTED)) onNetworkDisconnected();
	    		}
	    	}
	    	catch(Exception e) {
	    		
	    		StringBuilder errorContext = new StringBuilder()
	    		.append("Failed to handle network state change to ")
	    		.append(NetworkUtils.getNetworkState(getApplicationContext()).name())
	    		.append(" at ")
	    		.append(this.getClass().getName())
	    		.append(". ");
	    		
	    		Log.e(NetworkActivity.class.getSimpleName(), errorContext.toString(), e);
	    	}
	    }
	};
	
	
	/**
	 * <p>Registers a {@link PhoneStateListener} to listen for changes in the data 
	 * connection state and invoke the appropriate callbacks.
	 * 
	 * @throws PermissionDeniedException
	 * 			if {@link Manifest.permission#READ_PHONE_STATE} is denied
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		if(ProfileService.getInstance(getApplicationContext()).isActive(this, Profile.NETWORK)) {
		
			if(!PermissionUtils.isGranted(this, Manifest.permission.ACCESS_NETWORK_STATE)) {
				
				Log.e(getClass().getSimpleName(), "Failed to register a receiver for changes in network state. ", 
					new IckleBotRuntimeException(
						new PermissionDeniedException(Manifest.permission.ACCESS_NETWORK_STATE, Profile.NETWORK)));
			}
			else {
			
				IntentFilter intentFilter = new IntentFilter();
				intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
				
				registerReceiver(networkStateReceiver, intentFilter);
			}
		}
	}
	
	/**
	 * <p>Unregisters the {@link PhoneStateListener} which listens for changes in the 
	 * data connection state.
	 * 
	 * @throws PermissionDeniedException
	 * 			if {@link Manifest.permission#ACCESS_NETWORK_STATE} is denied
	 */
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		if(ProfileService.getInstance(getApplicationContext()).isActive(this, Profile.NETWORK)
			&& PermissionUtils.isGranted(this, Manifest.permission.ACCESS_NETWORK_STATE)) {
			
			unregisterReceiver(networkStateReceiver);
		}
	}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for connection establishment.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onNetworkConnecting() {}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for connection availability.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onNetworkConnected() {}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for data connection suspension.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onNetworkSuspended() {}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for connection termination.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onNetworkDisconnecting() {}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for connection unavailability.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onNetworkDisconnected() {}

	/**
	 * See {@link NetworkUtils#getNetworkManager(Context)}.
	 */
	protected final NetworkManager network() {
		
		return NetworkUtils.getNetworkManager(getApplicationContext());
	}
}
