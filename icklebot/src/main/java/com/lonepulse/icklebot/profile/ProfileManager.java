package com.lonepulse.icklebot.profile;

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


import android.app.Activity;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;

/**
 * <p>This contract specifies the service offered on managing the 
 * {@link PROFILE}s associated with an {@link IckleActivity}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface ProfileManager {

	
	/**
	 * <p>Determines whether a given profile is active for a given 
	 * instance of {@link Activity}.
	 * 
	 * @param activity
	 * 			the {@link Activity} whose profile activation needs 
	 * 			to be discovered
	 * 
	 * @param profile
	 * 			the {@link PROFILE} to be checked 
	 * 
	 * @return {@code true} if the given profile is active, else 
	 * 		   {@code false}
	 */
	public abstract boolean isActive(Activity activity, PROFILE profile);
}
