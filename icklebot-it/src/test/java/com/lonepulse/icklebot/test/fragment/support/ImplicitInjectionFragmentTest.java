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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.v4.app.Fragment;
import android.view.animation.Animation;

import com.lonepulse.icklebot.test.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * <p>Unit test for {@link ImplicitInjectionFragment}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ImplicitInjectionFragmentTest {
	
	
	/**
	 * <p>The instance of {@link FragmentActivityTemplate} with the 
	 * {@link Fragment} whose explicit injection is to be tested.
	 */
	private FragmentActivityTemplate<ImplicitInjectionFragment> activity;
	
	
	/**
	 * <p>Sets up the test by instantiating {@link #fragment}.
	 * 
	 * @throws Exception
	 * 			if {@link #fragment} instantiation terminated 
	 * 			with an error
	 */
	@Before
	public final void setUp() throws Exception {
		
		activity = new FragmentActivityTemplate<ImplicitInjectionFragment>(ImplicitInjectionFragment.class);
		activity.onCreate(null);
	}

	/**
	 * <p>Test for the fragment view.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testFragmentView() throws Exception {
	
		assertNotNull(activity.fragment.getView().findViewById(R.id.root));
	}
	
	/**
	 * <p>Test application injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testApplication() throws Exception {
		
		assertNotNull(activity.fragment.application);
	}
	
	/**
	 * <p>Test string injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testString() throws Exception {
		
		assertNotNull(activity.fragment.btnSubmit);
	}
	
	/**
	 * <p>Test integer injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testInteger() throws Exception {
		
		assertEquals(activity.fragment.major_version, 1);
	}
	
	/**
	 * <p>Test view injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testView() throws Exception {
		
		assertNotNull(activity.fragment.btnSubmit);
	}
	
	/**
	 * <p>Test drawable injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testDrawable() throws Exception {
		
		assertNotNull(activity.fragment.ic_launcher);
	}
	
	/**
	 * <p>Test color injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testColor() throws Exception {
		
		assertEquals(activity.getResources().getColor(R.color.bg_generic), 
					 activity.fragment.bg_generic, 0);
	}
	
	/**
	 * <p>Test dimension injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testDimension() throws Exception {
		
		assertEquals(12.0, activity.fragment.txt_small, 0);
	}
	
	/**
	 * <p>Test boolean injection.</p>
	 * 
	 * <p><b>NOTE</b>: Robolectric v1.1 retrieves false 
	 * for all boolean resources. Hence a null check on 
	 * a boolean wrapper is performed.</p>
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testBoolean() throws Exception {
		
		assertNotNull(activity.fragment.theme_generic);
	}
	
	/**
	 * <p>Test {@link Animation} injection.</p>
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testAnimation() throws Exception {
		
		assertNotNull(activity.fragment.fade_out);
	}
	
	/**
	 * <p>Test system service injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testSystemService() throws Exception {
		
		assertNotNull(activity.fragment.telephony_service);
	}
	
	/**
	 * <p>Test ickle service injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.1
	 */
	@Test
	public final void testIckleService() throws Exception {
		
		assertNotNull(activity.fragment.bindManager);
	}
	
	/**
	 * <p>Test POJO injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testPOJO() throws Exception {
		
		assertNotNull(activity.fragment.accountsService);
	}
	
	/**
	 * <p>Test layout injection.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testLayout() throws Exception {
		
		assertNotNull(activity.fragment.rootView);
	}
}
