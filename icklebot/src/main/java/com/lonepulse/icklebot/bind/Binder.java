package com.lonepulse.icklebot.bind;

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


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lonepulse.icklebot.annotation.bind.Bind;

/**
 * <p>Identifies the {@link BindingStrategy} strategy to be used.
 * 
 * @version 1.1.2
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum Binder {
	
	/**
	 * <p>The binding strategy to be employed for {@link TextView}s or any of 
	 * its children (e.g. {@link Button}) to bind {@link String} data.</p>
	 * 
	 * <p>This binding strategy supports <b>expressive binding</b>.</p>
	 * 
	 * <p><b>Targets:</b><ul><li>any model attribute which provides a meaningful 
	 * {@code toString()} representation.</li></ul></p>
	 * 
	 * @since 1.1.0
	 */
	TEXT(ExpressiveTextBinder.class),
	
	/**
	 * <p>The binding strategy to be employed for {@link ImageView}s. 
	 * 
	 * <p><b>Targets:</b>
	 * <ul>
	 * 	<li>{@link Drawable}</li>
	 * 	<li>{@link Bitmap}</li>
	 * 	<li>{@code byte[]} - image bytes</li>
	 * 	<li>{@link Byte}[] - images {@link Byte}s</li>
	 * 	<li>{@link int} - drawable identifier</li>
	 * 	<li>{@link Integer} - drawable identifier</li>
	 * 	<li>Base64 encoded {@link String}- Base64 encoded string</li>
	 * </ul>
	 * </p>
	 * 
	 * @since 1.1.1
	 */
	IMAGE(ImageBinder.class),
	
	/**
	 * <p>This is the default value for {@link Bind#binder()}. It indicates 
	 * that the binding strategy given in {@link Bind#binderType()} is being 
	 * used, which is {@link Bind.Binder#TEXT} by default.
	 * 
	 * @since 1.1.0
	 */
	VOID(VoidBinder.class);
	
	
	/**
	 * <p>The {@link Class} of the {@link AbstractBinder} identified by 
	 * this {@link Binder}.
	 */
	private Class<? extends AbstractBinder<? extends View, ? extends Object>> type;
	

	/**
	 * <p>Instantiates this {@link Binder} with a {@link Class} 
	 * of the assigned {@link AbstractBinder}.
	 * 
	 * @param type
	 * 			the {@link Class} of the {@link AbstractBinder} identified by 
	 * 			this {@link Binder}
	 *
	 * @since 1.1.0
	 */
	private Binder(Class<? extends AbstractBinder<? extends View, ? extends Object>> type) {
		
		this.type = type;
	}

	/**
	 * <p>Accessor for the {@link Class} of the {@link AbstractBinder} identified by 
	 * this {@link Binder}.
	 *
	 * @return the {@link Class} of the {@link AbstractBinder}
	 * 
	 * @since 1.1.0
	 */
	public Class<? extends AbstractBinder<? extends View, ? extends Object>> getType() {
		
		return type;
	}
};
