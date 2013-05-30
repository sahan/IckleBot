package com.lonepulse.icklebot.bind;

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


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import android.view.View;

import com.lonepulse.icklebot.annotation.bind.Bind;
import com.lonepulse.icklebot.annotation.bind.Bind.BINDER;
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

	@Override
	public AbstractBinder<? extends View, ? extends Object> resolve(View view, Object model, Field attribute) 
	throws BindResolutionException {

		try {
		
			if(attribute.isAnnotationPresent(Bind.class)) {
			
				Bind bind = attribute.getAnnotation(Bind.class);
				BINDER binder = bind.binder();
				
				Class<? extends AbstractBinder<? extends View, ? extends Object>> binderType;
				
				switch (binder) {
				
					case UNDEFINED: binderType = bind.binderType(); break;
					
					default: binderType = binder.getType(); break;
				}
				
				if(binderType == null) {
					
					StringBuilder errorContext = new StringBuilder()
					.append("An existing binding strategy, ")
					.append("or a custom binder must be specified via the ")
					.append(Bind.class.getName())
					.append(" annotation. ");
					
					throw new BindResolutionException(errorContext.toString());
				}
				
				int viewId = (bind.value() == 0)? bind.viewId() :bind.value();
				
				if(viewId == 0) {
					
					StringBuilder errorContext = new StringBuilder()
					.append("A view ID must be supplied via the ")
					.append(Bind.class.getName())
					.append(" annotation. ");
					
					throw new BindResolutionException(errorContext.toString());
				}
				
				Constructor<? extends AbstractBinder<? extends View, ? extends Object>> constructor 
					= binderType.getConstructor(View.class, Object.class);

				if(constructor == null) {
					
					StringBuilder errorContext = new StringBuilder()
					.append("The required constructor signature was not found on ")
					.append(binderType.getName())
					.append(". Please ensure that an exposed constructor which takes ")
					.append("a view and its data is present. ");
					
					throw new BindResolutionException(errorContext.toString());
				}
				
				View viewElement = view.findViewById(viewId);
				
				if(viewElement == null) {
					
					StringBuilder errorContext = new StringBuilder()
					.append("The view-element with ID ")
					.append(viewId)
					.append(" was not found in given view. ");
					
					throw new BindResolutionException(errorContext.toString());
				}
				
				Object data = FieldUtils.getFieldValue(model, Object.class, attribute);
				return constructor.newInstance(viewElement, data);
			}
			else {
			
				return VoidBinder.INSTANCE;
			}
		}
		catch(Exception e) {
			
			throw (e instanceof BindResolutionException)? 
					(BindResolutionException)e :new BindResolutionException(e);
		}
	}
}
