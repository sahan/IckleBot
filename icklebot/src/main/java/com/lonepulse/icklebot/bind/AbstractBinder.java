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


import android.view.View;

import com.lonepulse.icklebot.IckleBotRuntimeException;

/**
 * <p>This is an abstract implementation of a {@link BindingStrategy} which mandates the 
 * use of a parameterized constructor that supplies the arguments to the binding strategy 
 * and it serves as the <b>ViewModel</b>.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class AbstractBinder<V extends View, E> implements BindingStrategy<V, E> {

	
	/**
	 * <p>The <b>widget</b> to which the data is to be bound. This 
	 * will be an instance of {@link View}. 
	 */
	private final V widget;
	
	/**
	 * <p>The data which is to be bound to the <b>widget</b>.
	 */
	private final E data;

	
	/**
	 * <p>Instantiates a new {@link AbstractBinder} with the given 
	 * {@link View} widget and data. 
	 * 
	 * @param widget
	 * 			the {@link View} to which the data is to be bound
	 * 
	 * @param data
	 * 			the data to be bound to the {@link View}
	 *
	 * @since 1.1.0
	 */
	public AbstractBinder(V widget, E data) {
	
		StringBuilder errorContext = new StringBuilder()
		.append("Failed to instantiate a ")
		.append(this.getClass().getName())
		.append(". The argument(s), ");
		
		boolean hasNullArguments = false;
		
		if(widget == null) {
			
			errorContext.append("widget, ");
			hasNullArguments = true;
		}
		
		if(data == null) {
			
			errorContext.append("data, ");
			hasNullArguments = true;
		}
		
		if(hasNullArguments) {
			
			errorContext.append("cannot be null. ");
			throw new IckleBotRuntimeException(new InstantiationException(errorContext.toString()));
		}
		
		this.widget = widget;
		this.data = data;
	}
	
	/**
	 * <p>Retrieves the {@link View} widget to which the data is to be bound. 
	 *
	 * @return the {@link View} widget to which the data is to be bound
	 * 
	 * @since 1.1.0
	 */
	public V getWidget() {
		
		return this.widget;
	}
	
	/**
	 * <p>Retrieves the data which is to be bound. 
	 *
	 * @return the data which is to be bound
	 * 
	 * @since 1.1.0
	 */
	public E getData() {
		
		return this.data;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bind() throws BindException {
		
		try {
			
			onBind(widget, data);
		}
		catch(Exception e) {
			
			throw new BindException(widget, data, e);
		}
	}

	/**
	 * <p>Declares the strategy which will perform a <b>unidirectional binding</b> 
 	 * from data to widget.
	 * 
	 * @param widget
	 * 			the {@link View} widget to which the data is to be bound
	 * 
	 * @param data
	 * 			the data which is to be bound to the {@link View} widget
	 * <br><br>
	 * @since 1.1.0
	 */
	protected abstract void onBind(V widget, E data);
}
