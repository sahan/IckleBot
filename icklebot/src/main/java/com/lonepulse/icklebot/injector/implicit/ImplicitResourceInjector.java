package com.lonepulse.icklebot.injector.implicit;

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
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.ReflectiveR;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting {@link InjectView}s.</p>
 * 
 * @version 1.0.0 
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ImplicitResourceInjector implements Injector {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {

		Activity injectionActivity = config.getActivity();

		injectViewResources(injectionActivity, config.getInjectionTargets(InjectionCategory.RESOURCE_VIEW));
		injectStringResources(injectionActivity, config.getInjectionTargets(InjectionCategory.RESOURCE_STRING));
		injectDrawableResources(injectionActivity, config.getInjectionTargets(InjectionCategory.RESOURCE_DRAWABLE));
		injectIntegerResources(injectionActivity, config.getInjectionTargets(InjectionCategory.RESOURCE_INTEGER));
	}
	
	/**
	 * <p>Injects a {@link View} resource <b>implicitly</b>.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link Activity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @param field
	 * 			the field subjected to injection
	 * <br><br>
	 * @throws Exception
	 * 			if any error occurs with reflective field access
	 * <br><br>
	 * @since 1.0.0
	 */
	private void injectViewResources(Activity injectionActivity, Set<Field> fields) {
		
		for (Field field : fields) {
		
			try {
				
				int id = ReflectiveR.id(injectionActivity, field.getName());
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				field.set(injectionActivity, injectionActivity.findViewById(id));
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
	
	/**
	 * <p>Injects a {@link String} resource <b>implicitly</b>.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link Activity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @param field
	 * 			the field subjected to injection
	 * <br><br>
	 * @throws Exception
	 * 			if any error occurs with reflective field access
	 * 
	 * @since 1.0.0
	 */
	private void injectStringResources(Activity injectionActivity, Set<Field> fields) {
		
		for (Field field : fields) {
		
			try {
				
				int id = ReflectiveR.string(injectionActivity, field.getName());
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				field.set(injectionActivity, injectionActivity.getString(id));
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
	
	/**
	 * <p>Injects a {@link Drawable} resource <b>implicitly</b>.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link Activity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @param field
	 * 			the field subjected to injection
	 * <br><br>
	 * @throws Exception
	 * 			if any error occurs with reflective field access
	 * <br><br>
	 * @since 1.0.0
	 */
	private void injectDrawableResources(Activity injectionActivity, Set<Field> fields) {
		
		for (Field field : fields) {
		
			try {
				
				int id = ReflectiveR.drawable(injectionActivity, field.getName());
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				field.set(injectionActivity, injectionActivity.getResources().getDrawable(id));
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
	
	/**
	 * <p>Injects an {@link Integer} resource <b>implicitly</b>.</p>
	 * 
	 * @param injectionActivity
	 * 			the {@link Activity} which is the subject 
	 * 			of dependency injection
	 * <br><br>
	 * @param field
	 * 			the field subjected to injection
	 * <br><br>
	 * @throws Exception
	 * 			if any error occurs with reflective field access
	 * <br><br>
	 * @since 1.0.0
	 */
	private void injectIntegerResources(Activity injectionActivity, Set<Field> fields) {
		
		for (Field field : fields) {
			
			try {
				
				int id = ReflectiveR.integer(injectionActivity, field.getName());
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				field.set(injectionActivity, injectionActivity.getResources().getInteger(id));
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
}
