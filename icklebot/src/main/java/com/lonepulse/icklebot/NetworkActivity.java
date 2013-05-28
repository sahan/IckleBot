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
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

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
	 * <p>An instance of {@link PhoneStateListener} which is registered to listen 
	 * for data connection states.
	 */
	private PhoneStateListener phoneStateListener = new PhoneStateListener() {
		
		@Override
		public void onDataConnectionStateChanged(int state) {
			
			switch (state) { 
				
				case TelephonyManager.DATA_CONNECTED: {
					
					onDataConnected();
					break;
				}
					
				case TelephonyManager.DATA_CONNECTING: {
						
					onDataConnecting();
					break;
				}
					
				case TelephonyManager.DATA_SUSPENDED: {
						
					onDataSuspended();
					break;
				}
					
				case TelephonyManager.DATA_DISCONNECTED: {
					
					onDataDisconnected();
					break;
				}
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
		
			if(!PermissionUtils.isGranted(this, Manifest.permission.READ_PHONE_STATE))
				throw new PermissionDeniedException(Manifest.permission.READ_PHONE_STATE, Profile.NETWORK);
			
			((TelephonyManager)getSystemService(TELEPHONY_SERVICE))
			.listen(phoneStateListener, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
		}
	}
	
	/**
	 * <p>Unregisters the {@link PhoneStateListener} which listens for changes in the 
	 * data connection state.
	 * 
	 * @throws PermissionDeniedException
	 * 			if {@link Manifest.permission#READ_PHONE_STATE} is denied
	 */
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		if(ProfileService.getInstance(getApplicationContext()).isActive(this, Profile.NETWORK)
			&& PermissionUtils.isGranted(this, Manifest.permission.READ_PHONE_STATE)) {
			
			((TelephonyManager)getSystemService(TELEPHONY_SERVICE))
			.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE);
		}
	}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for connection availability.</p>
	 * 
	 * @param networkInfo
	 * 			{@link NetworkInfo} for the active data connection.
	 * 
	 * @since 1.1.0
	 */
	protected void onDataConnected() {}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for connection unavailability.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onDataDisconnected() {}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for connection establishment.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onDataConnecting() {}
	
	/**
	 * <p><b>Override</b> this method to handle callbacks for data connection suspension.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void onDataSuspended() {}
	
	/**
	 * See {@link NetworkUtils#getNetworkManager(Context)}.
	 */
	protected final NetworkManager network() {
		
		return NetworkUtils.getNetworkManager(getApplicationContext());
	}
}
