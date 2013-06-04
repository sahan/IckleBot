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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lonepulse.icklebot.annotation.event.ItemClick;
import com.lonepulse.icklebot.event.resolver.EventCategory;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>A concrete implementation of {@link EventLinker} which links methods 
 * annotated with {@code @ItemClick} to the specified {@link ListView}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ItemClickEventLinker implements EventLinker {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void link(EventLinker.Configuration config) {

		final Object listenerTemplate = config.getContext();
		
		Set<Method> methods = config.getListenerTargets(EventCategory.ITEM_CLICK);
		
		for (final Method method : methods) {
			
			OnItemClickListener onItemClickListener = new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					
					try {
						
						if(!method.isAccessible()) method.setAccessible(true);
						
						Class<?>[] paramTypes = method.getParameterTypes();
						List<Object> params = new ArrayList<Object>();
						
						for (Class<?> paramType : paramTypes) {
						
							if(paramType.equals(AdapterView.class)) params.add(parent);
							else if(paramType.equals(View.class)) params.add(view);
							else if(paramType.equals(int.class)) params.add(position);
							else if(paramType.equals(long.class)) params.add(id);
						}
						
						if(paramTypes.length == params.size())
							method.invoke(listenerTemplate, params.toArray());
					}
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Invocation of ")
						.append(method.getName())
						.append(" at ")
						.append(listenerTemplate.getClass().getName())
						.append(" failed for event OnItemClick.");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}
				}
			};
			
			try {
				
				int[] views = method.getAnnotation(ItemClick.class).value();
				
				for (int id : views) {

					try {
						
						if(ContextUtils.isActivity(listenerTemplate)) {
							
							((AdapterView<?>)ContextUtils.asActivity(listenerTemplate)
								.findViewById(id)).setOnItemClickListener(onItemClickListener);
						}
						else if(ContextUtils.isFragment(listenerTemplate)) {
							
							((AdapterView<?>)ContextUtils.asFragment(listenerTemplate)
								.getView().findViewById(id)).setOnItemClickListener(onItemClickListener);
						}
						else if(ContextUtils.isSupportFragment(listenerTemplate)) {
							
							((AdapterView<?>)ContextUtils.asSupportFragment(listenerTemplate)
								.getView().findViewById(id)).setOnItemClickListener(onItemClickListener);
						}
					}
					catch (Exception e) {
						
						StringBuilder builder = new StringBuilder()
						.append("Item click listener linking failed on method ")
						.append(method.getName())
						.append(" at ")
						.append(listenerTemplate.getClass().getName())
						.append(" for view with ID ")
						.append(ContextUtils.isActivity(listenerTemplate)? 
							ContextUtils.asActivity(listenerTemplate).getResources().getResourceName(id)
							:ContextUtils.asFragment(listenerTemplate).getResources().getResourceName(id))
						.append(".");
						
						Log.e(getClass().getName(), builder.toString(), e);
					}
				}
			} 
			catch (Exception e) {
				
				StringBuilder builder = new StringBuilder()
				.append("Item click listener linking failed on method ")
				.append(method.getName())
				.append(" at ")
				.append(listenerTemplate.getClass().getName())
				.append(".");
				
				Log.e(getClass().getName(), builder.toString(), e);
			}
		}
	}
}
