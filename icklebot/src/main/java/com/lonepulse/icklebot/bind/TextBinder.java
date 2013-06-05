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


import android.widget.Button;
import android.widget.TextView;

/**
 * <p>The binding strategy to be employed for {@link TextView}s or any of its children. 
 * The <i>data</i> is obtained by invoking {@code toString()} on the given {@link Object}, 
 * so be sure to override this method and provide the String to bind the {@link TextView} 
 * with if the super implementation is not satisfactory.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class TextBinder extends AbstractBinder<TextView, Object> {

	/**
	 * <p>Instantiates a new {@link TextBinder} with the given 
	 * {@link TextView} and data. 
	 * 
	 * @param textView
	 * 			the {@link TextView} to which the data is to be bound; this could 
	 * 			be any extension of {@link TextView} such as a {@link Button}
	 * 
	 * @param data
	 * 			the {@link Object} on which {@code toString()} will be 
	 * 			invoked to retrieve the String to bind to the {@link TextView}
	 *
	 * @since 1.1.0
	 */
	public TextBinder(TextView textView, Object data) {
	
		super(textView, data == null? "" :data);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBind(TextView view, Object data) {
		
		view.setText(data.toString());
	}
}
