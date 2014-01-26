package com.lonepulse.icklebot.injector.implicit;

/*
 * #%L
 * IckleBot
 * %%
 * Copyright (C) 2013 - 2014 Lonepulse
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

import com.lonepulse.icklebot.injector.InjectionMode;
import com.lonepulse.icklebot.injector.InjectionProvider;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;
import com.lonepulse.icklebot.util.FieldUtils;

/**
 * <p>An abstract {@link InjectionProvider} which supports injection with {@link InjectionMode#IMPLICIT}.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.1
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
public abstract class ImplicitInjectionProvider implements InjectionProvider {

	
	private InjectionCategory injectionCategory;
	
	
	/**
	 * <p>Instantiates a new {@link ImplicitInjectionProvider} with the given {@link InjectionCategory}.</p>
	 *
	 * @since 1.2.4
	 */
	protected ImplicitInjectionProvider(InjectionCategory injectionCategory) {
		
		this.injectionCategory = injectionCategory;
	}
	
	/**
	 * <p>Delegates to {@link #inject(Configuration, Field)} for each field injection. Any 
	 * {@link RuntimeException}s are caught and logged with {@link Log#e(String, String, Throwable)} 
	 * with information field and context names.</p>
	 * 
	 * <p>Implementations that wish to alter the default behaviour may override this method and invoke 
	 * {@link #inject(Configuration, Field)} as they see fit.</p> 
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void run(Configuration config) {
		
		Object target = config.getTarget();
		Set<Field> fields = config.getInjectionTargets(injectionCategory);
		
		for (Field field : fields) {
			
			try {
				
				FieldUtils.setValue(target, field, inject(config, field));
			} 
			catch (Exception e) {
				
				Log.e(getClass().getName(), new StringBuilder("Injection failed for field <").append(field.getName())
					  .append("> on target <").append(target.getClass().getSimpleName()).append(">").toString(), e);
			}
		}
	}
	
	/**
	 * <p>This callback is invoked for each {@link Field} in the context which requests a dependency 
	 * served by this provider and every implementation should return this dependency for injection.</p>
	 *
	 * @param config
	 * 			the {@link InjectionProvider.Configuration} which supplies all the information for injection
	 * <br><br> 
	 * @param annotation
	 * 			the annotation on the {@link Field} which explicitly identifies the dependency request 
	 * 			or provides additional metadata required for the injection 
	 * <br><br> 
	 * @return the requested dependency which will be subsequently injected into the field
	 * @since 1.2.1
	 */
	protected abstract Object inject(Configuration config, Field field);
}
