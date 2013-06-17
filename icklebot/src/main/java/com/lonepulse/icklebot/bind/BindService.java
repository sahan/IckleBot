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


import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lonepulse.icklebot.IckleBotRuntimeException;
import com.lonepulse.icklebot.annotation.bind.Expressive;
import com.lonepulse.icklebot.annotation.bind.Model;

/**
 * <p>This is a concrete implementation of {@link BindManager} which performs Model-View binding.
 * 
 * @version 1.1.1
 * <b></b>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BindService implements BindManager {

	
	/**
	 * <p>The {@link BinderResolver} to be used for determining 
	 * the {@link BindingStrategy} for a particular {@link View} 
	 * and model attribute.
	 */
	private BinderResolver binderResolver;
	
	
	/**
	 * <p>Creates a new instance of {@link BindService} by instantiation 
	 * an appropriate {@link BinderResolver}.
	 *
	 * @since 1.1.1
	 */
	public BindService() {
		
		binderResolver = new BasicBinderResolver();
	}

	/**
	 * <p>Binds the given model to the {@link View} or {@link ViewGroup}. 
	 * Make sure that your invoke this service from the <b>UI thread</b>. 
	 * 
	 * @param view
	 * 			the {@link View} to which the model is to be bound 
	 * 
	 * @param model
	 * 			the {@link Model} to be bound to the view
	 * 
	 * @throws IllegalAccessException
	 * 			if invoked from outside the UI thread
	 * 
	 * @throws IllegalArgumentException
	 * 			if any of the views are {@code null}
	 * 
	 * @throws BindResolutionException
	 * 			if a {@link BindingStrategy} could not be discovered
	 * 
	 * @throws BindException
	 * 			if a {@link BindingStrategy} failed to execute successfully
	 * 
	 * @since 1.1.0
	 */
	@Override
	public void bind(View view, Object model) {

		if(Looper.myLooper() != Looper.getMainLooper()) {
			
			StringBuilder errorContext = new StringBuilder()
			.append("Model-View binding failed. ")
			.append(" You should invoke ")
			.append(BindManager.class.getSimpleName())
			.append("#bind(View, Object) from the UI thread. ");
			
			throw new IckleBotRuntimeException(new IllegalAccessException(errorContext.toString()));
		}
		
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
			.append(".  Please annotate it using @")
			.append(Model.class.getName());
			
			throw new IllegalArgumentException(errorContext2.toString());
		}
		
		try {
				
			Map<Field, AbstractBinder<? extends View, ? extends Object>> binderMap 
			= binderResolver.resolve(view, model);
			
			Set<Entry<Field, AbstractBinder<? extends View, ? extends Object>>> entries = binderMap.entrySet();
			
			for (Entry<Field, AbstractBinder<? extends View, ? extends Object>> binderEntry : entries) {
					
				try {
				
					AbstractBinder<? extends View, ? extends Object> binder = binderEntry.getValue();
					
					if(binderEntry.getKey().isAnnotationPresent(Expressive.class)) {
						
						if(binder instanceof ExpressiveBindingStrategy) {
							
							ExpressiveBindingStrategy.class.cast(binder).xbind();
						}
						else {
							
							StringBuilder warningContext = new StringBuilder()
							.append("The attribute ")
							.append(binderEntry.getKey().getName())
							.append(" on ")
							.append(model.getClass().getName())
							.append(" cannot be bound expressively. Remove the @Expressive annotation")
							.append(" or use a binder which supports expressive binding. ");
							
							Log.w(getClass().getSimpleName(), warningContext.toString());
						}
					}
					else {
						
						binder.bind();
					}
				}
				catch(BindException be) {
					
					be.printStackTrace();
					
					Log.w(getClass().getSimpleName(), "Bind Failure. ", be);
				}
			}
		}
		catch(BindResolutionException bre) {
			
			bre.printStackTrace();
				
			Log.w(getClass().getSimpleName(), "Bind Resolution Failure. ", bre);
		}
	}
}
