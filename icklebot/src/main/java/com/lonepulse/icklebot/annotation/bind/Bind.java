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

import android.view.View;

import com.lonepulse.icklebot.bind.AbstractBinder;
import com.lonepulse.icklebot.bind.BindingStrategy;

/**
 * <p>Identifies a <i>model attribute</i> whose value is to be bound to a 
 * <b>widget</b> using a custom binding strategy which can be defined using 
 * and extension of {@link AbstractBinder}.
 * 
 * @version 1.3.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

	/**
	 * <p>The {@code integer} array which identifies the <b>ID</b>s 
	 * of the <b>widget</b>s to bind to.</p>
	 * 
	 * @return the IDs of the widgets to bind to
	 * <br><br>
	 * @since 1.3.0
	 */
	int[] ids();
	
	/**
	 * <p>The {@link Class} of the {@link BindingStrategy} strategy to be used. You can 
	 * create your own binding strategy by implementing an instance of {@link AbstractBinder} 
	 * and declaring the strategy in {@link AbstractBinder#onBind(View, Object)}.</p>
	 *
	 * @return the {@link BindingStrategy} strategy to be used on this attribute.
	 * 
	 * @since 1.1.1
	 */
	Class<? extends AbstractBinder<? extends View, ? extends Object>> type();
}
