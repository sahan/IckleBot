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

import com.lonepulse.icklebot.IckleSupportManager;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.annotation.inject.Stateful;
import com.lonepulse.icklebot.fragment.IckleSupportFragment;
import com.lonepulse.icklebot.test.R;

/**
 * <p>An extension of {@link Fragment} which is used to test the 
 * <b>state management support</b> features of IckleBot.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Layout(R.layout.act_explicit_injection)
public class SupportedStateFragment extends Fragment {
	

	@Stateful
	int intMajorVersion = 1;
	
	
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
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		shadow.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		shadow.onSaveInstanceState(outState);
	}
}
