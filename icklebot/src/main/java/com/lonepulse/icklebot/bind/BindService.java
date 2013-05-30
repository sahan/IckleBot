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


import java.lang.reflect.Field;
import java.util.Set;

import android.util.Log;
import android.view.View;

import com.lonepulse.icklebot.IckleBotRuntimeException;
import com.lonepulse.icklebot.annotation.bind.Bind;
import com.lonepulse.icklebot.annotation.bind.Model;
import com.lonepulse.icklebot.util.FieldUtils;

/**
 * <p>This is a concrete implementation of {@link BindManager} which performs Model-View binding.
 * 
 * @version 1.1.0
 * <b></b>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BindService implements BindManager {


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind(View view, Object model) {

		StringBuilder errorContext = new StringBuilder("Model-View binding failed. The argument(s), ");
		boolean hasNullArguments = false;
		
		if(view == null) {
			
			errorContext.append("view, ");
			hasNullArguments = true;
		}
		
		if(model == null) {
			
			errorContext.append("model, ");
			hasNullArguments = true;
		}
		
		if(hasNullArguments) {
			
			errorContext.append("cannot be null. ");
			throw new IckleBotRuntimeException(new IllegalArgumentException(errorContext.toString()));
		}
		
		Class<?> modelClass = model.getClass();
		
		if(!modelClass.isAnnotationPresent(Model.class)) {
			
			StringBuilder errorContext2 = new StringBuilder()
			.append("Model-View binding failed. ")
			.append(" The supplied model failed to be identified as a ")
			.append(Model.class.getName())
			.append(".  Please annotate it using @Model. ");
			
			throw new IllegalArgumentException(errorContext2.toString());
		}
		
		Set<Field> fields = FieldUtils.getAllFields(model, Bind.class);
		
		for (Field field : fields) {

			try {
			
				BinderResolvers.BASIC.resolve(view, model, field).bind();
			}
			catch(BindResolutionException bre) {
				
				Log.e(getClass().getSimpleName(), "Bind Resolution Failure. ", bre);
			}
			catch(BindException be) {
				
				Log.e(getClass().getSimpleName(), "Bind Failure. ", be);
			}
		}
	}
}
