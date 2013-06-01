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

import java.lang.reflect.Field;

import android.view.View;


/**
 * <p>An aggregation of all {@link BinderResolver}s.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum BinderResolvers implements BinderResolver {

	/**
	 * <p>See {@link BasicBinderResolver}.
	 *
	 * @since 1.1.0
	 */
	BASIC(new BasicBinderResolver());
	
	
	/**
	 * <p>The instance of {@link BinderResolver} reflected by this facet.
	 * 
	 * @since 1.1.0
	 */
	private BinderResolver binderResolver;
	
	/**
	 * <p>Populates {@link #binderResolver}.
	 * 
	 * @param binderResolver
	 * 			populates {@link #binderResolver}
	 * 
	 * @since 1.1.0
	 */
	private BinderResolvers(BinderResolver binderResolver) {
		
		this.binderResolver = binderResolver;
	}

	/**
	 * <p>Delegate method for {@link #BinderResolver#resolve(View, Object, Field)}.
	 * 
	 * @since 1.1.0
	 */
	@Override
	public AbstractBinder<? extends View, ? extends Object> resolve(View view, Object model, Field field) 
	throws BindResolutionException {
		
		return this.binderResolver.resolve(view, model, field);
	}
}
