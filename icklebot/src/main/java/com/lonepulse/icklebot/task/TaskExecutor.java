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
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.util.Log;

import com.lonepulse.icklebot.annotation.thread.Async;
import com.lonepulse.icklebot.util.LifeCycle;

/**
 * <p>This {@link Enum} contains a <b>Cached Thread Pool</b> which is used 
 * to execute background tasks identified via {@link Async}.</p> 
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
enum TaskExecutor implements LifeCycle.Destroy {

	/**
	 * <p>The single instance of the {@link TaskExecutor} used 
	 * in every {@link Activity}.</p>
	 * 
	 * @since 1.0.0
	 */
	CACHED_THREAD_POOL;
	
	
	/**
	 * <p>The <b>Cached Thead Pool</b> which is used to execute worker threads.</p>
	 */
	private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
	
	/**
	 * <p>Takes an {@link Activity} and executes the specified 
	 * {@link Method} on it via a worker thread.</p>
	 * 
	 * @param context
	 * 			the context on which a background task is to be run
	 * <br><br>
	 * @param method
	 * 			the {@link Method} which needs to be invoked on a worker 
	 * 			thread
	 * <br><br>
	 * @since 1.0.0
	 */
	public void execute(final Object context, final Method method, final Object... args) {
		
		try {

			EXECUTOR_SERVICE.execute(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						
						if(!method.isAccessible()) method.setAccessible(true);
							
						method.invoke(context, args);
					} 
					catch (Exception e) {
						
						StringBuilder stringBuilder = new StringBuilder();
						
						stringBuilder.append("Failed to invoke ");
						stringBuilder.append(method.getName());
						stringBuilder.append(" on ");
						stringBuilder.append(context.getClass().getName());
						stringBuilder.append(" with arguments ");
						stringBuilder.append(Arrays.toString(args));
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
			stringBuilder.append(context.getClass().getName());
			stringBuilder.append(" with arguments ");
			stringBuilder.append(Arrays.toString(args));
			stringBuilder.append(". ");
			
			Log.w(getClass().getName(), stringBuilder.toString(), e);
		}
	}
	
	/**
	 * <p>Performs a shutdown of the {@link #CACHED_THREAD_POOL};
	 * 
	 * @since 1.1.1
	 * 
	 * TODO hook {@link TaskExecutor#onDestroy()} to disable task execution permanently 
	 */
	@Override
	public void onDestroy() {
		
		if(!EXECUTOR_SERVICE.isShutdown()) {
			
			EXECUTOR_SERVICE.shutdown();
		
			try {
				
				if(!EXECUTOR_SERVICE.awaitTermination(10, TimeUnit.SECONDS)) {
				
					EXECUTOR_SERVICE.shutdownNow();
						
					if(!EXECUTOR_SERVICE.awaitTermination(5, TimeUnit.SECONDS))
						Log.w(getClass().getSimpleName(), "Failed to shutdown task pool.");
				}
			}
			catch (InterruptedException ie) {
	
				EXECUTOR_SERVICE.shutdownNow();
				
				Thread.currentThread().interrupt();
			}
		}
	}
}
