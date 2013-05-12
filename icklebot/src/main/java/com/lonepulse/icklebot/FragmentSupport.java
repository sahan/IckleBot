package com.lonepulse.icklebot;

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


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>This contract defines additional support operations offered specifically 
 * on {@link Fragment}s and support {@link android.support.v4.app.Fragment}s.
 * 
 * @version 1.1.0
 */
public interface FragmentSupport {
	
	/**
	 * <p>See {@link android.app.Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}.
	 * 
	 * @since 1.1.0
	 */
	View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
