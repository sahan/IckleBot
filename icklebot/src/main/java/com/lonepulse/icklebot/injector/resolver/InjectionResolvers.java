package com.lonepulse.icklebot.injector.resolver;

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

import com.lonepulse.icklebot.event.resolver.EventCategory;
import com.lonepulse.icklebot.event.resolver.EventResolver;
import com.lonepulse.icklebot.event.resolver.EventResolvers;


/**
 * <p>A repository of {@link EventResolvers} for different <i>modes</i> 
 * of injection - such as <i>Implicit</i> and <i>Explicit<i> Injection Modes.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum InjectionResolvers implements InjectionResolver {

	/**
	 * <p>The {@link EventResolver} responsible for resolving 
	 * <i>Implicit Injection</i>.</p>
	 *
	 * @since 1.0.0
	 */
	IMPLICIT(new ImplicitInjectionResolver()),
	
	/**
	 * <p>The {@link EventResolver} responsible for resolving 
	 * <i>Implicit Injection</i>.</p>
	 *
	 * @since 1.0.0
	 */
	EXPLICIT(new ExplicitInjectionResolver());
	
	
	/**
	 * <p>The instance of {@link EventResolver} used to resolve 
	 * the categories specified in {@link EventCategory}.</p>
	 * 
	 * @since 1.0.0
	 */
	private InjectionResolver injectionResolver;
	
	/**
	 * <p>A parameterized constructor which populates {@link #injectionResolver}.</p>
	 * 
	 * @param injectionResolver
	 * 			populates {@link #injectionResolver}
	 * <br><br>
	 * @since 1.0.0
	 */
	private InjectionResolvers(InjectionResolver injectionResolver) {
		
		this.injectionResolver = injectionResolver;
	}

	/**
	 * <p>Delegate method for {@link #injectionResolver#resolve(Object, Field)}.
	 * 
	 * @since 1.0.0
	 */
	@Override
	public InjectionCategory resolve(Object context, Field field) {
		
		return injectionResolver.resolve(context, field);
	}
}
