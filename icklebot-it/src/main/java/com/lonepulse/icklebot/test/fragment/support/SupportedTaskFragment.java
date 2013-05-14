package com.lonepulse.icklebot.test.fragment.support;

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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lonepulse.icklebot.IckleSupportManager;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.thread.Async;
import com.lonepulse.icklebot.annotation.thread.UI;
import com.lonepulse.icklebot.fragment.IckleSupportFragment;
import com.lonepulse.icklebot.test.R;

/**
 * <p>An extension of {@link Fragment} which is used to test the 
 * <b>threading</b> features of IckleBot on fragments.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_task)
public class SupportedTaskFragment extends Fragment {
	
	
	private static final int BG_GEN_TOKEN = 1;
	private static final int BG_GEN_ALIAS = 2;
	
	private static final int UI_UPDATE_TOKEN = 1;
	private static final int UI_UPDATE_ALIAS = 2;
	
	public double token = -1;
	public String alias = "Lonepulse";
	
	
	private com.lonepulse.icklebot.app.SupportFragment shadow;
	{
		shadow = IckleSupportFragment.shadow(this, new IckleSupportManager.Builder(this)
		.enableInjectionSupport());
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		return shadow.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		super.onViewCreated(view, savedInstanceState);
		shadow.onViewCreated(view, savedInstanceState);
	}
	
	@Override
	public void onStart() {
		
		super.onStart();
		
		shadow.getSupportManager().runAsyncTask(BG_GEN_TOKEN);
		shadow.getSupportManager().runAsyncTask(BG_GEN_ALIAS, "Ick");
	}
	
	@Async(BG_GEN_TOKEN)
	public void generateToken() {
		
		token = Math.random();	
		
		shadow.getSupportManager().runUITask(UI_UPDATE_TOKEN);
	}
	
	@UI(UI_UPDATE_TOKEN)
	public void updateToken() {
		
		((TextView)getView().findViewById(R.id.txtToken)).setText(String.valueOf(token));
	}
	
	@Async(BG_GEN_ALIAS)
	public void generateAlias(String prefix) {
		
		alias = prefix + " le Bot";
		
		shadow.getSupportManager().runUITask(UI_UPDATE_ALIAS, alias);
	}
	
	@UI(value = UI_UPDATE_ALIAS, delay = 500)
	public void updateAlias(String generatedAlias) {
		
		((TextView)getView().findViewById(R.id.txtAlias)).setText(generatedAlias);
	}
}
