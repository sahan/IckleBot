package com.lonepulse.icklebot.task;

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


import android.util.Log;

import com.lonepulse.icklebot.annotation.profile.Profile;
import com.lonepulse.icklebot.profile.ProfileService;

/**
 * <p>This class offers a set of utility operations for task execution.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class TaskUtils {
	
	
	/**
	 * <p>Constructor visibility. Instantiation is nonsensical.</p>
	 */
	private TaskUtils() {}

	/**
	 * <p>See {@link AsyncTaskService#execute(ThreadingActivity, int, Object...)}.</p>
	 * 
	 * @since 1.1.0
	 */
	public static void runAsyncTask(Object context, int asyncTaskId, Object... args) {
		
		if(!ProfileService.getInstance(context).isActive(context, Profile.THREADING)) {

			StringBuilder builder = new StringBuilder()
			.append("Async task with ID ")
			.append(asyncTaskId)
			.append("cannot be run since the threading profile is inactive.");
			
			Log.w("Async task cannot ", builder.toString());

			return;
		}
		
		TaskManagers.ASYNC.execute(context, asyncTaskId, args);
	}
	
	/**
	 * <p>See {@link UITaskService#execute(ThreadingActivity, int, Object...)}.</p>
	 * 
	 * @since 1.1.0
	 */
	public static void runUITask(Object context, int uiTaskId, final Object... args) {

		if(!ProfileService.getInstance(context).isActive(context, Profile.THREADING)) {
		
			StringBuilder builder = new StringBuilder()
			.append("UI task with ID ")
			.append(uiTaskId)
			.append("cannot be run since the threading profile is inactive.");
			
			Log.w("Async task cannot ", builder.toString());
			
			return;
		}
		
		TaskManagers.UI.execute(context, uiTaskId, args);
	}
}
