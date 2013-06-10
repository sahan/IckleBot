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


import java.util.List;

import android.view.View;

import com.lonepulse.icklebot.annotation.bind.Bind;

/**
 * <p>This contract specifies the services offered for resolving 
 * an {@link AbstractBinder} for a model attribute annotated with 
 * {@link Bind}.
 * 
 * @version 1.2.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
interface BinderResolver {
	
	
	/**
	 * <p>Takes a model attribute and resolves the {@link AbstractBinder} 
	 * implementation suited for it all the attributes which are exposed 
	 * to be bound.  
	 *
	 * @param view 
	 * 			the view to which the model is to be bound
	 * 
	 * @param model
	 * 			the model to which the view is bound
	 * 
	 * @return the resolved {@link AbstractBinder} list, which may 
	 * 		   include {@link VoidBinder}s for fields are not exposed 
	 * 		   for binding
	 * 
	 * @throws BindResolutionException
	 * 			if a {@link BindingStrategy} could not be discovered
	 * 
	 * @since 1.2.0
	 */
	List<AbstractBinder<? extends View, ? extends Object>> resolve(View view, Object model) 
	throws BindResolutionException;
}
