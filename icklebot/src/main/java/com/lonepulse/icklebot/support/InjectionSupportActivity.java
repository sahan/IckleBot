package com.lonepulse.icklebot.support;

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

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.injector.InjectionMode;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.explicit.ExplicitInjectors;
import com.lonepulse.icklebot.injector.implicit.ImplicitInjectors;

/**
 * <p>This activity can be extended to isolate and leverage the dependency 
 * injection features of IckleBot during the {@link Activity#onCreate(Bundle)} 
 * life-cycle callback.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class InjectionSupportActivity extends Activity {

	
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
		
		long millis = System.currentTimeMillis();

		inject();

		millis = System.currentTimeMillis() - millis;
		
		Log.d("INSTRUMENTATION:InjectionSupportActivity#inject()", getClass().getSimpleName() + ": " + millis + "ms");
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
	private void inject() {

		ExplicitInjectors.CONFIGURATION.inject(INJECTOR_CONFIGURATION);
		ExplicitInjectors.LAYOUT.inject(INJECTOR_CONFIGURATION);
		
		if(INJECTOR_CONFIGURATION.getInjectionMode().equals(InjectionMode.EXPLICIT)) {
			
			injectExplicitly();
		}
		else {
			
			injectImplicitly();
		}
	}
	
	/**
	 * <p>Performs <b>Explicit Injection</b> using the set of 
	 * {@link Injector}s at {@link ExplicitInjectors}.
	 */
	private void injectExplicitly() {
		
		ExplicitInjectors.APPLICATION.inject(INJECTOR_CONFIGURATION);
		ExplicitInjectors.RESOURCES.inject(INJECTOR_CONFIGURATION);
		ExplicitInjectors.SERVICES.inject(INJECTOR_CONFIGURATION);
		ExplicitInjectors.POJOS.inject(INJECTOR_CONFIGURATION);
	}
	
	/**
	 * <p>Performs <b>Implicit Injection</b> using the set of 
	 * {@link Injector}s at {@link ImplicitInjectors}.
	 */
	private void injectImplicitly() {
		
		ImplicitInjectors.APPLICATION.inject(INJECTOR_CONFIGURATION);		
		ImplicitInjectors.RESOURCES.inject(INJECTOR_CONFIGURATION);
		ImplicitInjectors.SERVICES.inject(INJECTOR_CONFIGURATION);
		ImplicitInjectors.POJOS.inject(INJECTOR_CONFIGURATION);
	}
}
