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

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lonepulse.icklebot.IckleBotRuntimeException;
import com.lonepulse.icklebot.IckleSupportManager;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.app.Fragment;
import com.lonepulse.icklebot.injector.IllegalContextException;
import com.lonepulse.icklebot.util.ContextUtils;
import com.lonepulse.icklebot.util.TypeUtils;

/**
 * <p>A default concrete implementation of {@link IckleSupportFragment} which provides 
 * additional utilities for supporting IckleBot's features in Fragments 
 * and Support {@link android.support.v4.app.Fragment}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IckleSupportFragment implements Fragment {

	
	/**
	 * <p>The fragment which this {@link IckleFragmentSupporter} serves.
	 */
	private Object fragment;
	
	/**
	 * <p>The {@link IckleSupportManager.Builder} which is configured for this fragment.
	 */
	private IckleSupportManager.Builder supportManagerBuilder;
	
	
	/**
	 * <p>Creates a new fragment supporter with the given context.
	 * 
	 * @param fragment
	 * 			the fragment or support-fragment which this {@link IckleFragmentSupporter} 
	 * 			is to serve
	 * 
	 * @param supportManagerBuilder
	 * 			the unbuilt {@link IckleSupportManager.Builder} which is configured for the 
	 * 			fragment which this {@link IckleSupportFragment} is to shadow 
	 */
	private IckleSupportFragment(Object fragment, IckleSupportManager.Builder supportManagerBuilder) {
	
		this.fragment = fragment;
		this.supportManagerBuilder = supportManagerBuilder;
	}
	
	/**
	 * <p>Creates a new IckleBot {@link Fragment} which shadows an existing 
	 * {@link android.app.Fragment} or Support {@link android.support.v4.app.Fragment} 
	 * and incorporates 
	 *
	 * @param fragment
	 * 			the fragment or support-fragment which this {@link IckleSupportFragment} 
	 * 			should shadow
	 * 
	 * @param supportManagerBuilder
	 * 			the {@link IckleSupportManager.Builder} which is configured for the fragment 
	 * 			which this {@link IckleSupportFragment} is to shadow
	 * 
	 * @return a new instance of {@link Fragment}
	 * 
	 * @since 1.1.0
	 */
	public static final Fragment shadow(Object fragment, IckleSupportManager.Builder supportManagerBuilder) {
		
		StringBuilder errorContext = new StringBuilder();
		
		if(fragment == null) {
			
			errorContext
			.append("Either an instance of ")
			.append(android.app.Fragment.class.getName())
			.append(" or ")
			.append(android.support.v4.app.Fragment.class.getName())
			.append(" must be supplied. ");
			
			throw new IckleBotRuntimeException(new IllegalArgumentException(errorContext.toString()));
		}
		
		if(supportManagerBuilder != null && supportManagerBuilder.isBuilt()) {
			
			errorContext
			.append("The provided ")
			.append(IckleSupportManager.Builder.class.getName())
			.append(" has already been built. These builders are non-reusable");
			
			throw new IckleBotRuntimeException(new IllegalStateException(errorContext.toString()));
		}
		
		return new IckleSupportFragment(fragment, supportManagerBuilder);
	}
	
	/**
	 * <p>Creates a new IckleBot {@link Fragment} which shadows an existing 
	 * {@link android.app.Fragment} or Support {@link android.support.v4.app.Fragment}
	 *
	 * @param fragment
	 * 			the fragment or support-fragment which this {@link IckleSupportFragment} 
	 * 			should shadow
	 * 
	 * @return a new instance of {@link Fragment}
	 * 
	 * @since 1.1.0
	 */
	public static final Fragment shadow(Object fragment) {
		
		return IckleSupportFragment.shadow(fragment, null);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		try {
			
			boolean isFragment = ContextUtils.isFragment(fragment);
			boolean isSupportFragment = ContextUtils.isSupportFragment(fragment);
		
			if(isFragment || isSupportFragment) {
	
				Layout layout = TypeUtils.getAnnotation(fragment, Layout.class);
				
				if(layout != null)
					return inflater.inflate(layout.value(), null);
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

	@Override
	public void onStart() {

		if(supportManagerBuilder != null)
			supportManagerBuilder.build();
	}
}
