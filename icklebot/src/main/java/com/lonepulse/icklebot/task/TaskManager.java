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


/**
 * <p>This contract declares the services offered by a <i>background task 
 * executors</i>.</p>  
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface TaskManager {

	
	/**
	 * <p>Executes a method designated as a <i>Task</i> identified 
	 * by a unique <i>ID</i>.</p>
	 * 
	 * @param context
	 *			the context in which the <i>UI Task</i> is run 
	 * <br><br>
	 * @param taskId
	 * 			the {@code int} ID of the <i>Task</i>
	 * <br><br>
	 * @param args
	 * 			an array of {@link Object}s which supply the 
	 * 			parameters to the task
	 * <br><br>
	 * @since 1.0.0
	 */
	public void execute(final Object context, int taskId, final Object... args);
}
