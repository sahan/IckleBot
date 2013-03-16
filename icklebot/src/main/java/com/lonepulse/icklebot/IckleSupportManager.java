package com.lonepulse.icklebot;

import android.app.Activity;
import android.os.Bundle;

import com.lonepulse.icklebot.event.EventLinker;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.state.StateService;
import com.lonepulse.icklebot.task.TaskManagers;

/**
 * <p>This contract specifies the services offered by a profile. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface IckleSupportManager {
	
	/**
	 * <p>Creates a new instance of {@link IckleSupportManager} by offering a 
	 * a default set of configurations natively in the {@link IckleSupportManager} 
	 * contract.
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static final class Builder {
		
		/**
		 * <p>The {@link Activity} which is to support {@link IckleActivity}'s features.
		 */
		private Activity activity;
		
		/**
		 * <p>A flag to determine if injection is enabled. 
		 */
		private boolean injectionEnabled = false;
		
		/**
		 * <p>A flag to determine if event linking is enabled.
		 */
		private boolean eventLinkingEnabled = false;
		
		/**
		 * <p>Creates a new instance of {@link IckleSupportManager}.
		 * 
		 * @param activity
		 * 			the activity which is requesting an {@link IckleSupportManager}
		 * 
		 * @since 1.1.0
		 */
		public Builder(final Activity activity) {
		
			this.activity = activity;
		}
		
		/**
		 * <p>Adds event linking support to the target activity.
		 * 
		 * @return the {@link Builder} with linking support
		 * 
		 * @since 1.1.0
		 */
		public IckleSupportManager.Builder addEventLinkingSupport() {
			
			eventLinkingEnabled = true;
			return this;
		}
		
		/**
		 * <p>Adds injection support to the target activity.
		 * 
		 * @return the {@link Builder} with event injection support 
		 * 
		 * @since 1.1.0
		 */
		public IckleSupportManager.Builder addInjectionSupport() {
			
			injectionEnabled = true;
			return this;
		}
		
		/**
		 * <p>Creates a new instance of {@link IckleSupportManager} configured 
		 * according to the build parameters which were set.
		 * 
		 * @return an {@link IckleSupportManager} tailored for the requesting activity
		 */
		public IckleSupportManager build() {
			
			if(injectionEnabled) InjectionActivity.inject(Injector.Configuration.getInstance(activity));
			if(eventLinkingEnabled) EventActivity.link(EventLinker.Configuration.getInstance(activity));
			
			return new IckleSupportManager() {
				
				@Override
				public boolean isEventLinkingEnabled() {
					
					return Builder.this.eventLinkingEnabled;
				}
				
				@Override
				public boolean isInjectionEnabled() {
					
					return Builder.this.injectionEnabled;
				}

				@Override
				public void runAsyncTask(int asyncTaskId, Object... args) {

					TaskManagers.ASYNC.execute(activity, asyncTaskId, args);
				}

				@Override
				public void runUITask(int uiTaskId, Object... args) {
					
					TaskManagers.UI.execute(activity, uiTaskId, args);
				}

				@Override
				public void onSaveInstanceState(Bundle outState) {
					
					StateService.getInstance().save(activity, outState);
				}

				@Override
				public void onRestoreInstanceState(Bundle savedInstanceState) {
					
					StateService.getInstance().restore(activity, savedInstanceState);
				}
			};
		}
	}

	
	/**
	 * <p>Determines if event linking is enabled.
	 * 
	 * @return {@code true} if event linking is enabled.
	 * 
	 * @since 1.1.0
	 */
	boolean isEventLinkingEnabled();
	
	/**
	 * <p>Determines if injection is enabled.
	 * 
	 * @return {@code true} if event linking is enabled.
	 * 
	 * @since 1.1.0
	 */
	boolean isInjectionEnabled();
	
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
