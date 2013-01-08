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

import com.lonepulse.icklebot.explicit.ExplicitInjectors;
import com.lonepulse.icklebot.implicit.ImplicitInjectors;
import com.lonepulse.icklebot.injector.InjectionMode;
import com.lonepulse.icklebot.injector.Injector;

/**
 * <p>All activities that wish to be <i>wired</i> by <b>dependency injection</b> 
 * should extend this activity.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class IckleActivity extends InjectionTemplate {

	
	/**
	 * <p>The {@link Injector.Configuration} for this {@link IckleActivity}.</p>
	 * 
	 * @since 1.0.0
	 */
	protected final Injector.Configuration CONFIGURATION;
	{
		CONFIGURATION = Injector.Configuration.getInstance(this);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void injectConfiguration() {
	
		ExplicitInjectors.CONFIGURATION.inject(CONFIGURATION);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void injectApplication() {
	
		if(CONFIGURATION.getInjectionMode().equals(InjectionMode.IMPLICIT)) {
			
			ImplicitInjectors.APPLICATION.inject(CONFIGURATION);
		}
		else {
			
			ExplicitInjectors.APPLICATION.inject(CONFIGURATION);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void injectLayout() {
		
		ExplicitInjectors.LAYOUT.inject(CONFIGURATION);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void injectResources() {
		
		if(CONFIGURATION.getInjectionMode().equals(InjectionMode.IMPLICIT)) {
			
			ImplicitInjectors.RESOURCES.inject(CONFIGURATION);
		}
		else {
			
			ExplicitInjectors.RESOURCES.inject(CONFIGURATION);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void injectServices() {
	
		if(CONFIGURATION.getInjectionMode().equals(InjectionMode.IMPLICIT)) {
			
			ImplicitInjectors.SERVICES.inject(CONFIGURATION);
		}
		else {
			
			ExplicitInjectors.SERVICES.inject(CONFIGURATION);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void injectPojos() {
		
		if(CONFIGURATION.getInjectionMode().equals(InjectionMode.IMPLICIT)) {
			
			ImplicitInjectors.POJOS.inject(CONFIGURATION);
		}
		else {
			
			ExplicitInjectors.POJOS.inject(CONFIGURATION);
		}
	}
}
