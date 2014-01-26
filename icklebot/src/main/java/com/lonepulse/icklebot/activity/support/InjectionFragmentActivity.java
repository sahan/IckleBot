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

import com.lonepulse.icklebot.activity.IckleActivity;
import com.lonepulse.icklebot.injector.InjectionUtils;
import com.lonepulse.icklebot.injector.InjectionProvider;

/**
 * <p>This profile performs explicit and implicit dependency injection.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class InjectionFragmentActivity extends ThreadingFragmentActivity {

	
	/**
	 * <p>The {@link InjectionProvider.Configuration} for this {@link IckleActivity}.</p>
	 * 
	 * @since 1.1.0
	 */
	private final InjectionProvider.Configuration INJECTOR_CONFIGURATION;
	{
		INJECTOR_CONFIGURATION = InjectionProvider.Configuration.newInstance(this);
	}
	
	
	/**
	 * <p>Performs <b>dependency injection</b> by invoking {@link #inject()}.</p>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		InjectionUtils.inject(INJECTOR_CONFIGURATION);
	}
}
