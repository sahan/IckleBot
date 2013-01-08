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
import com.lonepulse.icklebot.annotations.UITask;
import com.lonepulse.icklebot.util.MethodUtils;

/**
 * <p>This service is delegated the responsibility of executing code 
 * on the <b>UI Thread</b> of an {@link BoilerPlateActivity}.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class UITaskService implements TaskService {
	
	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link UITaskService}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final UITaskService INSTANCE; 

	static 
	{
		INSTANCE = new UITaskService();
	}
	
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>Executes a method designated as a <i>UI Task</i> (via 
	 * the annotation {@link UITask}) on the <b>UI Event Loop</b>.</p>
	 */
	@Override
	public void execute(final BoilerPlateActivity boilerPlateActivity, int uiTaskId, final Object... args) {
		
		Set<Method> methods = MethodUtils.getAllMethods(boilerPlateActivity, UITask.class);
		
		UITask uiTask;
		
		for (final Method method : methods) {
			
			uiTask = method.getAnnotation(UITask.class);
			
			if(uiTask.value() == uiTaskId) {
				
				boilerPlateActivity.getWindow().getDecorView().findViewById(android.R.id.content)
				.postDelayed(new Runnable() {
					
					@Override
					public void run() {
					
						try {
							
							if(!method.isAccessible()) method.setAccessible(true);
							
							method.invoke(boilerPlateActivity, args);
						} 
						catch (Exception e) {
							
							StringBuilder stringBuilder = new StringBuilder();
							
							stringBuilder.append("Failed to execute UI task ");
							stringBuilder.append(method.getName());
							stringBuilder.append(" on ");
							stringBuilder.append(boilerPlateActivity.getClass().getName() );
							stringBuilder.append(" with arguments ");
							stringBuilder.append(args);
							stringBuilder.append(". ");
							
							Log.e(UITaskService.class.getName(), stringBuilder.toString(), e);
						}
					}
				}, uiTask.delay());
				
				break;
			}
		}
	}
}
