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

import android.util.Log;

import com.lonepulse.icklebot.BoilerPlateActivity;
import com.lonepulse.icklebot.annotation.BackgroundTask;
import com.lonepulse.icklebot.util.MethodUtils;

/**
 * <p>This service is delegated the responsibility of executing background 
 * tasks on a <b>Worker Thread</b> in a {@link BoilerPlateActivity}.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BackgroundTaskService implements TaskService {

	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link BackgroundTaskService}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final BackgroundTaskService INSTANCE; 

	static 
	{
		INSTANCE = new BackgroundTaskService();
	}
	

	/**
	 * {@inheritDoc}
	 * 
	 * <p>Executes a method designated as a <i>Background Task</i> 
	 * (via the annotation {@link BackgroundTask}) on a separate 
	 * worker thread.</p>
	 */
	@Override
	public void execute(final BoilerPlateActivity boilerPlateActivity, int backgroundTaskId, final Object... args) {
		
		try {
		
			Set<Method> methods = MethodUtils.getAllMethods(boilerPlateActivity, BackgroundTask.class);
			
			BackgroundTask backgroundTask;
			
			for (Method method : methods) {
			
				backgroundTask = method.getAnnotation(BackgroundTask.class);
				
				if(backgroundTask.value() == backgroundTaskId) {
					
					TaskExecutor.CACHED_THREAD_POOL.execute(boilerPlateActivity, method, args);
					break;
				}
			}
		}
		catch (Exception e) {
			
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("Failed to execute background task with id ");
			stringBuilder.append(backgroundTaskId);
			stringBuilder.append(" on ");
			stringBuilder.append(boilerPlateActivity.getClass().getName() );
			stringBuilder.append(" with arguments ");
			stringBuilder.append(args);
			stringBuilder.append(". ");
			
			Log.e(BackgroundTaskService.class.getName(), stringBuilder.toString(), e);
		}
	}
}
