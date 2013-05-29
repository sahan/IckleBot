package com.lonepulse.icklebot.annotation.bind;

/*
 * #%L
 * IckleBot Library
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

import com.lonepulse.icklebot.bind.AbstractBinding;
import com.lonepulse.icklebot.bind.Binding;
import com.lonepulse.icklebot.bind.TextViewBinding;

/**
 * <p>Identifies a model attribute whose value is to be bound to a view.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

	/**
	 * <p>The {@code integer} which identifies the <b>id</b> 
	 * of the view to bind to.</p>
	 * 
	 * @return the id of the view to bind to
	 * <br><br>
	 * @since 1.0.0
	 */
	int value() default 0;
	
	/**
	 * <p>The {@code integer} which identifies the <b>id</b> 
	 * of the view to bind to.</p>
	 * 
	 * @return the id of the view to bind to
	 * <br><br>
	 * @since 1.0.0
	 */
	int viewId() default 0;
	
	/**
	 * <p>The {@link Class} of the {@link Binding} strategy to be used. By default 
	 * the {@link TextViewBinding} strategy is used. You can create your own binding 
	 * strategy by implementing an instance of {@link AbstractBinding} and declaring 
	 * the strategy in {@link AbstractBinding#onBind(Object, View)}.
	 *
	 * @return the {@link Binding} strategy to be used on this attribute.
	 * 
	 * @since 1.1.0
	 */
	Class<? extends Binding<? extends View, ? extends Object>> strategy() default TextViewBinding.class;
}
