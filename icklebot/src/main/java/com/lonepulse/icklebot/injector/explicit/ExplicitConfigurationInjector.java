package com.lonepulse.icklebot.injector.explicit;

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
import android.view.Window;
import android.view.WindowManager;

import com.lonepulse.icklebot.annotation.inject.Fullscreen;
import com.lonepulse.icklebot.annotation.inject.Title;
import com.lonepulse.icklebot.annotation.inject.WindowFeatures;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.Injector.Configuration;
import com.lonepulse.icklebot.util.TypeUtils;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting the <i>title</i> of an {@link Activity} using 
 * the {@link Configuration} metadata.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitConfigurationInjector implements Injector {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {
		
		Activity injectionActivity = config.getActivity();
		
		configureTitle(injectionActivity);
		configureWindowFeatures(injectionActivity);
		configureFullscreen(injectionActivity);
	}
	
	/**
	 * <p>Configures the {@link Activity} to use a <i>title</i> 
	 * specified via {@link Title}.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link Activity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @since 1.0.0			
	 */
	private void configureTitle(Activity injectionActivity) {
		
		Title title = TypeUtils.getAnnotation(injectionActivity, Title.class);
		
		if(title != null) {
			
			if(title.id() != 0)
				injectionActivity.setTitle(title.id());
			
			else if(!title.text().equals(""))
				injectionActivity.setTitle(title.text());
		}
	}
	
	/**
	 * <p>Configures the {@link Activity} to take up the entire screen 
	 * space by removing the <b>Status Bar</b>.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link Activity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @since 1.0.0			
	 */
	private void configureFullscreen(Activity injectionActivity) {
		
		if(injectionActivity.getClass().isAnnotationPresent(Fullscreen.class)) {
			
			injectionActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
			injectionActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
												   WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}
	
	/**
	 * <p>Configures the {@link Activity} to use the <i>Window 
	 * Features</i> specified via {@link WindowFeatures}.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link Activity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @since 1.0.0			
	 */
	private void configureWindowFeatures(Activity injectionActivity) {
		
		WindowFeatures windowFeatures = TypeUtils.getAnnotation(injectionActivity, WindowFeatures.class);
		
		if(windowFeatures != null) {
			
			int[] features = windowFeatures.value();
			
			for (int feature : features)
				injectionActivity.requestWindowFeature(feature);
		}
	}
}
