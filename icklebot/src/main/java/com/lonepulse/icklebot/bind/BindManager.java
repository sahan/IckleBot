package com.lonepulse.icklebot.bind;

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


import android.view.View;
import android.view.ViewGroup;

import com.lonepulse.icklebot.annotation.bind.Model;
import com.lonepulse.icklebot.annotation.inject.Pojo;

/**
 * <p>This contract specifies the services offered for managing 
 * model-view binding.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Pojo(BindService.class) //TODO add support for @IckleService & @InjectIckleService; ISSUE #2
public interface BindManager {

	
	/**
	 * <p>Binds the given model to the {@link View} or {@link ViewGroup}.
	 * 
	 * @param view
	 * 			the {@link View} to which the model is to be bound 
	 * 
	 * @param model
	 * 			the {@link Model} to be bound to the view
	 * 
	 * @since 1.1.0
	 */
	public abstract void bind(View view, Object model);
}
