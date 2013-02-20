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


import android.os.Bundle;
import android.widget.TextView;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.BackgroundTask;
import com.lonepulse.icklebot.annotation.Layout;
import com.lonepulse.icklebot.annotation.Title;
import com.lonepulse.icklebot.annotation.UITask;

/**
 * <p>An extension of {@link IckleActivity} which is used to test the 
 * alternate threading model.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_task)
@Title(id = R.string.ttl_act_task)
public class TaskActivity extends IckleActivity {
	
	
	private static final int BG_GEN_TOKEN = 1;
	private static final int BG_GEN_ALIAS = 2;
	
	private static final int UI_UPDATE_TOKEN = 1;
	private static final int UI_UPDATE_ALIAS = 2;
	
	
	public double token = -1;
	public String alias = "Lonepulse";

	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		runBackgroundTask(BG_GEN_TOKEN);
		runBackgroundTask(BG_GEN_ALIAS, "Ick");
	}
	
	@BackgroundTask(BG_GEN_TOKEN)
	public void generateToken() {
		
		token = Math.random();	
		
		runUITask(UI_UPDATE_TOKEN);
	}
	
	@UITask(UI_UPDATE_TOKEN)
	public void updateToken() {
		
		((TextView)findViewById(R.id.txtToken)).setText(String.valueOf(token));
	}
	
	@BackgroundTask(BG_GEN_ALIAS)
	public void generateAlias(String prefix) {
		
		alias = prefix + " le Bot";
		
		runUITask(UI_UPDATE_ALIAS, alias);
	}
	
	@UITask(value = UI_UPDATE_ALIAS, delay = 500)
	public void updateAlias(String generatedAlias) {
		
		((TextView)findViewById(R.id.txtAlias)).setText(generatedAlias);
	}
}
