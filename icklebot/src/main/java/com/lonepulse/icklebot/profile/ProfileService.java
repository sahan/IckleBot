package com.lonepulse.icklebot.profile;

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

import com.lonepulse.icklebot.activity.IckleActivity;
import com.lonepulse.icklebot.annotation.profile.ExcludeProfiles;
import com.lonepulse.icklebot.annotation.profile.IncludeProfiles;
import com.lonepulse.icklebot.annotation.profile.Profile;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>This is a utility class which is used to determine the activation status 
 * of a {@link PROFILE} for a particular {@link IckleActivity}.
 * 
 * @version 1.1.2
 * <b></b>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ProfileService implements ProfileManager {


	private static volatile ProfileManager instance;
	

	/**
	 * <p>The base {@link Context} in which this service is 
	 * being initialized. 
	 */
	@SuppressWarnings("unused") //to guard against a future contract-break
	private Context context;
	
	
	/**
	 * <p>Instantiation is restricted. Use the {@link #newInstance()} 
	 * or {@link #getInstance()} instead.
	 */
	private ProfileService(Context context) {
		
		this.context = context; 
	}
	
	/**
	 * <p>Creates a new instance of an implementation of {@link ProfileManager}.
	 * 
	 * @param context
	 * 			the invocation context
	 * 
	 * @return a new instance of {@link ProfileManager}.
	 * 
	 * @since 1.1.1
	 */
	public static final synchronized ProfileManager getInstance(Object context) {
		
		return (instance == null)? 
				(instance = new ProfileService(ContextUtils.asApplication(context))) :instance;
	}
	
	/**
	 * <p>Creates a new instance of an implementation of 
	 * {@link ProfileManager}.
	 * 
	 * @param context
	 * 			the context in which the service is instantiated
	 * 
	 * @return a new instance of {@link ProfileManager}.
	 * 
	 * @since 1.1.1
	 */
	public static final ProfileManager newInstance(Context context) {
		
		return new ProfileService(context);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isActive(Object context, Profile profile) {
		
		if(context == null || profile == null) {
			
			StringBuilder builder = new StringBuilder()
			.append("Failed to determine profile active profiles. ");
			
			if(context == null)
				builder.append("Context cannot be null. ");
				
			if(profile == null)
				builder.append("Profile cannot be null. ");
			
			throw new IllegalArgumentException(builder.toString());
		}
		
		if(context.getClass().isAnnotationPresent(IncludeProfiles.class)) {
			
			IncludeProfiles profiles = context.getClass().getAnnotation(IncludeProfiles.class);
			
			Profile[] activeProfiles = profiles.value();
			
			for (Profile currentProfile : activeProfiles)
				if(currentProfile.equals(profile)) return true;
			
			return false;
		}
		if(context.getClass().isAnnotationPresent(ExcludeProfiles.class)) {
			
			ExcludeProfiles profiles = context.getClass().getAnnotation(ExcludeProfiles.class);
			
			Profile[] inactiveProfiles = profiles.value();
			
			for (Profile currentProfile : inactiveProfiles)
				if(currentProfile.equals(profile)) return false;
			
			return true;
		}
		else {
			
			return true;
		}
	}
}
