package com.lonepulse.icklebot.test.activity.suppport;

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


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lonepulse.icklebot.activity.support.IckleFragmentActivity;
import com.lonepulse.icklebot.annotation.event.Click;
import com.lonepulse.icklebot.annotation.event.ItemClick;
import com.lonepulse.icklebot.annotation.event.Touch;
import com.lonepulse.icklebot.annotation.inject.InjectArray;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Title;
import com.lonepulse.icklebot.test.R;

/**
 * <p>An extension of {@link IckleFragmentActivity} which is used to test the 
 * <b>event linking</b> features of IckleBot.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_listener)
@Title(R.string.ttl_act_listener)
public class EventFragmentActivity extends IckleFragmentActivity {
	

	@InjectView(R.id.btnSubmit)
	public Button btnSubmit;
	
	@InjectView(R.id.txtAlias)
	public TextView txtAlias;
	
	@InjectView(android.R.id.list)
	public ListView listView;
	
	@InjectArray(R.array.audio_level)
	public String[] items;
	
	public AdapterView<?> parentView;
	public View selectedView;
	public int selectedPostion;
	public long selectedId;

	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke it from an external context.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		String[] items = new String[] { "1", "2", "3", "4", "5"};
		
		ArrayAdapter<String> arrayAdapter 
			= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		
		listView.setAdapter(arrayAdapter);
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
	
	@ItemClick(android.R.id.list)
	public void onSelect(AdapterView<?> parent, View view, int position, long id) {
		
		this.parentView = parent;
		this.selectedView = view;
		this.selectedPostion = position;
		this.selectedId = id;
	}
}
