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


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.lonepulse.icklebot.annotation.event.Click;
import com.lonepulse.icklebot.annotation.event.ItemClick;
import com.lonepulse.icklebot.annotation.event.Touch;
import com.lonepulse.icklebot.test.activity.EventActivity;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * <p>Unit test for {@link EventActivity}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@RunWith(RobolectricTestRunner.class)
public class EventFragmentTest {
	
	
	/**
	 * <p>The instance of {@link FragmentActivityTemplate} which 
	 * takes an {@link EventFragment} for testing event linking.
	 */
	private FragmentActivityTemplate<EventFragment> activity;
	
	
	/**
	 * <p>Sets up the test by instantiating {@link #activity}.
	 * 
	 * @throws Exception
	 * 			if {@link #activity} instantiation terminated 
	 * 			with an error
	 */
	@Before
	public final void setUp() throws Exception {
		
		activity = new FragmentActivityTemplate<EventFragment>(EventFragment.class);
		activity.onCreate(null);
		
		activity.fragment.btnSubmit.performClick();
		
		MotionEvent press = MotionEvent.obtain(SystemClock.uptimeMillis(), 
											   SystemClock.uptimeMillis(), 
											   MotionEvent.ACTION_DOWN, 0.0f, 0.0f, 0);
		
		activity.fragment.txtAlias.dispatchTouchEvent(press);
		press.recycle();
		
		View root = activity.getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
		activity.fragment.listView.performItemClick(root.findViewById(android.R.id.text1), 3, 3);
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
	
		assertTrue(activity.fragment.btnSubmit.getText().equals("Submitted"));
	}
	
	/**
	 * <p>Test for {@link Touch} and {@link View.OnTouchListener}.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testTouchEvent() throws Exception {
		
		assertTrue(activity.fragment.txtAlias.getText().equals("Ick le Bot"));
	}
	
	/**
	 * <p>Test for {@link ItemClick} and {@link AdapterView.OnItemClickListener}.
	 * 
	 * @throws Exception
	 * 			if test terminated with an error
	 * 
	 * @since 1.1.0
	 */
	@Test
	public final void testItemClickEvent() throws Exception {
		
		assertNotNull(activity.fragment.parentView);
		assertNotNull(activity.fragment.selectedView);
		assertNotSame(activity.fragment.selectedPostion, 0);
		assertNotSame(activity.fragment.selectedId, 0l);
	}
}
