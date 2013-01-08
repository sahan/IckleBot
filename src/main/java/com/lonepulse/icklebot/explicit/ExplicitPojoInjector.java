package com.lonepulse.icklebot.explicit;

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

import android.util.Log;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotations.InjectPojo;
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
public class ExplicitPojoInjector implements Injector {
	
	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link ExplicitPojoInjector}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final ExplicitPojoInjector INSTANCE; 

	static 
	{
		INSTANCE = new ExplicitPojoInjector();
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inject(Configuration config) {
		
		IckleActivity injectionActivity = config.getInjectionActivity();
		
		Set<Field> fields = config.getInjectionTargets(InjectionCategory.POJO);
		
		for (Field field : fields) {
			
			if(!field.isAccessible()) field.setAccessible(true);
			
			try {
				
				field.set(injectionActivity, field.getAnnotation(InjectPojo.class).value().newInstance());
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), "Injection Failed!", e);
			}
		}
	}
}
