package com.lonepulse.icklebot;

import java.io.Serializable;

import android.app.Activity;
import android.os.Bundle;

import com.lonepulse.icklebot.event.EventLinker;
import com.lonepulse.icklebot.injector.Injector;
import com.lonepulse.icklebot.state.StateService;
import com.lonepulse.icklebot.task.TaskManagers;

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
 * @version 1.1.0
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
	 * @version 1.1.0
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
		 * <p>This flag determines if an {@link IckleSupportManager} has already 
		 * been built using this instance of {@link IckleSupportManager.Builder}. 
		 */
		private transient volatile boolean built = false;
		
		
		/**
		 * <p>Creates a new instance of {@link IckleSupportManager} by taking 
		 * any mandatory parameters.
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
		 * @throws IllegalStateException
		 * 			if this method is invoked after the {@link IckleSupportManager} 
		 * 			has already been built using {@link #build()}
		 * 
		 * @since 1.1.0
		 */
		public IckleSupportManager.Builder addEventLinkingSupport() {
			
			if(built) throw new IllegalStateException(errorContext.toString());
			
			eventLinkingEnabled = true;
			return this;
		}
		
		/**
		 * <p>Adds injection support to the target activity.
		 * 
		 * @return the {@link Builder} with event injection support
		 * 
		 * @throws IllegalStateException
		 * 			if this method is invoked after the {@link IckleSupportManager} 
		 * 			has already been built using {@link #build()}
		 * 
		 * @since 1.1.0
		 */
		public IckleSupportManager.Builder addInjectionSupport() {
			
			if(built) throw new IllegalStateException(errorContext.toString());
			
			injectionEnabled = true;
			return this;
		}
		
		/**
		 * <p>Creates a new instance of {@link IckleSupportManager} configured 
		 * according to the build parameters which were set.
		 * 
		 * @return an {@link IckleSupportManager} tailored for the requesting activity
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
			
			if(injectionEnabled) InjectionActivity.inject(Injector.Configuration.getInstance(activity));
			if(eventLinkingEnabled) EventActivity.link(EventLinker.Configuration.getInstance(activity));
			
			return new IckleSupportManager() {
				
				private static final long serialVersionUID = 5949321867738227878L;

				@Override
				public boolean isEventLinkingEnabled() {
					
					return eventLinkingEnabled;
				}
				
				@Override
				public boolean isInjectionEnabled() {
					
					return injectionEnabled;
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
