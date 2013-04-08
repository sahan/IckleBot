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
import com.lonepulse.icklebot.injector.InjectionMode;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.explicit.ExplicitInjectors;
import com.lonepulse.icklebot.injector.implicit.ImplicitInjectors;
import com.lonepulse.icklebot.profile.ProfileService;
import com.lonepulse.icklebot.task.TaskManagers;

/**
 * <p>This profile offers dependency injection features.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class InjectionActivity extends ThreadingActivity {

	
	/**
	 * <p>The {@link Injector.Configuration} for this {@link IckleActivity}.</p>
	 * 
	 * @since 1.1.0
	 */
	private final Injector.Configuration INJECTOR_CONFIGURATION;
	{
		INJECTOR_CONFIGURATION = Injector.Configuration.getInstance(this);
	}
	
	
	/**
	 * <p>Performs <b>dependency injection</b> by invoking {@link #inject()}.</p>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		if(ProfileService.getInstance(getApplicationContext()).isActive(this, PROFILE.INJECTION)) {
	
			InjectionActivity.inject(INJECTOR_CONFIGURATION);
		}
	}
	
	/**
	 * <p>Drives the injection by executing the individual phases in the 
	 * following order:</p>
	 * 
	 * <ol>
	 * 	<li>Configuration Injection</li>
	 * 	<li>Application Injection</li>
	 * 	<li>Layout Injection</li>
	 * 	<li>Resource Injection</li>
	 * 	<li>Service Injection</li>
	 * 	<li>POJO Injection</li>
	 * </ol>
	 */
	static void inject(Injector.Configuration config) {

		long millis = System.currentTimeMillis();
		
		ExplicitInjectors.CONFIGURATION.inject(config);
		ExplicitInjectors.LAYOUT.inject(config);
		
		if(config.getInjectionMode().equals(InjectionMode.EXPLICIT)) {
			
			InjectionActivity.injectExplicitly(config);
		}
		else {
			
			InjectionActivity.injectImplicitly(config);
		}
		
		millis = System.currentTimeMillis() - millis;
		
		Log.d("INSTRUMENTATION:IckleInjectionProfile#inject()", 
			  InjectionActivity.class.getClass().getSimpleName() + ": " + millis + "ms");
	}
	
	/**
	 * <p>Performs <b>Explicit Injection</b> using the set of 
	 * {@link Injector}s at {@link ExplicitInjectors}.
	 * 
	 * @param config
	 * 			the {@link Injector.Configuration} to be used
	 */
	static void injectExplicitly(Injector.Configuration config) {
		
		ExplicitInjectors.APPLICATION.inject(config);
		ExplicitInjectors.RESOURCES.inject(config);
		ExplicitInjectors.SERVICES.inject(config);
		ExplicitInjectors.POJOS.inject(config);
	}
	
	/**
	 * <p>Performs <b>Implicit Injection</b> using the set of 
	 * {@link Injector}s at {@link TaskManagers}.
	 * 
	 * @param config
	 * 			the {@link Injector.Configuration} to be used
	 */
	static void injectImplicitly(Injector.Configuration config) {
		
		ImplicitInjectors.APPLICATION.inject(config);		
		ImplicitInjectors.RESOURCES.inject(config);
		ImplicitInjectors.SERVICES.inject(config);
		ImplicitInjectors.POJOS.inject(config);
	}
}
