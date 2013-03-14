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

import java.lang.reflect.Method;
import java.util.Set;

import android.app.Activity;
import android.util.Log;

import com.lonepulse.icklebot.annotation.thread.Async;
import com.lonepulse.icklebot.util.MethodUtils;

/**
 * <p>This service is delegated the responsibility of executing background 
 * tasks on a <b>Worker Thread</b> in a {@link Activity}.</p>
 * 
 * @version 1.2.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class AsyncTaskService implements TaskManager {

	
	/**
	 * <p>Creates a new instance of an implementation of 
	 * {@link TaskManager}.
	 * 
	 * @return a new instance of {@link TaskManager}.
	 * 
	 * @since 1.2.0
	 */
	public static final TaskManager newInstance() {
		
		return new AsyncTaskService();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>Executes a method designated as a <i>Background Task</i> 
	 * (via the annotation {@link Async}) on a separate 
	 * worker thread.</p>
	 */
	@Override
	public void execute(final Activity activity, int backgroundTaskId, final Object... args) {
		
		try {
		
			Set<Method> methods = MethodUtils.getAllMethods(activity, Async.class);
			
			Async backgroundTask;
			
			for (Method method : methods) {
			
				backgroundTask = method.getAnnotation(Async.class);
				
				if(backgroundTask.value() == backgroundTaskId) {
					
					TaskExecutor.CACHED_THREAD_POOL.execute(activity, method, args);
					break;
				}
			}
		}
		catch (Exception e) {
			
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("Failed to execute background task with id ");
			stringBuilder.append(backgroundTaskId);
			stringBuilder.append(" on ");
			stringBuilder.append(activity.getClass().getName() );
			stringBuilder.append(" with arguments ");
			stringBuilder.append(args);
			stringBuilder.append(". ");
			
			Log.e(AsyncTaskService.class.getName(), stringBuilder.toString(), e);
		}
	}
}
