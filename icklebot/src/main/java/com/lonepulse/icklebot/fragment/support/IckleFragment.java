package com.lonepulse.icklebot.fragment.support;

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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.util.TypeUtils;

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
	 * <p>Uses the {@code @Layout} metadata to identify the view resource associated 
	 * with this {@link Fragment} and inflate it.</p>
	 * 
	 * <p>See {@link Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.</p>
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		Layout layout = TypeUtils.getAnnotation(this, Layout.class);
		
		if(layout != null) {
			
			return inflater.inflate(layout.value(), null);
		}
		else {
			
			return super.onCreateView(inflater, container, savedInstanceState);
		}
	}

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
		
		Log.i("INSTRUMENTATION:IckleSupportFragment", getClass().getSimpleName() + ": " + millis + "ms");
	}
}
