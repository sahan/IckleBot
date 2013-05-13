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


import java.util.HashSet;
import java.util.Set;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.injector.IllegalContextException;
import com.lonepulse.icklebot.util.ContextUtils;
import com.lonepulse.icklebot.util.TypeUtils;

/**
 * <p>This class offers a set of utility operations on fragments.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class FragmentUtils {
	
	
	/**
	 * <p>Constructor visibility. Instantiation is nonsensical.</p>
	 */
	private FragmentUtils() {}

	/**
	 * <p>Determines the {@link View} to be returned depending on the layout configuration 
	 * on the given fragment. If not layout configuration is specified, the super-type's method 
	 * is invoked.</p>
	 *
	 * @param fragment
	 * 			the fragment whose view is to be resolved
	 *
	 * <p>See Fragment#onCreateView(LayoutInflater, ViewGroup, Bundle).</p>
	 * 
	 * @return the resolved {@link View}, else {@code null} if resolution failed
	 * <br><br> 
	 * @since 1.1.0
	 */
	public static View onCreateView(Object fragment, LayoutInflater inflater, 
									ViewGroup container, Bundle savedInstanceState) {
		
		
		try {
			
			boolean isFragment = ContextUtils.isFragment(fragment);
			boolean isSupportFragment = ContextUtils.isSupportFragment(fragment);
		
			if(isFragment || isSupportFragment) {
	
				Layout layout = TypeUtils.getAnnotation(fragment, Layout.class);
				
				if(layout != null) {
					
					return inflater.inflate(layout.value(), null);
				}
				else {
					
					//TODO here!!
				}
			}
			else {
			
				Set<Class<?>> applicableContexts = new HashSet<Class<?>>();
				applicableContexts.add(Fragment.class);
				applicableContexts.add(android.support.v4.app.Fragment.class);
				
				throw new IllegalContextException(fragment, applicableContexts);
			}
		}
		catch(Exception e) {
			
			String errorContext = new StringBuilder()
			.append("Layout resolution failed on ")
			.append(fragment.getClass().getName())
			.append(". ")
			.toString();
			
			Log.e(FragmentUtils.class.getSimpleName(), errorContext, e);
		}
		
		return null;
	}
}
