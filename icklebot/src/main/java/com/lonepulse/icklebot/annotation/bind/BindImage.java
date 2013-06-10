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

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * <p>Identifies a <i>model attribute</i> whose value is to be bound to an 
 * {@link ImageView}. This binding strategy can be used on a {@link Drawable}, 
 * {@link Bitmap}, {@code byte[]}, {@link Byte}[], integer drawable identifier, 
 * or a <b>Base64</b> encoded {@link String}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindImage {

	
	/**
	 * <p>The {@code integer} which identifies the <b>id</b> of 
	 * the {@link ImageView} to bind to.
	 * 
	 * @return the id of the {@link ImageView} to bind to
	 * <br><br>
	 * @since 1.1.0
	 */
	int value();
}