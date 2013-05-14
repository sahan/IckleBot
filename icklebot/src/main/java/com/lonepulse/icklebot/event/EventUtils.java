package com.lonepulse.icklebot.event;

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


import android.util.Log;

import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;
import com.lonepulse.icklebot.profile.ProfileService;

/**
 * <p>This class offers a set of utility operations for event linking.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class EventUtils {
	
	
	/**
	 * <p>Constructor visibility. Instantiation is nonsensical.</p>
	 */
	private EventUtils() {}

	/**
	 * <p>Drives the event listener linking for all supported listener types which include:</p>
	 * 
	 * <ol>
	 * 	<li>OnClickListeners</li>
	 * </ol>
	 * 
	 * @param config
	 * 			the {@link EventLinker.Configuration} to be used 
	 */
	public static void link(EventLinker.Configuration config) {
		
		long millis = System.currentTimeMillis();

		if(ProfileService.getInstance(config.getContext()).isActive(config.getContext(), PROFILE.EVENT)) {
		
			EventLinkers[] allLinkers = EventLinkers.values();
			
			for (EventLinkers eventLinker : allLinkers) {
				
				eventLinker.link(config);
			}
		}
		
		millis = System.currentTimeMillis() - millis;
		
		Log.i("INSTRUMENTATION:IckleEventProfile#link()", 
			  EventUtils.class.getSimpleName() + ": " + millis + "ms");
	}
}
