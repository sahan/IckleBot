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


import android.animation.AnimatorSet;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.lonepulse.icklebot.IckleSupportManager;
import com.lonepulse.icklebot.annotation.inject.InjectAll;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.bind.BindManager;
import com.lonepulse.icklebot.fragment.IckleSupportFragment;
import com.lonepulse.icklebot.test.R;
import com.lonepulse.icklebot.test.app.ApplicationService;
import com.lonepulse.icklebot.test.service.AccountsService;

/**
 * <p>An extension of {@link Fragment} which is used to test the 
 * <b>implicit runtime injection</b> features of IckleBot on fragments.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@InjectAll
@Layout(R.layout.act_implicit_injection)
public class SupportedImplicitInjectionFragment extends Fragment {

	
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
	
	AccountsService accountsService;
	
	@Layout(R.layout.act_explicit_injection)
	ViewGroup rootView;
	
	
	private com.lonepulse.icklebot.app.Fragment shadow;
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
}
