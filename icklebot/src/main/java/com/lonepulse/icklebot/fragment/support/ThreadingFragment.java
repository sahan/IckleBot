package com.lonepulse.icklebot.fragment.support;

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


import android.os.Bundle;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.profile.Profiles.PROFILE;
import com.lonepulse.icklebot.profile.ProfileService;
import com.lonepulse.icklebot.task.TaskUtils;

/**
 * <p>This profile offers an alternative threading model for running background 
 * worker threads and posting events on the UI thread.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class ThreadingFragment extends DataFragment {

	
	/**
	 * <p>This flag determines if {@link PROFILE#THREADING} has been activated 
	 * on the {@link IckleActivity}. 
	 */
	private boolean isProfileActive;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	
		super.onActivityCreated(savedInstanceState);
		
		isProfileActive = ProfileService.getInstance(
			getActivity().getApplicationContext()).isActive(this, PROFILE.THREADING);
	}
	
	/**
	 * <p>See {@link AsyncTaskService#execute(ThreadingActivity, int, Object...)}.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void runAsyncTask(int asyncTaskId, Object... args) {
		
		TaskUtils.runAsyncTask(getActivity(), isProfileActive, asyncTaskId, args);
	}
	
	/**
	 * <p>See {@link UITaskService#execute(ThreadingActivity, int, Object...)}.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void runUITask(int uiTaskId, final Object... args) {

		TaskUtils.runUITask(getActivity(), isProfileActive, uiTaskId, args);
	}
}
