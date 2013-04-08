package com.lonepulse.icklebot;

import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;


/**
 * <p>This {@link IckleBotRuntimeException} is thrown when a required permission 
 * if found to be denied in the caller's context.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class PermissionDeniedException extends IckleBotRuntimeException {

	
	private static final long serialVersionUID = -1316335786347605784L;

	
	/**
	 * <p>Prints a detailed message with information about the missing permission.
	 * 
	 * @param permission
	 * 			the missing permission
	 * 
	 * @since 1.1.0
	 */
	public PermissionDeniedException(String permission) {
		
		super("Required permission " + permission + " is denied. " +
		      "Please add it to you manifest using the <uses-permission> tag. ");
		
	}
	
	/**
	 * <p>Prints a detailed message with information about the missing permission 
	 * and the {@link PROFILE} which it belongs to.
	 * 
	 * @param permission
	 * 			the missing permission
	 * 
	 * @param profile
	 * 			the {@link PROFILE} which the permission belongs to
	 * 
	 * @since 1.1.0
	 */
	public PermissionDeniedException(String permission, PROFILE profile) {
		
		super("Required permission " + permission + " for profile " + profile.name() + " is denied. " +
			  "Please add it to you manifest using the <uses-permission> tag. ");
	}
	
	/**
	 * <p>Prints a detailed message with information about the missing permission 
	 * and the service which it belongs to.
	 * 
	 * @param permission
	 * 			the missing permission
	 * 
	 * @param service
	 * 			the service which the permission belongs to
	 * 
	 * @since 1.1.0
	 */
	public PermissionDeniedException(String permission, String service) {
		
		super("Required permission " + permission + " for service " + service + " is denied. " +
			  "Please add it to you manifest using the <uses-permission> tag. ");
	}

	/**
	 * <p>See {@link IckleBotRuntimeException#IckleBotRuntimeException(Throwable)}.
	 */
	public PermissionDeniedException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * <p>See {@link IckleBotRuntimeException#IckleBotRuntimeException(String, Throwable)}.
	 */
	public PermissionDeniedException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
