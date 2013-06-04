package com.lonepulse.icklebot.util;

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


/**
 * <p>{@link Phases} does not offer a contract and is available as an enum to 
 * oppose the notion of a marker interface. It serves as a contract aggregator 
 * for each life-cycle phase. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
enum Phases { 

	;
	
	/**
	 * <p>This contract declares the services offered for creating 
	 * a stateful object.
	 * 
	 * @since 1.1.0
	 */
	public static interface Create {
		
		void onCreate();
	}
	
	/**
	 * <p>This contract declares the services offered for destroying 
	 * a stateful object.
	 * 
	 * @since 1.1.0
	 */
	public static interface Destroy {
		
		void onDestroy();
	}
}

