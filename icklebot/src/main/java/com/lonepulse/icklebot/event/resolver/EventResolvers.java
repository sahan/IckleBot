package com.lonepulse.icklebot.event.resolver;

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

import java.lang.reflect.Method;
import java.util.Set;


/**
 * <p>A repository of {@link EventResolvers} for different <i>modes</i> 
 * of injection - such as <i>Implicit</i> and <i>Explicit<i> Injection Modes.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum EventResolvers implements EventResolver {

	/**
	 * <p>The default {@link EventResolver} which is responsible for 
	 * resolving listener linkers for a given event method.
	 *
	 * @since 1.1.0
	 */
	BASIC(new BasicEventResolver());
	
	
	/**
	 * <p>The instance of {@link EventResolver} used to resolve 
	 * the categories specified in {@link EventCategory}.</p>
	 * 
	 * @since 1.1.0
	 */
	private EventResolver listenerResolver;
	
	/**
	 * <p>A parameterized constructor which populates {@link #listenerResolver}.</p>
	 * 
	 * @param injectionResolver
	 * 			populates {@link #injectionResolver}
	 * <br><br>
	 * @since 1.1.0
	 */
	private EventResolvers(EventResolver listenerResolver) {
		
		this.listenerResolver = listenerResolver;
	}

	/**
	 * <p>Delegate method for {@link #listenerResolver#resolve(Method)}.
	 * 
	 * @since 1.1.0
	 */
	@Override
	public Set<EventCategory> resolve(Method method) {
		
		return listenerResolver.resolve(method);
	}
}
