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

import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;
import com.lonepulse.icklebot.profile.ProfileService;
import com.lonepulse.icklebot.state.StateService;

/**
 * <p>This profile offers state management features.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class StateActivity extends InjectionActivity {


	/**
	 * <p><b>Saves</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		
		if(ProfileService.getInstance().isActive(this, PROFILE.STATE)) {
		
			long millis = System.currentTimeMillis();
			
			StateService.getInstance().save(this, outState);
			
			millis = System.currentTimeMillis() - millis;
			
			Log.d("INSTRUMENTATION:IckleStateProfile#onSaveInstanceState", 
				  StateActivity.class.getClass().getSimpleName() + ": " + millis + "ms");
		}
	}
	
	/**
	 * <p><b>Restores</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		
		super.onRestoreInstanceState(savedInstanceState);
		
		if(ProfileService.getInstance().isActive(this, PROFILE.STATE)) {
		
			long millis = System.currentTimeMillis();
			
			StateService.getInstance().restore(this, savedInstanceState);
			
			millis = System.currentTimeMillis() - millis;
			
			Log.d("INSTRUMENTATION:IckleStateProfile#onRestoreInstanceState", 
					StateActivity.class.getClass().getSimpleName() + ": " + millis + "ms");
		}
	}
}
