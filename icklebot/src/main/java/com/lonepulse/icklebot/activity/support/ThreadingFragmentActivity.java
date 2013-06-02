package com.lonepulse.icklebot.activity.support;

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

import com.lonepulse.icklebot.task.TaskUtils;

/**
 * <p>This profile offers an alternative threading model for running background 
 * worker threads and posting events on the UI thread.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
abstract class ThreadingFragmentActivity extends NetworkFragmentActivity {
	
	
	/**
	 * <p>See {@link AsyncTaskService#execute(ThreadingActivity, int, Object...)}.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void runAsyncTask(int asyncTaskId, Object... args) {
		
		TaskUtils.runAsyncTask(this, asyncTaskId, args);
	}
	
	/**
	 * <p>See {@link UITaskService#execute(ThreadingActivity, int, Object...)}.</p>
	 * 
	 * @since 1.1.0
	 */
	protected void runUITask(int uiTaskId, final Object... args) {

		TaskUtils.runUITask(this, uiTaskId, args);
	}
}
