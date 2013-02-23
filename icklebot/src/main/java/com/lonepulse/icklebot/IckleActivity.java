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

import android.os.Bundle;
import android.util.Log;

/**
 * <p>All activities that wish to be <i>wired</i> by <b>dependency injection</b> 
 * should extend this activity.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class IckleActivity extends ListenerActivity {

	/**
	 * <p>This callback is executed when the {@link IckleActivity} is being 
	 * created. It invokes the dependency injection and event listener linking 
	 * via the {@link InjectionSupportActivity} and the {@link ListenerSupportActivity}.</p>
	 * 
	 * <p>See {@link InjectionSupportActivity#onCreate(Bundle)}.</p> 
	 * <p>See {@link ListenerSupportActivity#onCreate(Bundle)}.</p>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		long millis = System.currentTimeMillis();
		
		super.onCreate(savedInstanceState);

		millis = System.currentTimeMillis() - millis;
		
		Log.d("INSTRUMENTATION:IckleActivity", getClass().getSimpleName() + ": " + millis + "ms");
	}
}
