package com.lonepulse.icklebot.annotation.thread;

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

/**
 * <p>Marks a method which should run on the <i>UI Event Loop</i>.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UI {
	
	/**
	 * <p>The {@code integer} which identifies the <b>id</b> 
	 * of the UI task.</p>
	 * 
	 * @return the id of the UI task
	 * <br><br>
	 * @since 1.0.0
	 */
	int value();
	
	/**
	 * <p>A {@code long} value which specifies the <i>delay</i> 
	 * in executing the UI task. No delay is used by default.
	 * 
	 * @return the delay before UI task execution
	 * <br><br>
	 * @since 1.0.0
	 */
	long delay() default 0l;
}
