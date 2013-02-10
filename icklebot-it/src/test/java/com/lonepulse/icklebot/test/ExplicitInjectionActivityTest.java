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

import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * <p>Unit test for {@link ExplicitInjectionActivity}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ExplicitInjectionActivityTest {
	
	
	/**
	 * <p>The instance of {@link ExplicitInjectionActivity} 
	 * whose explicit injection is to be tested.
	 */
	private ExplicitInjectionActivity activity;
	
	
	/**
	 * <p>Sets up the test by instantiating {@link #activity}.
	 * 
	 * @throws Exception
	 * 			if {@link #activity} instantiation terminated 
	 * 			with an error
	 */
	@Before
	public final void setUp() throws Exception {
		
		activity = new ExplicitInjectionActivity();
		activity.onCreate(null);
	}

	/**
	 * <p>Test for layout, title and window-features injection.
	 * 
	 * @throws Exception
	 * 			if configuration testing terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testConfiguration() throws Exception {
	
		assertTrue(activity.findViewById(R.id.root) != null);
		assertTrue(activity.getTitle().toString().equalsIgnoreCase("Explicit Injection"));
	}
}
