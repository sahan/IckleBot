package com.lonepulse.icklebot.activity.support;

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

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.lonepulse.icklebot.annotation.profile.Profile;

/**
 * <p>A {@link FragmentActivity} that wish to leverage IckleBot's features 
 * should extend this activity.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class IckleFragmentActivity extends EventFragmentActivity {

	/**
	 * <p>This callback is executed when the {@link IckleFragmentActivity} is being 
	 * created. It initializes IckleBot's features and cache each {@link Profile} 
	 * configuration for use.
	 * 
	 * @since 1.1.0
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		long millis = System.currentTimeMillis();
		
		super.onCreate(savedInstanceState);

		millis = System.currentTimeMillis() - millis;
		
		Log.i("INSTRUMENTATION:IckleFragmentActivity", getClass().getSimpleName() + ": " + millis + "ms");
	}
}
