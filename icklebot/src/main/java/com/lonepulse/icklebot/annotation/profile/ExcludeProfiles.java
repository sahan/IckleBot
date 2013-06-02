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

import com.lonepulse.icklebot.activity.IckleActivity;

/**
 * <p>Allows the declaration of {@link Profile}s which selectively <b>deactivates</b> 
 * the features offered by {@link IckleActivity}.</p> <b>Note</b> that {@code @IncludeProfiles} 
 * takes precedence over {@code @ExcludeProfiles}.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcludeProfiles {
	
	
	/**
	 * <p>The list of {@link View} IDs that executes this method 
	 * as its click event callback.
	 * 
	 * @return the IDs of the {@link View}s that are registered 
	 * 		   for a particular callback
	 * <br><br>
	 * @since 1.1.0
	 */
	Profile[] value();
}
