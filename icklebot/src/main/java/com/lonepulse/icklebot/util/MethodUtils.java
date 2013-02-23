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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;

/**
 * <p>A utility class which performs some common operations involved 
 * in discovering <i>{@link Method} metadata</i>.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class MethodUtils {

	
	/**
	 * <p>Constructor visibility is restricted to prevent 
	 * nonsensical instantiation.</p>
	 */
	private MethodUtils() {
	}
	
	/**
	 * <p>Takes the {@link Activity} and discovers which of it's 
	 * {@link Method}s are annotated with the given annotation 
	 * (represented by supplied {@link Class}).</p>
	 * 
	 * @param activity
	 * 			the {@link BoilerPlateActivity} whose {@link Field}s are 
	 * 			to be scanned
	 * <br><br>
	 * @param annotation
	 * 			the {@link Class} of the {@link Annotation} to look for
	 * <br><br>
	 * @return the {@link Set} of {@link Method}s annotated with the 
	 * 			given annotation
	 * <br><br>
	 * @since 1.0.0
	 */
	public static Set<Method> getAllMethods(Activity activity,
											Class<? extends Annotation> annotation) {
		
		Set<Method> annotatedMethods = new HashSet<Method>();
		
		Method[] methods = activity.getClass().getDeclaredMethods();
		
		for (Method method : methods) {
			
			if(method.isAnnotationPresent(annotation))
				annotatedMethods.add(method);
		}
		
		return annotatedMethods;
	}
}
