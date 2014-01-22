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

import com.lonepulse.icklebot.injector.InjectionMode;

/**
 * <p>{@link InjectionResolver}s for each {@link InjectionMode} which discovers the {@link InjectionCategory} 
 * of a particular injection binding.</p>
 * 
 * @version 1.2.0
 * <br><br>
 * @since 1.0.0
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
public enum InjectionResolvers implements InjectionResolver {

	/**
	 * <p>The {@link InjectionResolver} responsible for <i>Implicit Injection</i>.</p>
	 *
	 * @since 1.0.0
	 */
	IMPLICIT(new ImplicitInjectionResolver()),
	
	/**
	 * <p>The {@link InjectionResolver} responsible <i>Explicit Injection</i>.</p>
	 *
	 * @since 1.0.0
	 */
	EXPLICIT(new ExplicitInjectionResolver());
	
	
	/**
	 * <p>Retrieves the {@link InjectionResolver} responsible for managing the given {@link InjectionMode}.</p>
	 *
	 * @param injectionMode
	 * 			the {@link InjectionMode} for a context whose managing {@link InjectionResolver} is returned
	 * <br><br>
	 * @return the {@link InjectionResolver} which manages the given {@link InjectionMode}
	 * <br><br>
	 * @since 1.2.1
	 */
	public static InjectionResolver get(InjectionMode injectionMode) {
		
		return injectionMode == InjectionMode.EXPLICIT? 
				InjectionResolvers.EXPLICIT :InjectionResolvers.IMPLICIT;
	}
	

	private InjectionResolver injectionResolver;
	
	
	private InjectionResolvers(InjectionResolver injectionResolver) {
		
		this.injectionResolver = injectionResolver;
	}

	/**
	 * <p>See {@link InjectionResolver#resolve(Object, Field)}.</p>
	 * 
	 * @since 1.0.0
	 */
	@Override
	public InjectionCategory resolve(Object context, Field field) {
		
		return injectionResolver.resolve(context, field);
	}
}
