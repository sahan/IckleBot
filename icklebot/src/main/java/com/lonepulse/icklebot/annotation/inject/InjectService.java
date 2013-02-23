package com.lonepulse.icklebot.annotation.inject;

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

import android.app.Activity;
import android.content.Context;

/**
 * <p>This annotation marks a <b>System Service</b> which is to be 
 * injected into an {@link Activity}.</p>
 * 
 * <p>System services are identified using their constant names 
 * declared in {@link Context}.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectService {
	
	/**
	 * <p>The {@code String} which identifies the <b>constant name</b> 
	 * of the System Service as declared in {@link Context}.</p>
	 * 
	 * @return the constant name of the System Service
	 * <br><br>
	 * @since 1.0.0
	 */
	String value();
}
