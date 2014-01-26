package com.lonepulse.icklebot.activity;

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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.lonepulse.icklebot.event.EventLinker;
import com.lonepulse.icklebot.event.EventUtils;
import com.lonepulse.icklebot.injector.InjectionUtils;
import com.lonepulse.icklebot.injector.InjectionProvider;
import com.lonepulse.icklebot.state.StateUtils;
import com.lonepulse.icklebot.task.TaskUtils;
import com.lonepulse.icklebot.util.ContextUtils;

/**
 * <p>This contract specifies the services offered by a support manager which 
 * aims to supplement an {@link Activity} with IckleBot's features even though 
 * it cannot extend {@link IckleActivity}.</p> 
 * 
 * <p>Use {@link IckleSupportManager.Builder} to create your support manager 
 * tailored for a particular {@link Activity}.</p>
 * 
 * <p>{@link IckleSupportManager}s can be serialized and as such they can be 
 * {@link Bundle}d.</p>
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface IckleSupportManager extends Serializable {
	
	/**
	 * <p>Creates a new instance of {@link IckleSupportManager} by offering a 
	 * a default set of configurations natively in the {@link IckleSupportManager} 
	 * contract.</p>
	 *  
	 * <p>This builder is not reusable after {@link IckleSupportManager.Builder#build()} 
	 * has already been invoked.</p>
	 * 
	 * @version 1.1.1
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Builder {
		
		/**
		 * <p>The contextual error message in case of state corruption.
		 */
		private static final StringBuilder errorContext;
		
		static
		{
			errorContext = new StringBuilder()
			.append(IckleSupportManager.class.getSimpleName())
			.append("'s builder is not reusable. ");
		}
		
		/**
		 * <p>The context which is to support IckleBot's features.
		 */
		private Object context;
		
		/**
		 * <p>A flag to determine if injection support is enabled. 
		 */
		private boolean injectionSupportEnabled = false;
		
		/**
		 * <p>A flag to determine if event support is enabled.
		 */
		private boolean eventSupportEnabled = false;
		
		/**
		 * <p>This flag determines if an {@link IckleSupportManager} has already 
		 * been built using this instance of {@link IckleSupportManager.Builder}. 
		 */
		private volatile boolean built = false;
		
		
		/**
		 * <p>Creates a new instance of {@link IckleSupportManager} by taking 
		 * any mandatory parameters.
		 * 
		 * @param context
		 * 			the context which is requesting an {@link IckleSupportManager}
		 * 
		 * @since 1.1.0
		 */
		public Builder(final Object context) {
		
			this.context = context;
		}
		
		/**
		 * <p>Determines if this {@link IckleSupportManager.Builder} has already been built.
		 *
		 * @return {@code true} if this {@link IckleSupportManager.Builder} has been built
		 * 
		 * @since 1.1.1
		 */
		public boolean isBuilt() {
			
			return built;
		}

		/**
		 * <p>Adds event linking support to the target context.
		 * 
		 * @return the {@link Builder} with event linking support
		 * 
		 * @throws IllegalStateException
		 * 			if this method is invoked after the {@link IckleSupportManager} 
		 * 			has already been built using {@link #build()}
		 * 
		 * @since 1.1.0
		 */
		public IckleSupportManager.Builder enableEventSupport() {
			
			if(built) throw new IllegalStateException(errorContext.toString());
			
			eventSupportEnabled = true;
			return this;
		}
		
		/**
		 * <p>Adds injection support to the target context.
		 * 
		 * @return the {@link Builder} with injection support
		 * 
		 * @throws IllegalStateException
		 * 			if this method is invoked after the {@link IckleSupportManager} 
		 * 			has already been built using {@link #build()}
		 * 
		 * @since 1.1.0
		 */
		public IckleSupportManager.Builder enableInjectionSupport() {
			
			if(built) throw new IllegalStateException(errorContext.toString());
			
			injectionSupportEnabled = true;
			return this;
		}
		
		/**
		 * <p>Creates a new instance of {@link IckleSupportManager} configured 
		 * according to the build parameters which were set.
		 * 
		 * @return an {@link IckleSupportManager} tailored for the requesting context
		 * 
		 * @throws IllegalStateException
		 * 			if the {@link IckleSupportManager} has already been built
		 * 
		 * @since 1.1.0
		 */
		public IckleSupportManager build() {
			
			if(built) {
				
				throw new IllegalStateException(errorContext.toString());
			}
			else {
				
				built = true;
			}
			
			if(injectionSupportEnabled) InjectionUtils.inject(InjectionProvider.Configuration.newInstance(context));
			if(eventSupportEnabled) EventUtils.link(EventLinker.Configuration.newInstance(context));
			
			return new IckleSupportManager() {
				
				private static final long serialVersionUID = 5949321867738227878L;

				private Context baseContext = ContextUtils.discover(context);
				
				@Override
				public Context getBaseContext() {

					return baseContext;
				}
				
				@Override
				public boolean isEventSupportEnabled() {
					
					return eventSupportEnabled;
				}
				
				@Override
				public boolean isInjectionSupportEnabled() {
					
					return injectionSupportEnabled;
				}

				@Override
				public void runAsyncTask(int asyncTaskId, Object... args) {

					TaskUtils.runAsyncTask(context, asyncTaskId, args);
				}

				@Override
				public void runUITask(int uiTaskId, Object... args) {
					
					TaskUtils.runUITask(context, uiTaskId, args);
				}

				@Override
				public void onSaveInstanceState(Bundle outState) {
					
					StateUtils.onSaveInstanceState(context, outState);
				}

				@Override
				public void onRestoreInstanceState(Bundle savedInstanceState) {
					
					StateUtils.onRestoreInstanceState(context, savedInstanceState);
				}
			};
		}
	}
	
	/**
	 * <p>Retrieves the base context of the context which 
	 * is supported by this {@link IckleSupportManager}.
	 * 
	 * @return the base context of the supported context
	 * 
	 * @version 1.1.0
	 */
	Context getBaseContext();
	
	/**
	 * <p>Determines if event linking is enabled.
	 * 
	 * @return {@code true} if event linking is enabled.
	 * 
	 * @since 1.1.0
	 */
	boolean isEventSupportEnabled();
	
	/**
	 * <p>Determines if injection is enabled.
	 * 
	 * @return {@code true} if event linking is enabled.
	 * 
	 * @since 1.1.0
	 */
	boolean isInjectionSupportEnabled();
	
	/**
	 * <p>Executes a method annotated with {@code @Async} on a background worker thread.
	 * 
	 * @param asyncTaskId
	 * 			the ID of the method to be in the background
	 * 
	 * @param args
	 * 			any arguments to be supplied to the method
	 * 
	 * @since 1.1.0
	 */
	void runAsyncTask(int asyncTaskId, Object... args);
	
	/**
	 * <p>Posts a method annotated with {@code @UI} on the UI event loop.
	 * 
	 * @param uiTaskId
	 * 			the ID of the method to be run
	 * 
	 * @param args
	 * 			any arguments to be supplied to the method
	 * 
	 * @since 1.1.0
	 */
	void runUITask(int uiTaskId, final Object... args);
	
	/**
	 * <p><b>Saves</b> instance variables annotated with {@code @Stateful}.
	 * 
	 * @param outState
	 * 			the {@link Bundle} which wraps the state to be saved
	 * 
	 * @since 1.1.0
	 */
	void onSaveInstanceState(Bundle outState);
	
	/**
	 * <p><b>Restores</b> instance variables annotated with {@code @Stateful}.</p>
	 * 
	 * @param savedInstanceState
	 * 			the {@link Bundle} which wraps the state to be restored
	 * 
	 * @since 1.1.0
	 */
	void onRestoreInstanceState(Bundle savedInstanceState);
}
