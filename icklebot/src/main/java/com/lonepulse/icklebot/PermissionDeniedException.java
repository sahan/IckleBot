package com.lonepulse.icklebot;

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


import com.lonepulse.icklebot.annotation.profile.Profile;


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
	 * 			the {@link Profile} which the permission belongs to
	 * 
	 * @since 1.1.0
	 */
	public PermissionDeniedException(String permission, Profile profile) {
		
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
