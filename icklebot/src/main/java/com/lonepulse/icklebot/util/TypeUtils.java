package com.lonepulse.icklebot.util;

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

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.lonepulse.icklebot.activity.IckleActivity;

/**
 * <p>A utility class which performs some common operations involved 
 * in discovering <i>{@link Type} metadata</i>.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class TypeUtils {

	
	/**
	 * <p>Constructor visibility is restricted to prevent 
	 * nonsensical instantiation.</p>
	 */
	private TypeUtils() {
	}
	
	/**
	 * <p>Takes the target {@link IckleActivity} and finds the given 
	 * annotation (if any).</p>
	 * 
	 * @param injectorActivity
	 * 			the {@link IckleActivity} whose metadata is searched 
	 * <br><br>
	 * @param annotation
	 * 			the {@link Class} of the {@link Annotation} to look for
	 * <br><br>
	 * @since 1.0.0
	 */
	public static <T extends Annotation> T getAnnotation(Object context, Class<T> annotation) {
		
		Class<?> contextClass = context.getClass();
		
		if(contextClass.isAnnotationPresent(annotation)) {
			
			return contextClass.getAnnotation(annotation);
		}
		else {
			
			return null;
		}
	}
}
