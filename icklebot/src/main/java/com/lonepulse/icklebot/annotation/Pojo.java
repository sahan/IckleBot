package com.lonepulse.icklebot.annotation;

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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Marks a <b>Plain Old Java Object (POJO)</b>. Generally, POJOs must adhere 
 * to the 3 rules specified below:</p>
 * 
 * <ol>
 * 	<li>Non-parameterized (default) public constructor.</li>
 * 	<li>Private instance variables.</li>
 * 	<li>Public accessors and mutators for the private members.</li>
 * </ol>
 * 
 * <p>Of these 3 rules, the <i>1st</i> is the <b>only essential 
 * rule</b> for dependency injection.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Pojo {

	/**
	 * <p>The implementation {@link Class} of the <b>Plain 
	 * Old Java Object (POJO)</b> contract.</p>
	 * 
	 * @return the {@link Class} of the <i>POJO</i>
	 * <br><br>
	 * @since 1.0.0
	 */
	Class<? extends Object> value();
}
