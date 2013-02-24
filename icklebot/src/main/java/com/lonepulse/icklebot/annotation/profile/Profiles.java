package com.lonepulse.icklebot.annotation.profile;

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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

import com.lonepulse.icklebot.IckleActivity;

/**
 * <p>Allows the declaration of {@link PROFILE}s which selectively activates 
 * the features offered by {@link IckleActivity}.</p> 
 * 
 * <p>If this annotation is not places on an {@link IckleActivity}, all the 
 * profiles declared in {@link PROFILE} is assumed to be active.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Profiles {

	
	/**
	 * <p>Declares all the available profiles which are offered by 
	 * an {@link IckleActivity}.
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static enum PROFILE {
		
		
		/**
		 * <p>Identifies the profile which performs <b>dependency 
		 * injection</b>, <b>activity configuration</b> and<b>state 
		 * management</b>.
		 * 
		 * @since 1.1.0
		 */
		INJECTION,
		
		/**
		 * <p>Identifies the profile which performs <b>event 
		 * listener linking</b>.
		 * 
		 * @since 1.1.0
		 */
		LISTENER,
		
		/**
		 * <p>Identifies the profile which offers the <b>alternative 
		 * threading model</b>.
		 * 
		 * @since 1.1.0
		 */
		THREADING,
	}
	
	/**
	 * <p>The list of {@link View} IDs that executes this method 
	 * as its click event callback.
	 * 
	 * @return the IDs of the {@link View}s that are registered 
	 * 		   for a particular callback
	 * <br><br>
	 * @since 1.1.0
	 */
	PROFILE[] value();
}
