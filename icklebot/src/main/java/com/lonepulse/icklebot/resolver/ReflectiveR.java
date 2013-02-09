package com.lonepulse.icklebot.resolver;

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

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;


/**
 * <p>Gives access to constants in the generated <b>R</b> class 
 * via proxy.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class ReflectiveR {
	
	
	/**
	 * <p>Constructor visibility is restricted, proxy wrapper 
	 * instantiation is nonsensical.</p>
	 */
	private ReflectiveR() {
	}
	
	/**
	 * <p>Takes the <i>name</i> of an <b>ID</b> resource and 
	 * finds its {@code int} id in the generated <b>R</b> class.</p>
	 * 
	 * @param context
	 * 			the {@link Context} of the <b>R</b> class
	 * <br><br>
	 * @param resourceName
	 * 			the name of the resource
	 * <br><br>
	 * @return the {@code int} ID of the {@link String} resource
	 * <br><br>
	 * @since 1.0.0
	 */
	public static int id(Context context, String resourceName) {
		
		return ReflectiveR.getIdentifier("id", context, resourceName);
	}
	
	/**
	 * <p>Takes the <i>name</i> of a {@link String} resource and 
	 * finds its {@code int} id in the generated <b>R</b> class.</pS>
	 * 
	 * @param context
	 * 			the {@link Context} of the <b>R</b> class
	 * <br><br>
	 * @param resourceName
	 * 			the name of the resource
	 * <br><br>
	 * @return the {@code int} ID of the {@link String} resource
	 * <br><br>
	 * @since 1.0.0
	 */
	public static int string(Context context, String resourceName) {
		
		return ReflectiveR.getIdentifier("string", context, resourceName);
	}
	
	/**
	 * <p>Takes the <i>name</i> of a {@link Drawable} resource and 
	 * finds its {@code int} id in the generated <b>R</b> class.</p>
	 * 
	 * @param context
	 * 			the {@link Context} of the <b>R</b> class
	 * <br><br>
	 * @param resourceName
	 * 			the name of the resource
	 * <br><br>
	 * @return the {@code int} ID of the {@link String} resource
	 * <br><br>
	 * @since 1.0.0
	 */
	public static int drawable(Context context, String resourceName) {
		
		return ReflectiveR.getIdentifier("drawable", context, resourceName);
	}
	
	/**
	 * <p>Takes the <i>name</i> of an {@link Integer} resource and 
	 * finds its {@code int} id in the generated <b>R</b> class.</p>
	 * 
	 * @param context
	 * 			the {@link Context} of the <b>R</b> class
	 * <br><br>
	 * @param resourceName
	 * 			the name of the resource
	 * <br><br>
	 * @return the {@code int} ID of the {@link String} resource
	 * <br><br>
	 * @since 1.0.0
	 */
	public static int integer(Context context, String resourceName) {
		
		return ReflectiveR.getIdentifier("integer", context, resourceName);
	}
	
	/**
	 * <p>Looks up the identifier of a resource using 
	 * {@link Resources#getIdentifier(String, String, String)}.</p>
	 * 
	 * @param resourcetype
	 * 			the resource <i>type</i>; these include <i>string</i>, 
	 * 			<i>drawable</i> etc 
	 * 
	 * @param context
	 * 			the {@link Context} in which the members of the <b>R</b> 
	 * 			file is discovered
	 * 
	 * @param resourceName
	 * 			the name of the resource whose identifier is to be queried
	 * 
	 * @return the {@code int} identifier as specified in the <b>R</b> class
	 * 
	 * @since 1.0.0
	 */
	private static int getIdentifier(String resourcetype, Context context, String resourceName) {
		
		String resourceQualifier = context.getPackageName() + ":" + resourcetype + "/" + resourceName;
		
		return context.getResources().getIdentifier(resourceQualifier, null, null);
	}
}
