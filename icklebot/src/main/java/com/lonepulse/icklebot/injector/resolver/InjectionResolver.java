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

/**
 * <p>The contract by which instance variables are resolved to determine 
 * their {@link InjectionCategory}.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface InjectionResolver {

	/**
	 * <p>Takes a {@link Field} and determines the best suited 
	 * {@link InjectionCategory} to which it falls.</p>
	 * 
	 * @param context
	 * 			the context from which the {@link InjectionCategory} 
	 * 			is to be resolved
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is 
	 * 			to be resolved
	 * <br><br>
	 * @return the resolved {@link InjectionCategory}
	 * <br><br>
	 * @since 1.1.1
	 */
	public abstract InjectionCategory resolve(Object context, Field field);
}
