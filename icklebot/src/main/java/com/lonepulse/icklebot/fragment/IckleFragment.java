package com.lonepulse.icklebot.fragment;

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

import com.lonepulse.icklebot.IckleActivity;

/**
 * <p>All fragments that wish to leverage IckleBot's features should 
 * extend this fragment.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IckleFragment extends EventFragment {


	/**
	 * <p>This callback is executed when the {@link IckleActivity} is being 
	 * created. It invokes the dependency injection and event listener linking 
	 * via the {@link InjectionActivity} and the {@link EventActivity}.</p>
	 * 
	 * <p>See {@link InjectionActivity#onCreate(Bundle)}.</p> 
	 * <p>See {@link EventActivity#onCreate(Bundle)}.</p>
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		long millis = System.currentTimeMillis();
		
		super.onActivityCreated(savedInstanceState);
		
		millis = System.currentTimeMillis() - millis;
		
		Log.i("INSTRUMENTATION:IckleFragment", getClass().getSimpleName() + ": " + millis + "ms");
	}
}
