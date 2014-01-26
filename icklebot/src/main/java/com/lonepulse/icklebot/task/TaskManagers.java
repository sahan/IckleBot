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

import android.app.Activity;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.injector.InjectionProvider;

/**
 * <p>Maintains a set of {@link InjectionProvider} <i>singletons</i> which are used 
 * by any {@link Activity} which implements the {@link Activity} 
 * and has requested <b>Implicit Injection</b> via {@link InjectAll}.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum TaskManagers implements TaskManager {

	/**
	 * <p>This {@link TaskManager} is responsible for executing 
	 * tasks on a background worker thread.</p>
	 * 
	 * @since 1.1.0
	 */
	ASYNC(new AsyncTaskService()),
	
	/**
	 * <p>This {@link TaskManager} is responsible for executing 
	 * tasks on the UI thread.</p>
	 * 
	 * @since 1.1.0
	 */
	UI(new UITaskService());
	
	
	/**
	 * <p>The wrapped {@link TaskManager} <i>singleton</i>.</p>
	 * 
	 * @since 1.1.0
	 */
	private TaskManager taskManager;
	
	/**
	 * <p>A parameterized constructor which initializes the 
	 * {@link #taskManager}.</p>
	 * 
	 * @param taskManager
	 * 			populates {@link #taskManager}
	 * <br><br>
	 * @since 1.1.0
	 */
	private TaskManagers(TaskManager taskManager) {
		
		this.taskManager = taskManager;
	}

	/**
	 * <p>Delegate for invoking the {@link #taskManager}'s 
	 * {@link TaskManager#execute(Object, int, Object...) service.</p>
	 * 
	 * @since 1.1.0
	 */
	@Override
	public void execute(Object context, int taskId, Object... args) {
		
		try {
			
			taskManager.execute(context, taskId, args);
		}
		catch(Exception e) {
					
			StringBuilder stringBuilder = new StringBuilder();
					
			stringBuilder.append("Task execution using ");
			stringBuilder.append(taskManager.getClass().getName());
			stringBuilder.append(" failed on activity ");
			stringBuilder.append(context.getClass().getName());
			stringBuilder.append(". ");
			
			Log.e(getClass().getName(), stringBuilder.toString(), e);
		}
	}
}
