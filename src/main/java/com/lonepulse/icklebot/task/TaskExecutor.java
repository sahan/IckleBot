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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.util.Log;

import com.lonepulse.icklebot.BoilerPlateActivity;
import com.lonepulse.icklebot.annotations.BackgroundTask;

/**
 * <p>This {@link Enum} contains a <b>Cached Thread Pool</b> which is used 
 * to execute background tasks identified via {@link BackgroundTask}.</p> 
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum TaskExecutor {

	/**
	 * <p>The single instance of the {@link TaskExecutor} used 
	 * in every {@link BoilerPlateActivity}.</p>
	 * 
	 * @since 1.0.0
	 */
	CACHED_THREAD_POOL;
	
	
	/**
	 * <p>The <b>Cached Thead Pool</b> which is used to execute 
	 * worker threads.</p>
	 */
	private ExecutorService executorService = null;
	
	/**
	 * <p>Default constructor is overridden to instantiate 
	 * any instance variables. 
	 */
	private TaskExecutor() {
	
		this.executorService = Executors.newCachedThreadPool();
	}
	
	/**
	 * <p>Takes an {@link BoilerPlateActivity} and executes the specified 
	 * {@link Method} on it via a worker thread.</p>
	 * 
	 * @param boilerPlateActivity
	 * 			the {@link BoilerPlateActivity} on which a background task 
	 * 			is to be run
	 * <br><br>
	 * @param method
	 * 			the {@link Method} which needs to be invoked on a worker 
	 * 			thread
	 * <br><br>
	 * @since 1.0.0
	 */
	public void execute(final BoilerPlateActivity boilerPlateActivity, final Method method, final Object... args) {
		try {
		
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						
						if(!method.isAccessible()) method.setAccessible(true);
							
						method.invoke(boilerPlateActivity, args);
					} 
					catch (Exception e) {
						
						StringBuilder stringBuilder = new StringBuilder();
						
						stringBuilder.append("Failed to invoke ");
						stringBuilder.append(method.getName());
						stringBuilder.append(" on ");
						stringBuilder.append(boilerPlateActivity.getClass().getName());
						stringBuilder.append(" with arguments ");
						stringBuilder.append(args);
						stringBuilder.append(". ");
						
						Log.e(getClass().getName(), stringBuilder.toString(), e);
					}
				}
			});
		}
		catch(Exception e) {
			
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("Background task ");
			stringBuilder.append(method.getName());
			stringBuilder.append(" failed to execute on ");
			stringBuilder.append(boilerPlateActivity.getClass().getName());
			stringBuilder.append(" with arguments ");
			stringBuilder.append(args);
			stringBuilder.append(". ");
			
			Log.w(getClass().getName(), stringBuilder.toString(), e);
		}
	}
}
