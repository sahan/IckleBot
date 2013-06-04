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


import android.content.Context;
import android.view.View;

/**
 * <p>This binding strategy signifies a non-existent or nullified bind.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class VoidBinder extends AbstractBinder<View, Object> {


	/**
	 * <p>The default instance of {@link VoidBinder} which signifies 
	 * all non-existent or nullified binds. 
	 */
	private static VoidBinder INSTANCE; 
	
	
	/**
	 * <p>Retrieves a static instance of {@link VoidBinder} by initializing it 
	 * if necessary.
	 *
	 * @param context
	 * 			the {@link Context} from which the {@link VoidBinder} is requested
	 * 
	 * @return the only instance of {@link VoidBinder}
	 * 
	 * @since 1.1.0
	 */
	public static final VoidBinder getInstance(Context context) {
		
		if(INSTANCE == null)
			INSTANCE = new VoidBinder(new View(context), new Object());
		
		return INSTANCE; 
	}
	
	/**
	 * <p>This constructor is exposed to conform to the general usage policy of an 
	 * {@link AbstractBinder}. It's advised to use {@link #getInstance(Context)} instead.
	 * 
	 * @since 1.1.0
	 */
	public VoidBinder(View view, Object data) {
	
		super(view, data);
	}

	/**
	 * <p>For a {@link VoidBinder}, {@link #onBind(View, Object)} does <b>absolutely nothing</b>.
	 * 
	 * @since 1.1.0
	 */
	@Override
	public void onBind(View view, Object data) {}
}
