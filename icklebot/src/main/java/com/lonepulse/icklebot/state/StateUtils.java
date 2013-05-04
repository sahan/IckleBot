package com.lonepulse.icklebot.state;

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


import android.os.Bundle;
import android.util.Log;

import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>This class offers a set of utility operations for state management.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class StateUtils {
	
	
	/**
	 * <p>Constructor visibility. Instantiation is nonsensical.</p>
	 */
	private StateUtils() {}
	
	/**
	 * <p><b>Saves</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	public static void onSaveInstanceState(Object context, Bundle outState) {

		long millis = System.currentTimeMillis();
		
		StateService.newInstance(ContextUtils.discover(context)).save(context, outState);
			
		millis = System.currentTimeMillis() - millis;
			
		Log.i("INSTRUMENTATION:IckleStateProfile#onSaveInstanceState", 
			  StateUtils.class.getClass().getSimpleName() + ": " + millis + "ms");
	}
	
	/**
	 * <p><b>Restores</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	public static void onRestoreInstanceState(Object context, Bundle savedInstanceState) {
		
		long millis = System.currentTimeMillis();
			
		StateService.newInstance(ContextUtils.discover(context)).restore(context, savedInstanceState);
			
		millis = System.currentTimeMillis() - millis;
			
		Log.i("INSTRUMENTATION:IckleStateProfile#onRestoreInstanceState", 
			  StateUtils.class.getClass().getSimpleName() + ": " + millis + "ms");
	}
}
