package com.lonepulse.icklebot.util;

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
	public static boolean areAllGranted(Context context, Set<String> permissions) {
		
		for (String permission : permissions) {
			
			if(context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
				
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
	public static boolean areAnyGranted(Context context, Set<String> permissions) {
		
		for (String permission : permissions) {
			
			if(context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
				
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
	public static boolean isGranted(Context context, String permission) {
		
		if(context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
				
			return true;
		}
		else {
			
			return false;
		}
	}
}
