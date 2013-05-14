package com.lonepulse.icklebot.util;

/*
 * #%L
 * IckleBot Library
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


import java.util.Set;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * <p>This utility class offers a set of services which can be used to query 
 * and manage manifest permissions.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class PermissionUtils {

	
	/**
	 * <p>Visibility restricted. Instantiation is nonsensical. 
	 */
	private PermissionUtils() {}
	
	
	/**
	 * <p>Takes a set of permissions and determines if all of them are granted.</p>
	 * 
	 * <p>Use {@link Manifest.permission} to reference permission constants.</p>
	 * 
	 * @param context
	 * 			the permissible {@link Context}
	 * 
	 * @param permissions
	 *			the set of {@link permission}s to test 
	 *
	 * @return {@code true} if all the permissions are granted
	 * 
	 * @since 1.1.0
	 */
	public static boolean areAllGranted(Object context, Set<String> permissions) {
		
		for (String permission : permissions) {
			
			if(ContextUtils.discover(context).checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
				
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * <p>Takes a set of permissions and determines if one or more of them are granted.</p>
	 * 
	 * <p>Use {@link Manifest.permission} to reference permission constants.</p>
	 * 
	 * @param context
	 * 			the permissible {@link Context}
	 * 
	 * @param permissions
	 *			the set of {@link permission}s to test 
	 *
	 * @return {@code true} if any of the permissions are granted
	 * 
	 * @since 1.1.0
	 */
	public static boolean areAnyGranted(Object context, Set<String> permissions) {
		
		for (String permission : permissions) {
			
			if(ContextUtils.discover(context)
				.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * <p>Takes a permission and determines if its's granted.</p>
	 * 
	 * <p>Use {@link Manifest.permission} to reference permission constants.</p>
	 * 
	 * @param context
	 * 			the permissible {@link Context}
	 * 
	 * @param permissions
	 *			the {@link permission} to test 
	 *
	 * @return {@code true} if all the permissions are granted
	 * 
	 * @since 1.1.0
	 */
	public static boolean isGranted(Object context, String permission) {
		
		if(ContextUtils.discover(context)
			.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
				
			return true;
		}
		else {
			
			return false;
		}
	}
}
