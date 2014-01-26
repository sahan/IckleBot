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

import android.app.Application;

import com.lonepulse.icklebot.annotation.inject.InjectApplication;
import com.lonepulse.icklebot.injector.InjectionProvider;
import com.lonepulse.icklebot.injector.resolver.InjectionCategory;

/**
 * <p>An implementation of {@link InjectionProvider} which is responsible 
 * for injecting the {@link Application} instance being used.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitApplicationInjector extends ExplicitInjectionProvider<InjectApplication> {

	
	protected ExplicitApplicationInjector() {
		
		super(InjectionCategory.APPLICATION);
	}

	@Override
	protected Object inject(Configuration config, InjectApplication annotation, Field field) {
		
		return field.getType().cast(config.getContext().getApplicationContext());
	}
}
