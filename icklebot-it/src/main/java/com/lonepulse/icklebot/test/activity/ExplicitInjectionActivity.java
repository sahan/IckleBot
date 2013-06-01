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
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.lonepulse.icklebot.IckleActivity;
import com.lonepulse.icklebot.annotation.inject.InjectAnimation;
import com.lonepulse.icklebot.annotation.inject.InjectAnimator;
import com.lonepulse.icklebot.annotation.inject.InjectApplication;
import com.lonepulse.icklebot.annotation.inject.InjectArray;
import com.lonepulse.icklebot.annotation.inject.InjectBoolean;
import com.lonepulse.icklebot.annotation.inject.InjectColor;
import com.lonepulse.icklebot.annotation.inject.InjectDimension;
import com.lonepulse.icklebot.annotation.inject.InjectDrawable;
import com.lonepulse.icklebot.annotation.inject.InjectIckleService;
import com.lonepulse.icklebot.annotation.inject.InjectInteger;
import com.lonepulse.icklebot.annotation.inject.InjectPojo;
import com.lonepulse.icklebot.annotation.inject.InjectSystemService;
import com.lonepulse.icklebot.annotation.inject.InjectString;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Title;
import com.lonepulse.icklebot.bind.BindManager;
import com.lonepulse.icklebot.test.R;
import com.lonepulse.icklebot.test.app.ApplicationService;
import com.lonepulse.icklebot.test.service.AccountsService;
import com.lonepulse.icklebot.test.service.AccountsServiceImpl;

/**
 * <p>An extension of {@link IckleActivity} which is used to test the 
 * <b>explicit runtime injection</b> features of IckleBot.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_explicit_injection)
@Title(R.string.ttl_act_explicit_injection)
public class ExplicitInjectionActivity extends IckleActivity {
	

	@InjectApplication
	ApplicationService application;
	
	@InjectString(R.string.app_name)
	String strAppName;
	
	@InjectInteger(R.integer.major_version)
	int intMajorVersion;
	
	@InjectView(R.id.btnSubmit)
	Button btnSubmit;
	
	@InjectDrawable(R.drawable.ic_launcher)
	Drawable drwLauncherIcon;
	
	@InjectColor(R.color.bg_generic)
	int colorGeneric;
	
	@InjectDimension(R.dimen.txt_small)
	float txtSizeSmall;
	
	@InjectBoolean(R.bool.theme_generic)
	Boolean themeGeneric;
	
	@InjectArray(R.array.font_sizes)
	String[] fontSizes;
	
	@InjectArray(R.array.audio_level)
	int[] audioLevel;
	
	@InjectAnimation(R.anim.fade_out)
	Animation fadeOut;
	
	@InjectAnimator(R.animator.grow)
	AnimatorSet grow;
	
	@InjectSystemService(Context.TELEPHONY_SERVICE)
	TelephonyManager telephonyManager;
	
	@InjectPojo(AccountsServiceImpl.class)
	AccountsService accountsService;
	
	@Layout(R.layout.act_explicit_injection)
	ViewGroup rootView;
	
	@InjectIckleService
	BindManager bindManager;
	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}
}
