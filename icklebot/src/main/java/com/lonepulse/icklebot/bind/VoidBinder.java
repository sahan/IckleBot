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
	public static final VoidBinder INSTANCE = new VoidBinder(); 
	
	
	/**
	 * <p>Instantiates a new {@link VoidBinder}.
	 *
	 * @since 1.1.0
	 */
	private VoidBinder() {
	
		super(null, null);
	}

	/**
	 * <p>For a {@link VoidBinder}, {@link #onBind(View, Object)} does <b>absolutely nothing</b>. 
	 */
	@Override
	public void onBind(View view, Object data) {}
}
