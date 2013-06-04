package com.lonepulse.icklebot.event;

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
import android.view.View;
import android.view.View.OnClickListener;

import com.lonepulse.icklebot.annotation.event.Click;
import com.lonepulse.icklebot.event.resolver.EventCategory;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>A concrete implementation of {@link EventLinker} which links methods 
 * annotated with {@code @Click} to the specified {@link View}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ClickEventLinker implements EventLinker {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void link(EventLinker.Configuration config) {

		final Object context = config.getContext();
		
		Set<Method> methods = config.getListenerTargets(EventCategory.CLICK);
		
		for (final Method method : methods) {
			
			OnClickListener onClickListener = new OnClickListener() {
				
				@Override
				public void onClick(View v) {

					try {
						
						if(!method.isAccessible()) method.setAccessible(true);
						Class<?>[] params = method.getParameterTypes();
						boolean viewArgPresent = false;
						
						if(params.length == 1)
							viewArgPresent = View.class.isAssignableFrom(params[0]);
							
						if(viewArgPresent)
							method.invoke(context, v);
							
						else
							method.invoke(context);
					}
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Invocation of ")
						.append(method.getName())
						.append(" at ")
						.append(context.getClass().getName())
						.append(" failed for event OnClick.");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}
				}
			};
			
			try {
				
				int[] views = method.getAnnotation(Click.class).value();
				
				for (int id : views) {

					try {
						
						if(ContextUtils.isActivity(context)) {
							
							ContextUtils.asActivity(context)
								.findViewById(id).setOnClickListener(onClickListener);
						}
						else if(ContextUtils.isFragment(context)) {
							
							ContextUtils.asFragment(context)
								.getView().findViewById(id).setOnClickListener(onClickListener);
						}
						else if(ContextUtils.isSupportFragment(context)) {
							
							ContextUtils.asSupportFragment(context)
								.getView().findViewById(id).setOnClickListener(onClickListener);
						}
					}
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Click listener linking failed on method ")
						.append(method.getName())
						.append(" at ")
						.append(context.getClass().getName())
						.append(" for view with ID ")
						.append(ContextUtils.isActivity(context)? 
							ContextUtils.asActivity(context).getResources().getResourceName(id)
							:ContextUtils.asFragment(context).getResources().getResourceName(id))
						.append(".");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}
				}
			} 
			catch (Exception e) {
				
				StringBuilder builder = new StringBuilder()
				.append("Click listener linking failed on method ")
				.append(method.getName())
				.append(" at ")
				.append(context.getClass().getName())
				.append(".");
				
				Log.e(getClass().getName(), builder.toString(), e);
			}
		}
	}
}
