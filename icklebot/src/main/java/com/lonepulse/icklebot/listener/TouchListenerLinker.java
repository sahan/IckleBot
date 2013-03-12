package com.lonepulse.icklebot.listener;

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


import java.lang.reflect.Method;
import java.util.Set;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.lonepulse.icklebot.annotation.event.Touch;
import com.lonepulse.icklebot.listener.resolver.ListenerCategory;

/**
 * <p>A concrete implementation of {@link ListenerLinker} which links methods 
 * annotated with {@code @Click} to the specified {@link pView}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class TouchListenerLinker implements ListenerLinker {

	
	/**
	 * <p>An <i>eager initialized</i> instance of {@link TouchListenerLinker}.</p>
	 * 
	 * @since 1.0.0
	 */
	public static final TouchListenerLinker INSTANCE; 

	static 
	{
		INSTANCE = new TouchListenerLinker();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void link(ListenerLinker.Configuration config) {

		final Activity listenerTemplate = config.getActivity();
		
		Set<Method> methods = config.getListenerTargets(ListenerCategory.TOUCH);
		
		for (final Method method : methods) {
			
			OnTouchListener onTouchListener = new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					
					try {
						
						if(!method.isAccessible()) method.setAccessible(true);
						
						Class<?>[] params = method.getParameterTypes();
						
						Object[] args = new Object[params.length];
						boolean argsPopulated = false;

						if(params.length < 3) {
							
							argsPopulated = true;
							
							for(int i = 0; i < params.length; i++) {
							
								if(View.class.isAssignableFrom(params[i])) {
									
									args[i] = v;
								}
								else if(MotionEvent.class.isAssignableFrom(params[i])) {
									
									args[i] = event;
								}
								else {
									
									argsPopulated = false;
								}
							}
						}
							
						if(argsPopulated)
							method.invoke(listenerTemplate, args);
							
						else
							method.invoke(listenerTemplate);
					}
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Invocation of ")
						.append(method.getName())
						.append(" at ")
						.append(listenerTemplate.getClass().getName())
						.append(" failed for event OnTouch.");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}

					return false;
				}
			};
			
			try {
				
				int[] views = method.getAnnotation(Touch.class).value();
				
				for (int id : views) {

					try {
						
						listenerTemplate.findViewById(id).setOnTouchListener(onTouchListener);
					}
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Touch listener linking failed on method ")
						.append(method.getName())
						.append(" at ")
						.append(listenerTemplate.getClass().getName())
						.append(" for view with ID ")
						.append(listenerTemplate.getResources().getResourceName(id))
						.append(".");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}
				}
			} 
			catch (Exception e) {
				
				StringBuilder builder = new StringBuilder()
				.append("Touch listener linking failed on method ")
				.append(method.getName())
				.append(" at ")
				.append(listenerTemplate.getClass().getName())
				.append(".");
				
				Log.e(getClass().getName(), builder.toString(), e);
			}
		}
	}
}
