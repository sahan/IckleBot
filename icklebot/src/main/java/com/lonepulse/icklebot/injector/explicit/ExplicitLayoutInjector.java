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
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ContextUtils;
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
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {
		
		Object context = config.getContext();
		
		if(ContextUtils.isActivity(context)) {
			
			Layout layout = TypeUtils.getAnnotation(context, Layout.class);
			
			if(layout != null) 
				ContextUtils.asActivity(context).setContentView(layout.value());
		}
		
		Set<Field> fields = config.getInjectionTargets(InjectionCategory.LAYOUT);
		Context baseContext = ContextUtils.discover(context);
		
		for (Field field : fields) {
			
			try {
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				int id = field.getAnnotation(Layout.class).value();
				View layoutView = LayoutInflater.from(baseContext).inflate(id, null);
				
				field.set(context, layoutView);
			}
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Layout Injection Failed!", e);
			}
		}
	}
}
