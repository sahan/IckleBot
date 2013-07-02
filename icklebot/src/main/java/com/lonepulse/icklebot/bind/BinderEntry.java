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

import android.view.View;

/**
 * <p>This entity aggregates a {@link Field} and an {@link AbstractBinder}. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BinderEntry {

	
	/**
	 * <p>The {@link Field} to which contains the binding data and metadata. 
	 */
	private Field field;
	
	/**
	 * <p>The {@link AbstractBinder} which performs binding on the resolved 
	 * {@link View} using the data provided by {@link #field}.
	 */
	private AbstractBinder<? extends View, ? extends Object> binder;

	
	/**
	 * <p>Default constructor for lazy initialization when required.
	 *
	 * @since 1.1.0
	 */
	public BinderEntry() {}
	
	/**
	 * <p>Parameterized contstructor whihc initializes {@link #field} and {@link #binder}.
	 * 
	 * @param field
	 * 			the {@link Field} to which contains the binding data and metadata
	 * 
	 * @param binder
	 * 			the {@link AbstractBinder} which performs binding on the resolved 
	 * 			{@link View} using the data provided by {@link #field}
	 *
	 * @since 1.1.0
	 */
	public BinderEntry(Field field, AbstractBinder<? extends View, ? extends Object> binder) {
		
		this.field = field;
		this.binder = binder;
	}

	/**
	 * <p>Accessor for field.
	 *
	 * @return the field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * <p>Mutator for field.
	 *
	 * @param field 
	 *			the field to set
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * <p>Accessor for binder.
	 *
	 * @return the binder
	 */
	public AbstractBinder<? extends View, ? extends Object> getBinder() {
		return binder;
	}

	/**
	 * <p>Mutator for binder.
	 *
	 * @param binder 
	 *			the binder to set
	 */
	public void setBinder(AbstractBinder<? extends View, ? extends Object> binder) {
		this.binder = binder;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("BinderEntry [field=");
		builder.append(field);
		builder.append(", binder=");
		builder.append(binder);
		builder.append("]");
		
		return builder.toString();
	}
}
