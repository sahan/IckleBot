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
import android.widget.Button;
import android.widget.TextView;

import com.lonepulse.icklebot.bind.AbstractBinder;
import com.lonepulse.icklebot.bind.BasicTextBinder;
import com.lonepulse.icklebot.bind.BindingStrategy;
import com.lonepulse.icklebot.bind.VoidBinder;

/**
 * <p>Identifies a <i>model attribute</i> whose value is to be bound to a <b>widget</b>.
 * 
 * @version 1.2.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

	/**
	 * <p>Indicates the {@link BindingStrategy} strategy to be used for this bind.
	 * 
	 * @version 1.1.0
	 * <br><br> 
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static enum BINDER {
		
		/**
		 * <p>The binding strategy to be employed for {@link TextView}s or any of 
		 * its children (e.g. {@link Button}) to bind {@link String} data.</p>
		 * 
		 * <p><b>Targets:</b><ul><li>any model attribute which provides a meaningful 
		 * {@code toString()} representation.</li></ul></p>
		 * 
		 * @since 1.1.0
		 */
		TEXT(BasicTextBinder.class),
		
		/**
		 * <p>This is the default value for {@link Bind#binder()}. It indicates 
		 * that the binding strategy given in {@link Bind#binderType()} is being 
		 * used, which is {@link Bind.BINDER#TEXT} by default.
		 * 
		 * @since 1.1.0
		 */
		VOID(VoidBinder.class);
		
		
		/**
		 * <p>The {@link Class} of the {@link AbstractBinder} identified by 
		 * this {@link BINDER}.
		 */
		private Class<? extends AbstractBinder<? extends View, ? extends Object>> type;
		

		/**
		 * <p>Instantiates this {@link BINDER} with a {@link Class} 
		 * of the assigned {@link AbstractBinder}.
		 * 
		 * @param type
		 * 			the {@link Class} of the {@link AbstractBinder} identified by 
		 * 			this {@link BINDER}
		 *
		 * @since 1.1.0
		 */
		private BINDER(Class<? extends AbstractBinder<? extends View, ? extends Object>> type) {
			
			this.type = type;
		}

		/**
		 * <p>Accessor for the {@link Class} of the {@link AbstractBinder} identified by 
		 * this {@link BINDER}.
		 *
		 * @return the {@link Class} of the {@link AbstractBinder}
		 * 
		 * @since 1.1.0
		 */
		public Class<? extends AbstractBinder<? extends View, ? extends Object>> getType() {
			
			return type;
		}
	};
	
	/**
	 * <p>The {@code integer} which identifies the <b>id</b> 
	 * of the <b>widget</b> to bind to.</p>
	 * 
	 * @return the id of the view to bind to
	 * <br><br>
	 * @since 1.0.0
	 */
	int value() default 0;
	
	/**
	 * <p>The {@code integer} which identifies the <b>id</b> 
	 * of the <b>widget</b> to bind to.</p>
	 * 
	 * @return the id of the view to bind to
	 * <br><br>
	 * @since 1.2.0
	 */
	int widgetId() default 0;
	
	/**
	 * <p>Identifies an existing binding strategy to be used. This overrides the binding 
	 * strategy specified in {@link #binderType()}.</p> 
	 *
	 * @return the {@link BindingStrategy} strategy to be used on this attribute.
	 * 
	 * @since 1.1.0
	 */
	BINDER binder() default BINDER.VOID;
	
	/**
	 * <p>The {@link Class} of the {@link BindingStrategy} strategy to be used. By default 
	 * the {@link Bind.BINDER#TEXT} strategy is used. You can create your own binding strategy 
	 * by implementing an instance of {@link AbstractBinder} and declaring the strategy in 
	 * {@link AbstractBinder#onBind(View, Object)}.</p>
	 *
	 * @return the {@link BindingStrategy} strategy to be used on this attribute.
	 * 
	 * @since 1.1.0
	 */
	Class<? extends AbstractBinder<? extends View, ? extends Object>> binderType() default BasicTextBinder.class;
}
