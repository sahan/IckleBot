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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

import android.app.Activity;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.InjectDrawable;
import com.lonepulse.icklebot.annotation.inject.InjectInteger;
import com.lonepulse.icklebot.annotation.inject.InjectString;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting {@link InjectView}s.</p>
 * 
 * @version 1.0.0 
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitResourceInjector implements Injector {
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {
		
		Activity injectionActivity = config.getActivity();
		
		injectResource(injectionActivity, 
					   config.getInjectionTargets(InjectionCategory.RESOURCE_VIEW), 
					   InjectView.class);
	
		injectResource(injectionActivity, 
					   config.getInjectionTargets(InjectionCategory.RESOURCE_STRING), 
					   InjectString.class);
		
		injectResource(injectionActivity, 
					   config.getInjectionTargets(InjectionCategory.RESOURCE_DRAWABLE), 
					   InjectDrawable.class);
		
		injectResource(injectionActivity, 
					   config.getInjectionTargets(InjectionCategory.RESOURCE_INTEGER), 
					   InjectInteger.class);
	}
	
	/**
	 * <p>Injects all {@link String} resources identified by the 
	 * {@link InjectString} annotation.</p>
	 * 
	 * @param injectorActivity
	 * 			the {@link Activity} which is the subject of 
	 * 			dependency injection
	 * <br><br>			
	 * @param fields
	 * 			the {@link Field}s which are annotated with {@link Resource}
	 * <br><br>
	 * @since 1.0.0
	 */
	private void injectResource(Activity injectorActivity, Set<Field> fields, 
								Class<? extends Annotation> resourceAnnotation) {
		
		for (Field field : fields) {
			
			if(!field.isAccessible()) field.setAccessible(true);

			try {
					
				if(resourceAnnotation.equals(InjectView.class)) {
					
					field.set(injectorActivity, 
							  injectorActivity.findViewById(field.getAnnotation(InjectView.class).value()));
				}
				else if(resourceAnnotation.equals(InjectString.class)) {
					
					field.set(injectorActivity, 
							  injectorActivity.getString(field.getAnnotation(InjectString.class).value()));
				}
				else if(resourceAnnotation.equals(InjectDrawable.class)) {
						
					field.set(injectorActivity, 
						      injectorActivity.getResources().getDrawable(field.getAnnotation(InjectDrawable.class).value()));
				}
				else if(resourceAnnotation.equals(InjectInteger.class)) {
					
					field.set(injectorActivity, 
							  injectorActivity.getResources().getInteger(field.getAnnotation(InjectInteger.class).value()));
				}
			} 
			catch (Exception e) {
					
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
}
