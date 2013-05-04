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

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;

import com.lonepulse.icklebot.annotation.inject.Stateful;

/**
 * <p>This contract declares the services which are offered for 
 * <i>saving</i> and <i>restoring</i> the <b>instance state</b> 
 * of an {@link Activity}.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface StateManager {

	/**
	 * <p><b>Saves</b> the <b>instance state</b> of the given 
	 * context by persisting all {@link Field}s annotated with 
	 * {@link Stateful} into the passed {@link Bundle}.</p>
	 * 
	 * @param context
	 * 			the context whose state is to be saved
	 * <br><br>
	 * @param bundle
	 * 			the {@link Bundle} used for persisting the state
	 * <br><br>
	 * @since 1.0.0
	 */
	public abstract void save(Object context, Bundle bundle);
	
	/**
	 * <p><b>Restores</b> the <b>instance state</b> of the given 
	 * context by retrieving all persisted {@link Field}s annotated 
	 * with {@link Stateful} and injecting their values into the 
	 * passed {@link Bundle}.</p>
	 * 
	 * @param context
	 * 			the context whose state is to be saved
	 * <br><br>
	 * @param bundle
	 * 			the {@link Bundle} used for persisting the state
	 * <br><br>
	 * @since 1.0.0
	 */
	public abstract void restore(Object context, Bundle bundle);
}
