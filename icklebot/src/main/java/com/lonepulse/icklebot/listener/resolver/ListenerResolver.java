package com.lonepulse.icklebot.listener.resolver;

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
 * <p>The contract by which instance methods are resolved to determine 
 * their {@link ListenerCategory}s.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface ListenerResolver {

	/**
	 * <p>Takes a {@link Method} and determines all applicable 
	 * {@link ListenerCategory}s to which it falls.</p>
	 * 
	 * @param method
	 * 			the {@link Method} whose {@link ListenerCategory}s 
	 * 			are to be resolved
	 * <br><br>
	 * @return the set if resolved {@link ListenerCategory}s
	 * <br><br>
	 * @since 1.1.0
	 */
	public abstract Set<ListenerCategory> resolve(Method method);
}
