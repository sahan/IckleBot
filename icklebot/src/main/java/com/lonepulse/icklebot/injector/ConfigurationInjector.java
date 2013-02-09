package com.lonepulse.icklebot.injector;

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

import android.view.Window;
import android.view.WindowManager;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.Fullscreen;
import com.lonepulse.icklebot.annotation.Title;
import com.lonepulse.icklebot.annotation.WindowFeatures;
import com.lonepulse.icklebot.util.TypeUtils;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting the <i>title</i> of an {@link IckleActivity} using 
 * the {@link Configuration} metadata.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ConfigurationInjector implements Injector {

	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link ConfigurationInjector}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final ConfigurationInjector INSTANCE; 

	static 
	{
		INSTANCE = new ConfigurationInjector();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {
		
		IckleActivity injectionActivity = config.getIckleActivity();
		
		configureTitle(injectionActivity);
		configureWindowFeatures(injectionActivity);
		configureFullscreen(injectionActivity);
	}
	
	/**
	 * <p>Configures the {@link IckleActivity} to use a <i>title</i> 
	 * specified via {@link Title}.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link IckleActivity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @since 1.0.0			
	 */
	private void configureTitle(IckleActivity injectionActivity) {
		
		Title title = TypeUtils.getAnnotation(injectionActivity, Title.class);
		
		if(title != null) {
			
			if(title.id() != 0)
				injectionActivity.setTitle(title.id());
			
			else if(!title.text().equals(""))
				injectionActivity.setTitle(title.text());
		}
	}
	
	/**
	 * <p>Configures the {@link IckleActivity} to take up the entire screen 
	 * space by removing the <b>Status Bar</b>.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link IckleActivity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @since 1.0.0			
	 */
	private void configureFullscreen(IckleActivity injectionActivity) {
		
		if(injectionActivity.getClass().isAnnotationPresent(Fullscreen.class)) {
			
			injectionActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
			injectionActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}
	
	/**
	 * <p>Configures the {@link IckleActivity} to use the <i>Window 
	 * Features</i> specified via {@link WindowFeatures}.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link IckleActivity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @since 1.0.0			
	 */
	private void configureWindowFeatures(IckleActivity injectionActivity) {
		
		WindowFeatures windowFeatures = TypeUtils.getAnnotation(injectionActivity, WindowFeatures.class);
		
		if(windowFeatures != null) {
			
			int[] features = windowFeatures.value();
			
			for (int feature : features)
				injectionActivity.requestWindowFeature(feature);
		}
	}
}
