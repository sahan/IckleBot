package com.lonepulse.icklebot.annotation.config;

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

import android.app.ActionBar;

/**
 * <p>Specifies the <b>title</b> to be used on the {@link ActionBar} 
 * or <b>Title Bar</b> (pre-ICS).</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Title {

	/**
	 * <p>The {@code integer} which identifies the <b>resource id</b> 
	 * of the title to be used.</p>
	 * 
	 * <p><b>NOTE</b>: the resource id is given precedence over the 
	 * {@link #text()}</p>.
	 * 
	 * @return the string resource id
	 * <br><br>
	 * @since 1.0.0
	 */
	int id() default 0;
	
	/**
	 * <p>The <i>text</i> which is to be used for the title.</p> 
	 * 
	 * @return the title text 
	 * <br><br>
	 * @since 1.0.0
	 */
	String text() default "";
}
