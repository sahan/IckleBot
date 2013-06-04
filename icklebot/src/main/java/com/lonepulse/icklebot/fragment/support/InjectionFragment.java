package com.lonepulse.icklebot.fragment.support;

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
import android.support.v4.app.Fragment;
import android.view.View;

import com.lonepulse.icklebot.activity.IckleActivity;
import com.lonepulse.icklebot.injector.InjectionUtils;
import com.lonepulse.icklebot.injector.Injector;

/**
 * <p>This profile offers dependency injection features.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class InjectionFragment extends ThreadingFragment {
	
	
	/**
	 * <p>The {@link Injector.Configuration} for this {@link IckleActivity}.</p>
	 * 
	 * @since 1.1.0
	 */
	private Injector.Configuration INJECTOR_CONFIGURATION;

	
	/**
	 * <p>Initializes the {@link Injector.Configuration} for this {@link Fragment}.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		INJECTOR_CONFIGURATION = Injector.Configuration.newInstance(this);
	}
	
	/**
	 * <p>Performs <b>dependency injection</b> by invoking {@link InjectionUtils#inject()}.</p>
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		super.onViewCreated(view, savedInstanceState);
		InjectionUtils.inject(INJECTOR_CONFIGURATION);
	}
}
