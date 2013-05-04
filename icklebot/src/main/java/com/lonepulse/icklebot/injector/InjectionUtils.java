package com.lonepulse.icklebot.injector;

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


import android.util.Log;

import com.lonepulse.icklebot.injector.explicit.ExplicitInjectors;
import com.lonepulse.icklebot.injector.implicit.ImplicitInjectors;
import com.lonepulse.icklebot.task.TaskManagers;

/**
 * <p>This class offers a set of utility operations for dependency injection.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class InjectionUtils {
	
	
	/**
	 * <p>Constructor visibility. Instantiation is nonsensical.</p>
	 */
	private InjectionUtils() {}
	
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
	public static void inject(Injector.Configuration config) {

		long millis = System.currentTimeMillis();
		
		ExplicitInjectors.CONFIGURATION.inject(config);
		ExplicitInjectors.LAYOUT.inject(config);
		
		if(config.getInjectionMode().equals(InjectionMode.EXPLICIT)) {
			
			InjectionUtils.injectExplicitly(config);
		}
		else {
			
			InjectionUtils.injectImplicitly(config);
		}
		
		millis = System.currentTimeMillis() - millis;
		
		Log.i("INSTRUMENTATION:IckleInjectionProfile#inject()", 
				InjectionUtils.class.getClass().getSimpleName() + ": " + millis + "ms");
	}
	
	/**
	 * <p>Performs <b>Explicit Injection</b> using the set of 
	 * {@link Injector}s at {@link ExplicitInjectors}.
	 * 
	 * @param config
	 * 			the {@link Injector.Configuration} to be used
	 */
	private static void injectExplicitly(Injector.Configuration config) {
		
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
	private static void injectImplicitly(Injector.Configuration config) {
		
		ImplicitInjectors.APPLICATION.inject(config);		
		ImplicitInjectors.RESOURCES.inject(config);
		ImplicitInjectors.SERVICES.inject(config);
		ImplicitInjectors.POJOS.inject(config);
	}
}
