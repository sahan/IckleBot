package com.lonepulse.icklebot.profile;

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


import com.lonepulse.icklebot.activity.IckleActivity;

/**
 * <p>Declares all the available profiles which are offered by 
 * an {@link IckleActivity}.
 * 
 * @version 2.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum Profile {
	
	/**
	 * <p>Identifies the profile which performs <b>state 
	 * management</b>.
	 * 
	 * @since 1.1.0
	 */
	STATE,
	
	/**
	 * <p>Identifies the profile which performs <b>dependency 
	 * injection</b> and <b>activity configuration</b>.
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
	EVENT,
	
	/**
	 * <p>Identifies the profile which offers the <b>alternative 
	 * threading model</b>.
	 * 
	 * @since 1.1.0
	 */
	THREADING,
	
	/**
	 * <p>Identifies the profile which detects changes in the 
	 * data connection.</p>
	 * 
	 * <p>This profile requires the following permission:
	 * <ul>
	 *  <li>READ_PHONE_STATE: to register for data state changes.</li>
	 * </ul>
	 * </p>
	 * 
	 * @since 1.1.1
	 */
	NETWORK
}

