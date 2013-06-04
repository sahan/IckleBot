package com.lonepulse.icklebot.test.activity;

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


import android.animation.AnimatorSet;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.lonepulse.icklebot.activity.IckleActivity;
import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Title;
import com.lonepulse.icklebot.bind.BindManager;
import com.lonepulse.icklebot.network.NetworkManager;
import com.lonepulse.icklebot.test.R;
import com.lonepulse.icklebot.test.app.ApplicationService;
import com.lonepulse.icklebot.test.service.AccountsService;

/**
 * <p>An extension of {@link IckleActivity} which is used to test the 
 * <b>implicit runtime injection</b> features of IckleBot.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@InjectAll
@Layout(R.layout.act_implicit_injection)
@Title(R.string.ttl_act_implicit_injection)
public class ImplicitInjectionActivity extends IckleActivity {
	

	ApplicationService application;

	String app_name;
	
	int major_version;
	
	Button btnSubmit;
	
	Drawable ic_launcher;
	
	int bg_generic;
	
	float txt_small;
	
	Boolean theme_generic;
	
	String[] font_sizes;
	
	int[] audio_level;
	
	Animation fade_out;
	
	AnimatorSet grow;
	
	TelephonyManager telephony_service;
	
	BindManager bindManager;
	
	NetworkManager networkManager;
	
	AccountsService accountsService;
	
	@Layout(R.layout.act_implicit_injection)
	ViewGroup rootView;
	
	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
}
