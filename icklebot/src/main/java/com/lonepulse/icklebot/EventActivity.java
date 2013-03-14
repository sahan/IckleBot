package com.lonepulse.icklebot;

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


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;
import com.lonepulse.icklebot.event.EventLinker;
import com.lonepulse.icklebot.event.EventLinkers;
import com.lonepulse.icklebot.profile.ProfileService;

/**
 * <p>This profile offers the linking of event listeners to {@link View}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class EventActivity extends InjectionActivity {


	/**
	 * <p>The {@link EventLinkers.Configuration} for this {@link IckleActivity}.</p>
	 * 
	 * @since 1.1.0
	 */
	private final EventLinker.Configuration LISTENER_CONFIGURATION;
	{
		LISTENER_CONFIGURATION = EventLinker.Configuration.getInstance(this);
	}
	
	
	/**
	 * <p>Performs <b>event listener linking</b> by invoking {@link #link()}.</p>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		if(ProfileService.getInstance().isActive(this, PROFILE.EVENT)) {
			
			long millis = System.currentTimeMillis();
	
			link();
	
			millis = System.currentTimeMillis() - millis;
			
			Log.d("INSTRUMENTATION:IckleEventActivity#link()", getClass().getSimpleName() + ": " + millis + "ms");
		}
	}

	/**
	 * <p>Drives the event listener linking for all supported listener types which include:</p>
	 * 
	 * <ol>
	 * 	<li>OnClickListeners</li>
	 * </ol>
	 * 
	 * @since 1.1.0
	 */
	private void link() {
		
		EventLinkers[] allLinkers = EventLinkers.values();
		
		for (EventLinkers listenerLinker : allLinkers) {
			
			listenerLinker.link(LISTENER_CONFIGURATION);
		}
	}
}

