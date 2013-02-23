package com.lonepulse.icklebot.support;

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
import android.os.Bundle;
import android.util.Log;

import com.lonepulse.icklebot.listener.ListenerLinker;
import com.lonepulse.icklebot.listener.ListenerLinkers;

/**
 * <p>This activity can be extended to isolate and leverage the listener 
 * linking features of IckleBot during the {@link Activity#onCreate(Bundle)} 
 * life-cycle callback.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class ListenerSupportActivity extends Activity {


	/**
	 * <p>The {@link ListenerLinkers.Configuration} for this {@link Activity}.</p>
	 * 
	 * @since 1.1.0
	 */
	private final ListenerLinker.Configuration LISTENER_CONFIGURATION;
	{
		LISTENER_CONFIGURATION = ListenerLinker.Configuration.getInstance(this);
	}
	
	
	/**
	 * <p>Performs <b>event listener linking</b> by invoking {@link #link()}.</p>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		long millis = System.currentTimeMillis();

		link();

		millis = System.currentTimeMillis() - millis;
		
		Log.d("INSTRUMENTATION:ListenerSupportActivity#link()", getClass().getSimpleName() + ": " + millis + "ms");
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
		
		ListenerLinkers.CLICK.link(LISTENER_CONFIGURATION);
	}
}
