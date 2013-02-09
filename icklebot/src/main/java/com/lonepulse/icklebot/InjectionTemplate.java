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

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

/**
 * <p>This contract specifies the different phases in the <i>injection</i> 
 * of resources into an {@link Activity}.</p>
 * 
 * @version 1.0.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class InjectionTemplate extends BoilerPlateActivity {

	
	/**
	 * {@inheritDoc}
	 * <br><br>
	 * <p>Performs <b>dependency injection</b> by invoking {@link #inject()}.</p>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		long millis = System.currentTimeMillis();

		inject();

		millis = System.currentTimeMillis() - millis;
		
		Log.d("INSTRUMENTATION:InjectionTemplate#inject()", getClass().getSimpleName() + ": " + millis + "ms");
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

		injectConfiguration();
		injectApplication();
		injectLayout();
		injectResources();
		injectServices();
		injectPojos();
	}
	
	/**
	 * <p>Injects the activity configuration such which includes 
	 * elements such as the <i>title</i> and features such as the 
	 * option to go <i>fullscreen</i>.</p>
	 * 
	 * @since 1.0.0
	 */
	protected abstract void injectConfiguration();
	
	/**
	 * <p>Injects the {@link Application} instance being used.</p>
	 * 
	 * @since 1.0.0
	 */
	protected abstract void injectApplication();
	
	/**
	 * <p>Injects the main <b>content view</b> of the activity.</p>
	 * 
	 * @since 1.0.0
	 */
	protected abstract void injectLayout();
	
	/**
	 * <p>Injects the android resources such as {@link View}s, 
	 * {@link Drawable}s and {@link Animation}s which are linked 
	 * via the generated <b>R</b> file.</p>
	 * 
	 * @since 1.0.0
	 */
	protected abstract void injectResources();
	
	/**
	 * <p>Injects any of the <b>System Services</b> declared 
	 * in {@link Context}.</p>
	 * 
	 * @since 1.0.0
	 */
	protected abstract void injectServices();
	
	/**
	 * <p>Injects the any <i>Plain Old Java Objects (POJOs)</i> 
	 * which are instantiated by default constructors.</p>
	 * 
	 * @since 1.0.0
	 */
	protected abstract void injectPojos();
}
