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


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.view.View;

import com.lonepulse.icklebot.annotation.listener.Click;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * <p>Unit test for {@link ListenerActivity}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ListenerActivityTest {
	
	
	/**
	 * <p>The instance of {@link ListenerActivity} 
	 * whose event listener linking is to be tested.
	 */
	private ListenerActivity activity;
	
	
	/**
	 * <p>Sets up the test by instantiating {@link #activity}.
	 * 
	 * @throws Exception
	 * 			if {@link #activity} instantiation terminated 
	 * 			with an error
	 */
	@Before
	public final void setUp() throws Exception {
		
		activity = new ListenerActivity();
		activity.onCreate(null);
		
		activity.btnSubmit.performClick();
	}

	/**
	 * <p>Test for {@link Click} and {@link View.OnClickListener}.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testClickEvent() throws Exception {
	
		assertTrue(activity.btnSubmit.getText().equals("Submitted"));
	}
}
