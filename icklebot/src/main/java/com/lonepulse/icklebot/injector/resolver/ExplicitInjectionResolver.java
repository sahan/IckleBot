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

import java.lang.reflect.Field;

import android.content.Context;

import com.lonepulse.icklebot.annotation.inject.InjectAnimation;
import com.lonepulse.icklebot.annotation.inject.InjectAnimator;
import com.lonepulse.icklebot.annotation.inject.InjectApplication;
import com.lonepulse.icklebot.annotation.inject.InjectArray;
import com.lonepulse.icklebot.annotation.inject.InjectBoolean;
import com.lonepulse.icklebot.annotation.inject.InjectColor;
import com.lonepulse.icklebot.annotation.inject.InjectDimension;
import com.lonepulse.icklebot.annotation.inject.InjectDrawable;
import com.lonepulse.icklebot.annotation.inject.InjectInteger;
import com.lonepulse.icklebot.annotation.inject.InjectPojo;
import com.lonepulse.icklebot.annotation.inject.InjectService;
import com.lonepulse.icklebot.annotation.inject.InjectString;
import com.lonepulse.icklebot.annotation.inject.InjectView;

/**
 * <p>An implementation of {@link InjectionResolver} which caters to 
 * <b>Explicit Injections</b> using any of the <i>@Inject...</i> annotations.</p>
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ExplicitInjectionResolver implements InjectionResolver {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InjectionCategory resolve(Context context, Field field) {
		
		if(field.isAnnotationPresent(InjectApplication.class))
			return InjectionCategory.APPLICATION;
			
		else if(field.isAnnotationPresent(InjectView.class))
			return InjectionCategory.RESOURCE_VIEW;
		
		else if(field.isAnnotationPresent(InjectInteger.class))
			return InjectionCategory.RESOURCE_INTEGER;
		
		else if(field.isAnnotationPresent(InjectString.class))
			return InjectionCategory.RESOURCE_STRING;
		
		else if(field.isAnnotationPresent(InjectDrawable.class))
			return InjectionCategory.RESOURCE_DRAWABLE;
		
		else if(field.isAnnotationPresent(InjectColor.class))
			return InjectionCategory.RESOURCE_COLOR;
		
		else if(field.isAnnotationPresent(InjectDimension.class))
			return InjectionCategory.RESOURCE_DIMENSION;
		
		else if(field.isAnnotationPresent(InjectBoolean.class))
			return InjectionCategory.RESOURCE_BOOLEAN;
		
		else if(field.isAnnotationPresent(InjectArray.class))
			return InjectionCategory.RESOURCE_ARRAY;
		
		else if(field.isAnnotationPresent(InjectAnimation.class))
			return InjectionCategory.RESOURCE_ANIMATION;
		
		else if(field.isAnnotationPresent(InjectAnimator.class))
			return InjectionCategory.RESOURCE_ANIMATOR;
			
		else if(field.isAnnotationPresent(InjectPojo.class))
			return InjectionCategory.POJO;
		
		else if(field.isAnnotationPresent(InjectService.class))
			return InjectionCategory.SERVICE;
		
		return InjectionCategory.NONE;
	}
}
