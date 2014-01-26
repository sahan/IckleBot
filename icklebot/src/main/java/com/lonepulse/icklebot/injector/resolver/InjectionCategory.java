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

import java.lang.annotation.Annotation;

import android.animation.Animator;
import android.app.Application;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;

import com.lonepulse.icklebot.annotation.inject.IgnoreInjection;
import com.lonepulse.icklebot.annotation.inject.InjectAnimation;
import com.lonepulse.icklebot.annotation.inject.InjectAnimator;
import com.lonepulse.icklebot.annotation.inject.InjectApplication;
import com.lonepulse.icklebot.annotation.inject.InjectArray;
import com.lonepulse.icklebot.annotation.inject.InjectBoolean;
import com.lonepulse.icklebot.annotation.inject.InjectColor;
import com.lonepulse.icklebot.annotation.inject.InjectDimension;
import com.lonepulse.icklebot.annotation.inject.InjectDrawable;
import com.lonepulse.icklebot.annotation.inject.InjectIckleService;
import com.lonepulse.icklebot.annotation.inject.InjectInteger;
import com.lonepulse.icklebot.annotation.inject.InjectPojo;
import com.lonepulse.icklebot.annotation.inject.InjectString;
import com.lonepulse.icklebot.annotation.inject.InjectSystemService;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.injector.InjectionProvider;

/**
 * <p>Identifies the <i>category</i> associated with a dependency request. This is used to resolve the 
 * {@link InjectionProvider.InjectionProvider} used for injection.</p>
 * 
 * @version 1.4.0
 * <br><br>
 * @since 1.0.0
 * <br><br>
 * @author <a href="http://sahan.me">Lahiru Sahan Jayasinghe</a>
 */
public enum InjectionCategory {

	/**
	 * <p>Identifies a dependency for the {@link Application} context.</p>
	 * 
	 * @since 1.0.0 
	 */
	APPLICATION(InjectApplication.class),
	
	/**
	 * <p>Identifies a dependency for an inflated layout.</p>
	 * 
	 * @since 1.1.0 
	 */
	LAYOUT(Layout.class),
	
	/**
	 * <p>Identifies a dependency for an inflated {@link View}.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_VIEW(InjectView.class),
	
	/**
	 * <p>Identifies a dependency for an {@code int} or {@link Integer} resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_INTEGER(InjectInteger.class),
	
	/**
	 * <p>Identifies a dependency for a {@link String} resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_STRING(InjectString.class),
	
	/**
	 * <p>Identifies a dependency for a {@link Drawable} resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_DRAWABLE(InjectDrawable.class),
	
	/**
	 * <p>Identifies a dependency for a dimension resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_DIMENSION(InjectDimension.class),
	
	/**
	 * <p>Identifies a dependency for a color resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_COLOR(InjectColor.class),
	
	/**
	 * <p>Identifies a dependency for a {@code boolean} or {@link Boolean} resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_BOOLEAN(InjectBoolean.class),
	
	/**
	 * <p>Identifies a dependency for an array resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_ARRAY(InjectArray.class),
	
	/**
	 * <p>Identifies a dependency for an {@link Animation} resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_ANIMATION(InjectAnimation.class),
	
	/**
	 * <p>Identifies a dependency for an {@link Animator} resource.</p>
	 * 
	 * @since 1.0.0 
	 */
	RESOURCE_ANIMATOR(InjectAnimator.class),
	
	/**
	 * <p>Identifies a dependency for a system service.</p>
	 * 
	 * @since 1.0.0 
	 */
	SYSTEM_SERVICE(InjectSystemService.class),
	
	/**
	 * <p>Identifies a dependency for one of IckleBot's own services.</p>
	 * 
	 * @since 1.0.0 
	 */
	ICKLE_SERVICE(InjectIckleService.class),
	
	/**
	 * <p>Identifies a dependency for a simple POJO.</p>
	 * 
	 * @since 1.0.0 
	 */
	POJO(InjectPojo.class),
	
	/**
	 * <p>Identifies a void request a dependency or an actively ignored injection.</p>
	 * 
	 * @since 1.0.0 
	 */
	NONE(IgnoreInjection.class);

	
	
	private Class<? extends Annotation> annotation;
	
	
	private InjectionCategory(Class<? extends Annotation> annotation) {

		this.annotation = annotation;
	}

	/**
	 * <p>Retrieves the {@link Class} of the annotation which identifies this {@link InjectionCategory} 
	 * in a context which has requested <i>explicit injection</i>.</p>
	 *
	 * @return the annotation {@link Class} which explicitly identifies this {@link InjectionCategory} 
	 * <br><br>
	 * @since 1.2.1
	 */
	public Class<? extends Annotation> getAnnotation() {
		
		return annotation;
	}
}
