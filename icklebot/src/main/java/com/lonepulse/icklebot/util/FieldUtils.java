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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.util.Log;

import com.lonepulse.icklebot.injector.DuplicateInjectionException;

/**
 * <p>A utility class which performs some common operations involved 
 * in discovering <i>{@link Field} metadata</i>.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class FieldUtils {

	
	/**
	 * <p>Constructor visibility is restricted to prevent 
	 * nonsensical instantiation.</p>
	 */
	private FieldUtils() {
	}
	
	
	/**
	 * <p>Takes the {@link Activity} and discovers which of 
	 * it's instance {@link Field}s are annotated with the given 
	 * annotation (represented by supplied {@link Class}).</p>
	 * 
	 * @param activity	
	 * 			the {@link Activity} whose {@link Field}s are 
	 * 			to be scanned
	 * <br><br>
	 * @param annotation
	 * 			the {@link Class} of the {@link Annotation} to look for
	 * <br><br>
	 * @return all the {@link Field}s which are <i>annotated</i> with 
	 * 			the passed {@link Annotation}
	 * <br><br>
	 * @since 1.0.0
	 */
	public static Set<Field> getAllFields(Activity activity,
										  Class<? extends Annotation> annotation) {
		
		Set<Field> annotatedFields = new HashSet<Field>();
		
		Field[] fields = activity.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			if(field.isAnnotationPresent(annotation)) {
				
				annotatedFields.add(field);
			}
		}
		
		return annotatedFields;
	}
	
	/**
	 * <p>Takes the target {@link Activity} and finds the <b>only</b> 
	 * field which is marked for injection with the given annotation.</p>
	 * 
	 * @param activity
	 * 			the {@link Activity} whose {@link Field}s are 
	 * 			to be scanned
	 * <br><br>
	 * @param annotation
	 * 			the {@link Class} of the {@link Annotation} to look for
	 * <br><br>
	 * @return the {@link Field} which is designated for injection
	 * <br><br>
	 * @throws DuplicateInjectionException
	 * 			when multiple fields exist with the given annotation
	 * <br><br>
	 * @since 1.0.0
	 */
	public static Field getUniqeField(Activity activity,
										Class<? extends Annotation> annotation) {
		
		Set<Field> fields = FieldUtils.getAllFields(activity, annotation);
		
		if(fields.isEmpty()) {
			
			return null;
		}
		else if(fields.size() > 1) {
			
			throw new DuplicateInjectionException(activity.getClass(), annotation);
		}
		
		return new ArrayList<Field>(fields).get(0);
	}
	
	/**
	 * <p>Takes a {@link Field} and retrieves it's value on the 
	 * given {@link Activity} if it's type is compatible with the 
	 * expected type referred to by the given {@link Class}.</p>
	 * 
	 * @param activity	
	 * 			the {@link Activity} whose {@link Field}s are 
	 * 			to be scanned
	 * <br><br>
	 * @param expectedType
	 * 			the {@link Class} of the field type which is <i>legal</i> 
	 * 			for the annotation
	 * <br><br>
	 * @param field
	 * 			the {@link Field} whose value is to be extracted
	 * <br><br>
	 * @return the value of the {@link Field}
	 * <br><br>
	 * @throws ClassCastException 
	 * 			when {@link Field} value cannot be cast to the 
	 * 			expected type
	 * <br><br>
	 * @since 1.0.0
	 */
	public static <T extends Object> T getFieldValue(Activity activity,
													 Class<T> expectedType,
													 Field field) {
		
		if (!field.isAccessible())  {
			
			StringBuilder msg = new StringBuilder();
			msg.append("Forcing accessibility for field ");
			msg.append(field.getName());
			msg.append(" on ");
			msg.append(activity.getClass().getName());
			msg.append(". ");
			
			Log.w(FieldUtils.class.getName(), msg.toString());
			
			field.setAccessible(true);
		}

		Object valueObject = null;
		
		try {
			
			valueObject = field.get(activity);
		} 
		catch (IllegalArgumentException iae) {
			
			StringBuilder msg = new StringBuilder();
			msg.append("Activity ");
			msg.append(activity.getClass().getName());
			msg.append(" is incompatible with the field ");
			msg.append(field.getName());
			msg.append(". ");
			
			Log.e(FieldUtils.class.getName(), msg.toString(), iae);
		} 
		catch (IllegalAccessException iae) {
			
			StringBuilder msg = new StringBuilder();
			msg.append("Field ");
			msg.append(field.getName());
			msg.append(" on ");
			msg.append(activity.getClass().getName());
			msg.append(" cannot be accessed. ");
			
			Log.e(FieldUtils.class.getName(), msg.toString(), iae);
		}
		
		return expectedType.cast(valueObject);
	}
}
