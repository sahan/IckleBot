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


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.widget.TextView;

import com.lonepulse.icklebot.bind.AbstractBinder;
import com.lonepulse.icklebot.test.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * <p>Unit test for {@link SupportedModelToViewBindingActivity}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@RunWith(RobolectricTestRunner.class)
public class SupportedModelToViewBindingActivityTest {
	
	
	/**
	 * <p>The instance of {@link SupportedModelToViewBindingActivity} 
	 * whose binding is to be tested.
	 */
	private SupportedModelToViewBindingActivity activity;
	
	
	/**
	 * <p>Sets up the test by instantiating {@link #activity}.
	 * 
	 * @throws Exception
	 * 			if {@link #activity} instantiation terminated 
	 * 			with an error
	 */
	@Before
	public final void setUp() throws Exception {
		
		activity = new SupportedModelToViewBindingActivity();
		activity.onCreate(null);
	}

	/**
	 * <p>Test non-expressive {@link TextView} binding.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testTextViewBinding() throws Exception {
	
		assertEquals(((TextView)activity.findViewById(R.id.name)).getText().toString(), "Lahiru Sahan J.");
	}
	
	/**
	 * <p>Test non-expressive {@link TextView} binding with a 
	 * custom {@link AbstractBinder}.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testCustomBinder() throws Exception {
		
		assertEquals(((TextView)activity.findViewById(R.id.github)).getText().toString(), "https://github.com/sahan");
	}
}
