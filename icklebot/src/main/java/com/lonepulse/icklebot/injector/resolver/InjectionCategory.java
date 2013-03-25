package com.lonepulse.icklebot.injector.resolver;

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

import android.app.Application;
import android.graphics.drawable.Drawable;

/**
 * <p>Identifies the <i>category</i> to which a particular 
 * injection operation falls.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum InjectionCategory {

	/**
	 * <p>This <i>category</i> is responsible for identifying an 
	 * {@link Application} instance to be injected.</p>
	 * 
	 * @since 1.1.0 
	 */
	APPLICATION,
	
	/**
	 * <p>This <i>category</i> is responsible for identifying an 
	 * <b>View Android Resource</b> to be injected.</p>
	 * 
	 * @since 1.1.0 
	 */
	RESOURCE_VIEW,
	
	/**
	 * <p>This <i>category</i> is responsible for identifying an 
	 * <b>Integer Android Resource</b> to be injected.</p>
	 * 
	 * @since 1.1.0 
	 */
	RESOURCE_INTEGER,
	
	/**
	 * <p>This <i>category</i> is responsible for identifying a 
	 * <b>String Android Resource</b> to be injected.</p>
	 * 
	 * @since 1.1.0 
	 */
	RESOURCE_STRING,
	
	/**
	 * <p>This <i>category</i> is responsible for identifying a 
	 * <b>{@link Drawable} Android Resource</b> to be injected.</p>
	 * 
	 * @since 1.1.0 
	 */
	RESOURCE_DRAWABLE,
	
	/**
	 * <p>This <i>category</i> is responsible for identifying an 
	 * <b>Android Dimension Resource</b> to be injected.</p>
	 * 
	 * @since 1.1.1 
	 */
	RESOURCE_DIMENSION,
	
	/**
	 * <p>This <i>category</i> is responsible for identifying a 
	 * <b>System Service</b> to be injected.</p>
	 * 
	 * @since 1.1.0 
	 */
	SERVICE,
	
	/**
	 * <p>This <i>category</i> is responsible for identifying a 
	 * <b>Plain Old Java Object (POJO)</b> to be injected.</p>
	 * 
	 * @since 1.1.0 
	 */
	POJO,
	
	/**
	 * <p>Used when the subject does not fall into any of existing 
	 * categories.</p>
	 *
	 * @since 1.1.0
	 */
	NONE;
}
