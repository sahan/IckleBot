package com.lonepulse.icklebot.injector;

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
 * <p>Identifies the <b>mode</b> of the injection. For example 
 * <b>Explicit</b> or <b>Implicit</b> injection.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum InjectionMode {
	
	/**
	 * <p>Identifies an <b>Explicit Injection</b>.</p>
	 * 
	 * <p><i>Explicit Injection</i> is used when an {@link InjectionActivity} 
	 * has all the target injection fields annotated with {@code  @Inject...} 
	 * type annotations <i>explicitly</i>.</p>
	 * 
	 * <p>If {@code @InjectAll} is used on the {@link InjectionActivity}, 
	 * explicit injection is <b>disabled</b>.</p>
	 * 
	 * @since 1.0.0
	 */
	EXPLICIT,
	
	/**
	 * <p>Identifies an <b>Implicit Injection</b>.</p>
	 * 
	 * <p><i>Implicit Injections</i> are enabled if {@code @InjectAll} is used 
	 * on the {@link InjectionActivity}. All {@link Field}s are <i>resolved</i> 
	 * to discover potential injection targets and the dependencies are injected.</p>
	 * 
	 * <p>A {@link Field} may exclude itself from injection by using the 
	 * {@code @Ignore} annotation - which will indicate that the field is 
	 * populated <i>manually</i> without dependency injection.</p> 
	 * 
	 * @since 1.0.0
	 */
	IMPLICIT;
}
