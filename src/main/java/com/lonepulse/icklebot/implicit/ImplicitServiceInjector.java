package com.lonepulse.icklebot.implicit;

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

import android.content.Context;
import android.util.Log;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.resolver.InjectionCategory;

/**
 * <p>An implementation of {@link Injector} which is responsible 
 * for injecting <i>POJOs</i>.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ImplicitServiceInjector implements Injector {

	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link ImplicitServiceInjector}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final ImplicitServiceInjector INSTANCE; 

	static 
	{
		INSTANCE = new ImplicitServiceInjector();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {

		IckleActivity injectionActivity = config.getIckleActivity();
		
		Set<Field> fields = config.getInjectionTargets(InjectionCategory.SERVICE);	
		Field[] contextFields = Context.class.getDeclaredFields();
		
		for (Field field : fields) {
		
			try {
				
				for (Field contextField : contextFields) {
					
					if(contextField.getName().equalsIgnoreCase(field.getName())) {
						
						String identifier = (String) contextField.get(null);
						
						if(!field.isAccessible()) field.setAccessible(true);
						
						field.set(injectionActivity, injectionActivity.getSystemService(identifier));
					}
				}
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
}
