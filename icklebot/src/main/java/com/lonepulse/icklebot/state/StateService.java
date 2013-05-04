package com.lonepulse.icklebot.state;

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

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Set;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.Stateful;
import com.lonepulse.icklebot.util.FieldUtils;

/**
 * <p>A concrete implementation of {@link StateManager} which offers 
 * <b>instance state</b> saves and restores beyond the <i>onCreate()/
 * onDestroy()<i> life-cycle.</p>
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class StateService implements StateManager {


	private static volatile StateManager instance;
	

	/**
	 * <p>The base {@link Context} in which this service is 
	 * being initialized. 
	 */
	@SuppressWarnings("unused") //to guard against future contract-break
	private Context context;
	
	
	/**
	 * <p>Instantiation is restricted. Use the {@link #newInstance()} 
	 * or {@link #getInstance()} instead.
	 */
	private StateService(Context context) {
		
		this.context = context; 
	}
	
	/**
	 * <p>Creates a new instance of an implementation of {@link StateManager}.
	 * 
	 * @param application
	 * 			the {@link Context} of the {@link Application} instance via 
	 * 			{@link Activity#getApplication()} or {@link Activity#getApplicationContext()}
	 * 
	 * @return a new instance of {@link StateManager}.
	 * 
	 * @since 1.1.1
	 */
	public static final synchronized StateManager getInstance(Context applicationContext) {
		
		return (instance == null)? (instance = new StateService(applicationContext)) :instance;
	}
	
	/**
	 * <p>Creates a new instance of an implementation of 
	 * {@link StateManager}.
	 * 
	 * @param context
	 * 			the context in which the service is instantiated
	 * 
	 * @return a new instance of {@link StateService}.
	 * 
	 * @since 1.1.1
	 */
	public static final StateManager newInstance(Context context) {
		
		return new StateService(context);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Object context, Bundle bundle) {
		
		Set<Field> fieldsToSave = FieldUtils.getAllFields(context, Stateful.class);
		
		for (Field field : fieldsToSave) {
			
			try {
				
				if(!field.isAccessible()) field.setAccessible(true); 
				
				Serializable state = (Serializable)field.get(context);
				
				bundle.putSerializable(field.getName(), state);
			}
			catch (Exception e) {
				
				StringBuilder stringBuilder = new StringBuilder();
				
				stringBuilder.append("Failed to save state of field ");
				stringBuilder.append(field.getName());
				stringBuilder.append(" in ");
				stringBuilder.append(context.getClass().getName());
				stringBuilder.append(". ");
				
				Log.e(getClass().getName(), stringBuilder.toString(), e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restore(Object context, Bundle bundle) {
		
		Set<Field> fieldsToRestore = FieldUtils.getAllFields(context, Stateful.class);
		
		for (Field field : fieldsToRestore) {
			
			try {
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				Serializable state = bundle.getSerializable(field.getName());
				
				field.set(context, state);
			}
			catch (Exception e) {
				
				StringBuilder stringBuilder = new StringBuilder();
				
				stringBuilder.append("Failed to restore state of field ");
				stringBuilder.append(field.getName());
				stringBuilder.append(" in ");
				stringBuilder.append(context.getClass().getName());
				stringBuilder.append(". ");
				
				Log.e(getClass().getName(), stringBuilder.toString(), e);
			}
		}
	}
}
