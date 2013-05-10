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


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.widget.TextView;

import com.lonepulse.icklebot.test.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * <p>Unit test for {@link TaskSupportActivity}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@RunWith(RobolectricTestRunner.class)
public class TaskSupportActivityTest {
	
	
	/**
	 * <p>The instance of {@link TaskSupportActivity} background 
	 * and UI task execution is to be tested.
	 */
	private TaskSupportActivity activity;
	
	
	/**
	 * <p>Sets up the test by instantiating {@link #activity}.
	 * 
	 * @throws Exception
	 * 			if {@link #activity} instantiation terminated 
	 * 			with an error
	 */
	@Before
	public final void setUp() throws Exception {
		
		activity = new TaskSupportActivity();
		activity.onCreate(null);
		
		try { //allow completion of asynchronous tasks
			
			Thread.sleep(2000);
		}
		catch (InterruptedException ie) {}
	}

	/**
	 * <p>Test for the execution of a generic background task.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testGenericBGTask() throws Exception {
	
		assertTrue(activity.token >= 0.0);
	}
	
	/**
	 * <p>Test for the execution of a generic UI task.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testGenericUITask() throws Exception {
		
		String token = ((TextView)activity.findViewById(R.id.txtToken)).getText().toString();

		assertTrue(Double.parseDouble(token) >= 0.0);
	}
	
	/**
	 * <p>Test for the execution of a parameterized background task.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testParameterizedBGTask() throws Exception {
		
		assertTrue(!activity.alias.equals("Lonepulse"));
	}
	
	/**
	 * <p>Test for the execution of a parameterized and delayed UI task.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testParameterizedDelayedUITask() throws Exception {
		
		String alias = ((TextView)activity.findViewById(R.id.txtAlias)).getText().toString();
		
		assertTrue(alias.equals("Ick le Bot"));
	}
}
