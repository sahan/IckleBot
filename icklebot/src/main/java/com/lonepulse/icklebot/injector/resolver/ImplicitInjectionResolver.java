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

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;

import com.lonepulse.icklebot.annotation.inject.ApplicationContract;
import com.lonepulse.icklebot.annotation.inject.IckleService;
import com.lonepulse.icklebot.annotation.inject.IgnoreInjection;
import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Pojo;
import com.lonepulse.icklebot.event.resolver.EventResolver;
import com.lonepulse.icklebot.util.ContextUtils;
import com.lonepulse.icklebot.util.ReflectiveR;

/**
 * <p>An implementation of {@link EventResolver} which caters to 
 * <b>Implicit Injections</b> activated via {@link InjectAll}.</p>
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class ImplicitInjectionResolver implements InjectionResolver {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InjectionCategory resolve(Object context, Field field) {
		
		Context baseContext = ContextUtils.discover(context);
		
		if(field.isAnnotationPresent(IgnoreInjection.class))
			return InjectionCategory.NONE;
		
		else if(isCategoryApplication(field))
			return InjectionCategory.APPLICATION;
		
		else if(isCategoryResourceView(field))
			return InjectionCategory.RESOURCE_VIEW;
		
		else if(isCategoryResourceInteger(baseContext, field))
			return InjectionCategory.RESOURCE_INTEGER;
		
		else if(isCategoryResourceString(field))
			return InjectionCategory.RESOURCE_STRING;
		
		else if(isCategoryResourceDrawable(field))
			return InjectionCategory.RESOURCE_DRAWABLE;
		
		else if(isCategoryResourceColor(baseContext, field)) 
			return InjectionCategory.RESOURCE_COLOR;
		
		else if(isCategoryResourceDimension(field))
			return InjectionCategory.RESOURCE_DIMENSION;
		
		else if(isCategoryResourceBoolean(field))
			return InjectionCategory.RESOURCE_BOOLEAN;
		
		else if(isCategoryResourceArray(field))
			return InjectionCategory.RESOURCE_ARRAY;
		
		else if(isCategoryResourceAnimation(field))
			return InjectionCategory.RESOURCE_ANIMATION;
		
		else if(isCategoryResourceAnimator(field))
			return InjectionCategory.RESOURCE_ANIMATOR;
		
		else if(isCategoryPojo(field))
			return InjectionCategory.POJO;
		
		else if(isCategorySystemService(field))
			return InjectionCategory.SYSTEM_SERVICE;
		
		else if(isCategoryIckleService(field))
			return InjectionCategory.ICKLE_SERVICE;
		
		return InjectionCategory.NONE;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#APPLICATION}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#APPLICATION}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryApplication(Field field) {
	
		return field.getType().isAnnotationPresent(ApplicationContract.class);
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_VIEW}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_VIEW}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceView(Field field) {
		
		return (View.class.isAssignableFrom(field.getType())
				&& !field.isAnnotationPresent(Layout.class))? true :false; 
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_INTEGER}.</p>
	 * 
	 * @param context
	 * 			the context from which the injection resolution is requested
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_INTEGER}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceInteger(Context context, Field field) {
		
		return ((Integer.class.isAssignableFrom(field.getType())
				|| int.class.isAssignableFrom(field.getType()))
				&& ((ReflectiveR.integer(context, field.getName())) != 0))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_STRING}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_STRING}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceString(Field field) {
		
		return (String.class.isAssignableFrom(field.getType()))? true :false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_DRAWABLE}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_DRAWABLE}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceDrawable(Field field) {
		
		return (Drawable.class.isAssignableFrom(field.getType()))? true :false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_COLOR}.</p>
	 * 
	 * @param context
	 * 			the context from which the injection resolution is requested
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_COLOR}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryResourceColor(Context context, Field field) {
		
		return ((Integer.class.isAssignableFrom(field.getType())
				|| int.class.isAssignableFrom(field.getType()))
				&& ((ReflectiveR.color(context, field.getName())) != 0))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_DIMENSION}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_DRAWABLE}, 
	 * <br><br>
	 * @since 1.1.1
	 */
	private boolean isCategoryResourceDimension(Field field) {
		
		return (Float.class.isAssignableFrom(field.getType())
				|| float.class.isAssignableFrom(field.getType()))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_BOOLEAN}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_BOOLEAN}, 
	 * <br><br>
	 * @since 1.1.1
	 */
	private boolean isCategoryResourceBoolean(Field field) {
		
		return (Boolean.class.isAssignableFrom(field.getType())
				|| boolean.class.isAssignableFrom(field.getType()))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_ARRAY}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_ARRAY}, 
	 * <br><br>
	 * @since 1.1.1
	 */
	private boolean isCategoryResourceArray(Field field) {
		
		return (String[].class.isAssignableFrom(field.getType())
				|| int[].class.isAssignableFrom(field.getType())
				|| Integer[].class.isAssignableFrom(field.getType()))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_ANIMATION}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_ANIMATION}, 
	 * <br><br>
	 * @since 1.1.1
	 */
	private boolean isCategoryResourceAnimation(Field field) {
		
		return (Animation.class.isAssignableFrom(field.getType()))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#RESOURCE_ANIMATOR}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#RESOURCE_ANIMATOR}, 
	 * <br><br>
	 * @since 1.1.1
	 */
	private boolean isCategoryResourceAnimator(Field field) {
		
		return (AnimatorSet.class.isAssignableFrom(field.getType()))? true : false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#POJO}.</p>
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#POJO}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategoryPojo(Field field) {
		
		return field.getType().isAnnotationPresent(Pojo.class);
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#SYSTEM_SERVICE}.</p>
	 * 
	 * <p>The {@link Field}'s <i>declared name</i> must match the 
	 * respective constant in {@link Context} (case insensitive). 
	 * For example, if an instance of {@link TelephonyManager} is 
	 * to be injected, the field declaration should be:</p>
	 * 
	 * <br><br>&nbsp;&nbsp;&nbsp;
	 * 
	 * {@code TelephonyManager telephony_service;}
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#SYSTEM_SERVICE}, 
	 * <br><br>
	 * @since 1.1.0
	 */
	private boolean isCategorySystemService(Field field) {
		
		if(!(field.getName().endsWith("_service") 
			|| field.getName().endsWith("_SERVICE"))) {
			
			return false;
		}
		
		Field[] declaredFields = Context.class.getDeclaredFields();
		
		for (Field declaredField : declaredFields) {
			
			if(String.class.isAssignableFrom(declaredField.getType())
				&& declaredField.getName().equalsIgnoreCase(field.getName())) {
				
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * <p>Determines if this {@link Field} injection falls into 
	 * {@link InjectionCategory#ICKLE_SERVICE}.</p>
	 * 
	 * <p>There are no restrictions to the {@link Field}'s <i> declared 
	 * name</i>. Simply use a service contract with any field name and 
	 * if the contract is an Ickle Service an instance of it will be 
	 * injected. For example, the following declaration is acceptable:</p>
	 * 
	 * <br><br>&nbsp;&nbsp;&nbsp;
	 * 
	 * {@code BindManager myBinder;}
	 * 
	 * @param field
	 * 			the {@link Field} whose {@link InjectionCategory} is to 
	 * 			be resolved
	 * <br><br>
	 * @return {@code true} if it's {@link InjectionCategory#ICKLE_SERVICE}, 
	 * <br><br>
	 * @since 1.1.2
	 */
	private boolean isCategoryIckleService(Field field) {
		
		Class<?> type = field.getType();
		
		return (type.getName().startsWith("com.lonepulse.icklebot") 
				&& type.isAnnotationPresent(IckleService.class));
	}
}
