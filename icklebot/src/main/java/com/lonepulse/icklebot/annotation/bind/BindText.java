package com.lonepulse.icklebot.annotation.bind;

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

import android.widget.TextView;

/**
 * <p>Identifies a <i>model attribute</i> whose value is to be bound to any <b>widget</b> 
 * which is an extension of {@link TextView}. The <i>data</i> is obtained by invoking 
 * {@code toString()} on the given {@link Object}, so be sure to override this method 
 * and provide the String to bind the {@link TextView} with if the super implementation 
 * is not satisfactory.
 * 
 * @version 1.2.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindText {

	/**
	 * <p>The {@code integer} arrats which identifies the <b>ID</b>s 
	 * of the <b>widget</b> to bind to which could be any extension 
	 * of {@link TextView}.</p>
	 * 
	 * @return the id of the {@link TextView} extension to bind to
	 * <br><br>
	 * @since 1.2.0
	 */
	int[] value();
}
