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
import android.os.Bundle;
import android.util.Log;

import com.lonepulse.icklebot.annotation.inject.Stateful;
import com.lonepulse.icklebot.util.FieldUtils;

/**
 * <p>A concrete implementation of {@link StateManager} which offers 
 * <b>instance state</b> saves and restores beyond the <i>onCreate()/
 * onDestroy()<i> life-cycle.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class StateService implements StateManager {


	/**
	 * <p>An <i>eager initialized</i> instance of {@link StateService}.</p>
	 * 
	 * @since 1.1.0
	 */
	public static final StateService INSTANCE = new StateService();
	
	
	/**
	 * <p>Instantiation is restricted. Use the {@link #newInstance()} 
	 * or {@link #INSTANCE} instead.
	 */
	private StateService() {}
	
	/**
	 * <p>Creates a new instance of an implementation of 
	 * {@link StateManager}.
	 * 
	 * @return a new implementation of {@link StateManager}.
	 * 
	 * @since 1.1.1
	 */
	public static final StateManager newInstance() {
		
		return new StateService();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Activity activity, Bundle bundle) {
		
		Set<Field> fieldsToSave = FieldUtils.getAllFields(activity, Stateful.class);
		
		for (Field field : fieldsToSave) {
			
			try {
				
				if(!field.isAccessible()) field.setAccessible(true); 
				
				Serializable state = (Serializable)field.get(activity);
				
				bundle.putSerializable(field.getName(), state);
			}
			catch (Exception e) {
				
				StringBuilder stringBuilder = new StringBuilder();
				
				stringBuilder.append("Failed to save state of field ");
				stringBuilder.append(field.getName());
				stringBuilder.append(" in ");
				stringBuilder.append(activity.getClass().getName());
				stringBuilder.append(". ");
				
				Log.e(getClass().getName(), stringBuilder.toString(), e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void restore(Activity activity, Bundle bundle) {
		
		Set<Field> fieldsToRestore = FieldUtils.getAllFields(activity, Stateful.class);
		
		for (Field field : fieldsToRestore) {
			
			try {
				
				if(!field.isAccessible()) field.setAccessible(true);
				
				Serializable state = bundle.getSerializable(field.getName());
				
				field.set(activity, state);
			}
			catch (Exception e) {
				
				StringBuilder stringBuilder = new StringBuilder();
				
				stringBuilder.append("Failed to restore state of field ");
				stringBuilder.append(field.getName());
				stringBuilder.append(" in ");
				stringBuilder.append(activity.getClass().getName());
				stringBuilder.append(". ");
				
				Log.e(getClass().getName(), stringBuilder.toString(), e);
			}
		}
	}
}
