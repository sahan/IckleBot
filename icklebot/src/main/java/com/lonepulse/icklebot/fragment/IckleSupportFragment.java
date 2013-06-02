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
import com.lonepulse.icklebot.activity.IckleSupportManager;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.app.Fragment;
import com.lonepulse.icklebot.app.SupportFragment;
import com.lonepulse.icklebot.injector.IllegalContextException;
import com.lonepulse.icklebot.util.ContextUtils;
import com.lonepulse.icklebot.util.TypeUtils;

/**
 * <p>A default concrete implementation of {@link IckleSupportFragment} which provides 
 * additional utilities for supporting IckleBot's features in Fragments 
 * and Support {@link android.support.v4.app.Fragment}s.
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class IckleSupportFragment implements SupportFragment {

	
	/**
	 * <p>The fragment which this {@link IckleFragmentSupporter} serves.
	 */
	private Object fragment;
	
	/**
	 * <p>The {@link IckleSupportManager.Builder} which is configured for this fragment.
	 */
	private IckleSupportManager.Builder supportManagerBuilder;
	
	/**
	 * <p>The {@link IckleSupportManager} which was built using {@link #supportManagerBuilder}.
	 */
	private IckleSupportManager supportManager;
	
	
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
	 * <p>Creates a new IckleBot {@link SupportFragment} which shadows an existing 
	 * {@link android.app.Fragment} or Support {@link android.support.v4.app.Fragment} 
	 * and incorporates the given {@link IckleSupportManager.Builder} into its configuration 
	 * (accessible via {@link #getSupportManager()}). 
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
	public static final SupportFragment shadow(Object fragment, IckleSupportManager.Builder supportManagerBuilder) {
		
		boolean hasIllegalArguments = false;
		StringBuilder errorContext = new StringBuilder();
		
		if(fragment == null) {
			
			errorContext
			.append("Either an instance of ")
			.append(android.app.Fragment.class.getName())
			.append(" or ")
			.append(android.support.v4.app.Fragment.class.getName())
			.append(" must be supplied. ");
			
			hasIllegalArguments = true;
		}
		
		if(supportManagerBuilder == null) {
			
			errorContext
			.append("An instance of ")
			.append(IckleSupportManager.Builder.class.getName())
			.append(" must be supplied. ");
			
			hasIllegalArguments = true;
		}
		
		if(hasIllegalArguments)
			throw new IckleBotRuntimeException(new IllegalArgumentException(errorContext.toString()));
		
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
	 * <p>Creates a new IckleBot {@link SupportFragment} which shadows an existing 
	 * {@link android.app.Fragment} or Support {@link android.support.v4.app.Fragment} 
	 * and incorporates a default {@link IckleSupportManager.Builder} (no features enabled) 
	 * into its configuration (accessible via {@link #getSupportManager()}). 
	 *
	 * @param fragment
	 * 			the fragment or support-fragment which this {@link IckleSupportFragment} 
	 * 			should shadow
	 * 
	 * @return a new instance of {@link SupportFragment}
	 * 
	 * @since 1.1.0
	 */
	public static final SupportFragment shadow(Object fragment) {
		
		return IckleSupportFragment.shadow(fragment, new IckleSupportManager.Builder(fragment));
	}
	
	/**
	 * {@inheritDoc}
	 */
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
			
			Log.e(IckleSupportFragment.class.getSimpleName(), errorContext, e);
		}
		
		return null;
	}

	/**
	 * <p>See {@link android.app.Fragment#onViewCreated(View, Bundle)}.
	 * 
	 * @since 1.1.2
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		supportManager = supportManagerBuilder.build();
	}
	
	/**
	 * <p>See {@link android.app.Fragment#onActivityCreated(Bundle)}.
	 * 
	 * @since 1.1.2
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		supportManager.onRestoreInstanceState(savedInstanceState);
	}

	/**
	 * <p>Retrives the {@link IckleSupportManager} being used by this support 
	 * fragment. The {@link IckleSupportManager} is built during the {@link #onStart()} 
	 * lifecycle-callback. Invoking {@link #getSupportManager()} before this phase 
	 * will result in a {@code null} result.
	 * 
	 * @return the {@link IckleSupportManager} associated with this support fragment, 
	 * 		   else {@code null} if the fragment lifecycle has not yet progressed 
	 * 		   beyond the {@link #onStart()} phase
	 * 
	 * @since 1.1.1
	 */
	@Override
	public IckleSupportManager getSupportManager() {
		
		return supportManager;
	}

	/**
	 * <p>See {@link android.app.Fragment#onSaveInstanceState(Bundle)}.
	 * 
	 * @since 1.1.2
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		
		supportManager.onSaveInstanceState(outState);
	}
}
