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

import java.lang.reflect.Field;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.injector.InjectionProvider;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ContextUtils;
import com.lonepulse.icklebot.util.TypeUtils;

/**
 * <p>An implementation of {@link InjectionProvider} which is responsible 
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
class ExplicitLayoutInjector extends ExplicitInjectionProvider<Layout> {
	

	protected ExplicitLayoutInjector() {
		
		super(InjectionCategory.LAYOUT);
	}
	
	@Override
	public void run(Configuration config) {
		
		Object context = config.getTarget();
		
		if(ContextUtils.isActivity(context)) {
			
			Layout layout = TypeUtils.getAnnotation(context, Layout.class);
			
			if(layout != null) {
				
				ContextUtils.asActivity(context).setContentView(layout.value());
			}
		}
		
		super.run(config);
	}

	@Override
	protected Object inject(Configuration config, Layout annotation, Field field) {
		
		return LayoutInflater.from(config.getContext()).inflate(annotation.value(), null);
	}
}
