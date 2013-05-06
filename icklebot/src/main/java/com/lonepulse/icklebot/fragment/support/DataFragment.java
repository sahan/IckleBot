package com.lonepulse.icklebot.fragment.support;

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


import android.content.Context;
import android.support.v4.app.Fragment;

import com.lonepulse.icklebot.network.NetworkManager;
import com.lonepulse.icklebot.network.NetworkUtils;
import com.lonepulse.icklebot.state.StateUtils;

/**
 * <p>This profile detects changes in the data connection and provides 
 * callbacks to handle them. In addition it can be used to discover 
 * additional network information via {@link DataSupportFragment#network()}.</p>
 * 
 * <p>This profile requires the following permission:
 * <ul>
 *  <li>READ_PHONE_STATE: to register for data state changes.</li>
 * </ul>
 * </p>
 *  
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class DataFragment extends Fragment {

	/**
	 * See {@link StateUtils#getNetworkManager(Context)}.
	 */
	protected final NetworkManager network() {
		
		return NetworkUtils.getNetworkManager(getActivity().getApplicationContext());
	}
}
