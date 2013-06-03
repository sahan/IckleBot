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


import android.animation.AnimatorSet;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.lonepulse.icklebot.activity.support.IckleFragmentActivity;
import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Title;
import com.lonepulse.icklebot.bind.BindManager;
import com.lonepulse.icklebot.test.R;
import com.lonepulse.icklebot.test.app.ApplicationService;
import com.lonepulse.icklebot.test.service.AccountsService;

/**
 * <p>An extension of {@link IckleFragmentActivity} which is used to test the 
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
public class ImplicitInjectionFragmentActivity extends IckleFragmentActivity {
	

	public ApplicationService application;

	public String app_name;
	
	public int major_version;
	
	public Button btnSubmit;
	
	public Drawable ic_launcher;
	
	public int bg_generic;
	
	public float txt_small;
	
	public Boolean theme_generic;
	
	public String[] font_sizes;
	
	public int[] audio_level;
	
	public Animation fade_out;
	
	public AnimatorSet grow;
	
	public TelephonyManager telephony_service;
	
	public BindManager bindManager;
	
	public AccountsService accountsService;
	
	@Layout(R.layout.act_implicit_injection)
	public ViewGroup rootView;
	
	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
}
