package com.lonepulse.icklebot.annotations;

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

import android.view.Window;

import com.lonepulse.icklebot.IckleActivity;

/**
 * <p>Used to annotate an {@link IckleActivity} with <i>Window 
 * Feature</i> metadata using the constants declared in {@link Window}.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WindowFeatures {

	/**
	 * <p>The array of {@code int}s containing the IDs of the 
	 * <i>window features</i> to be applied. These constants 
	 * can be found in {@link Window}.</p>
	 * 
	 * <p>Empty by default.</p>
	 * 
	 * @return the constant {@code int} IDs representing the  
	 * 		   {@link Window} features to be used.
	 * <br><br>
	 * @since 1.0.0
	 */
	int[] value() default {};
}
