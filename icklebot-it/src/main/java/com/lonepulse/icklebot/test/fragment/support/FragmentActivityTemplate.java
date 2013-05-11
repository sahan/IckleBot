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
import android.support.v4.app.FragmentActivity;

/**
 * <p>An {@link FragmentActivity} which is used to test IckleBot's features 
 * offered via {@link Fragment}s.
 * 
 * @category test
 * <br><br>
 * @version 1.2.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class FragmentActivityTemplate<T extends Fragment> extends FragmentActivity {


	/**
	 * <p>The support fragment which is under test.
	 */
	public T fragment; 
	
	/**
	 * <p>The {@link Class} of the fragment to tested.
	 */
	private Class<T> fragmentClass;
	
	
	/**
	 * <p>Instantiates this {@link FragmentActivity} by taking 
	 * the {@link Class} of the fragment to be tested.
	 * 
	 * @param fragmentClass
	 * 			the {@link Class} of the fragment to tested
	 *
	 * @since 1.2.0
	 */
	public FragmentActivityTemplate(Class<T> fragmentClass) {
		
		this.fragmentClass = fragmentClass;
	}
			
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit tests to 
	 * invoke it from an external context. Creates an instance of the 
	 * fragment to be tested commits it via the support fragment manager. 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		try {
			
			fragment = fragmentClass.newInstance();
		}
		catch (InstantiationException ie) {
			
			ie.printStackTrace();
		}
		catch (IllegalAccessException iae) {

			iae.printStackTrace();
		}
		
		getSupportFragmentManager()
			.beginTransaction().add(fragment, "fragment").commit();
	}
}
