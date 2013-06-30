package com.lonepulse.icklebot.bind;

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


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.View;

import com.lonepulse.icklebot.annotation.bind.Bind;
import com.lonepulse.icklebot.annotation.bind.BindImage;
import com.lonepulse.icklebot.annotation.bind.BindText;
import com.lonepulse.icklebot.util.FieldUtils;

/**
 * <p>A basic implementation of {@link BinderResolver} which resolves an 
 * {@link AbstractBinder} for a model attribute annotated with {@link Bind}. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class BasicBinderResolver implements BinderResolver {

	/**
	 * {@inheritDoc}
	 */
	@Override 
	@SuppressWarnings("unchecked") //safe cast from Object (at Constructor#newInstance()) to AbstractBinder
	public List<BinderEntry> resolve(View view, Object model) 
	throws BindResolutionException {

		List<BinderEntry> binderEntries = new ArrayList<BinderEntry>();
		
		try {

			Field[] fields = model.getClass().getDeclaredFields();
			
			for (Field attribute : fields) {
				
				Class<? extends AbstractBinder<? extends View, ? extends Object>> binderType = null;
				int[] widgetIds = null;
				
				if(attribute.isAnnotationPresent(BindText.class)) {
					
					binderType = Binder.TEXT.getType();
					widgetIds = attribute.getAnnotation(BindText.class).value();
				}
				else if(attribute.isAnnotationPresent(BindImage.class)) {
					
					binderType = Binder.IMAGE.getType();
					widgetIds = attribute.getAnnotation(BindImage.class).value();
				}
				else if(attribute.isAnnotationPresent(Bind.class)) {
					
					Bind bind = attribute.getAnnotation(Bind.class);
					
					binderType = bind.type();
					widgetIds = bind.ids();
				}
				else {
					 
					binderEntries.add(new BinderEntry(attribute, VoidBinder.getInstance(view.getContext())));
					continue;
				}
				
				if(widgetIds == null || widgetIds.length == 0) {
					
					throw new BindResolutionException(
						"One or more widget IDs must be supplied via the bind annotation. ");
				}
					
				Constructor<?>[] constructors = binderType.getConstructors();
				Constructor<?> constructor = null;
					
				for (Constructor<?> candidateConstructor : constructors) {
						
					Class<?>[] parameters = candidateConstructor.getParameterTypes();
					if(parameters.length == 2) constructor = candidateConstructor;
				}
				
				if(constructor == null) {
						
					StringBuilder errorContext = new StringBuilder()
					.append("The required constructor signature was not found on ")
					.append(binderType.getName())
					.append(". Please ensure that a public constructor which takes only ")
					.append("a widget and its data is present. ");
						
					throw new BindResolutionException(errorContext.toString());
				}
				
				for (int widgetId : widgetIds) {
					
					try {
					
						View widget = view.findViewById(widgetId);
						
						if(widget == null) {
							
							StringBuilder errorContext = new StringBuilder()
							.append("The widget with ID ")
							.append(widgetId)
							.append(" was not found in given view. ");
							
							throw new BindResolutionException(errorContext.toString());
						}
						
						Object data = FieldUtils.getFieldValue(model, Object.class, attribute);
						binderEntries.add(new BinderEntry(attribute, AbstractBinder.class.cast(constructor.newInstance(widget, data))));
					}
					catch(Exception e) {
						
						Log.e(getClass().getSimpleName(), "Binder resolution failed. ", e);
					}
				}
			}
		}
		catch(Exception e) {
			
			throw (e instanceof BindResolutionException)? 
				(BindResolutionException)e :new BindResolutionException(e);
		}
		
		return binderEntries;
	}
}
