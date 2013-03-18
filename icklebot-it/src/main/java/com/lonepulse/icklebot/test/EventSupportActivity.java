package com.lonepulse.icklebot.test;

/*
 * #%L
 * IckleBot Integration Tests
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


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import com.lonepulse.icklebot.IckleSupportManager;
import com.lonepulse.icklebot.annotation.event.Click;
import com.lonepulse.icklebot.annotation.event.Touch;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Title;

/**
 * <p>An extension of {@link Activity} which is used to test the 
 * <b>event linking</b> features of {@link IckleSupportManager}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_listener)
@Title(id = R.string.ttl_act_listener)
public class EventSupportActivity extends Activity {
	

	@InjectView(R.id.btnSubmit)
	Button btnSubmit;
	
	@InjectView(R.id.txtAlias)
	TextView txtAlias;
	
	
	@SuppressWarnings("unused")
	private IckleSupportManager supportManager;
	{
		supportManager = new IckleSupportManager.Builder(this)
		.enableInjectionSupport()
		.enableEventSupport()
		.build();
	}

	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke it from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
	
	@Click(R.id.btnSubmit)
	public void onSubmit(Button button) {
		
		button.setText("Submitted");
	}
	
	@Touch(R.id.txtAlias)
	public void onTouch(TextView textView, MotionEvent motionEvent) {
		
		if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			
			textView.setText("Ick le Bot");
		}
	}
}
