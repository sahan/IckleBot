package com.lonepulse.icklebot.test.activity;

/*
 * #%L
 * IckleBot Integration Tests
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

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Stateful;
import com.lonepulse.icklebot.annotation.inject.Title;
import com.lonepulse.icklebot.annotation.profile.Profiles;
import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;
import com.lonepulse.icklebot.test.R;

/**
 * <p>An extension of {@link IckleActivity} which is used to test the 
 * <b>state management</b> features of IckleBot.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_explicit_injection)
@Title(R.string.ttl_act_explicit_injection)
@Profiles({PROFILE.EVENT, PROFILE.INJECTION, PROFILE.STATE, PROFILE.THREADING})
public class StateActivity extends IckleActivity {
	

	@Stateful
	int intMajorVersion = 1;
	
	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to it from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
	
	/**
	 * <p>Exposes {@link #onSaveInstanceState(Bundle)} and allows unit 
	 * tests to it from an external context.
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	
		super.onSaveInstanceState(outState);
	}
	
	/**
	 * <p>Exposes {@link #onRestoreInstanceState(Bundle)} and allows unit 
	 * tests to it from an external context.
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {

		super.onRestoreInstanceState(savedInstanceState);
	}
}
