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
import android.view.View;

import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.util.TypeUtils;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting the <i>layout</i> of an {@link Activity} using 
 * the {@link Layout} metadata.</p> 
 * 
 * <p>The layout <i>resource id</i> or <i>root {@link View}</i> should 
 * be marked for injection via {@link Layout}.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitLayoutInjector implements Injector {
	
	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link ExplicitLayoutInjector}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final ExplicitLayoutInjector INSTANCE; 

	static 
	{
		INSTANCE = new ExplicitLayoutInjector();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {
		
		Activity injectionActivity = config.getActivity();
		
		Layout layout = TypeUtils.getAnnotation(injectionActivity, Layout.class);
		
		if(layout != null) {
			
			injectionActivity.setContentView(layout.value());
		}
	}
}
