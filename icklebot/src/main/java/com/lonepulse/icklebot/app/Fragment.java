package com.lonepulse.icklebot.app;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * <p>This contract defines additional support operations offered specifically 
 * on {@link android.app.Fragment}s and support {@link android.support.v4.app.Fragment}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface Fragment {
	
	/**
	 * <p>See {@link android.app.Fragment#onStart()}.
	 * 
	 * @since 1.1.0
	 */
	void onStart();
	
	/**
	 * <p>See {@link android.app.Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.
	 * 
	 * @since 1.1.0
	 */
	View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
