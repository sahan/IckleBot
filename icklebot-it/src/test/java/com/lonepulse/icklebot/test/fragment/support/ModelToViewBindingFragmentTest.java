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
import android.widget.ImageView;
import android.widget.TextView;

import com.lonepulse.icklebot.test.R;
import com.xtremelabs.robolectric.RobolectricTestRunner;

/**
 * <p>Unit test for {@link ModelToViewBindingFragment}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@RunWith(RobolectricTestRunner.class)
public class ModelToViewBindingFragmentTest {
	
	
	/**
	 * <p>The instance of {@link FragmentActivityTemplate} with the 
	 * {@link Fragment} whose explicit injection is to be tested.
	 */
	private FragmentActivityTemplate<ModelToViewBindingFragment> activity;
	
	
	/**
	 * <p>Sets up the test by instantiating {@link #fragment}.
	 * 
	 * @throws Exception
	 * 			if {@link #fragment} instantiation terminated 
	 * 			with an error
	 */
	@Before
	public final void setUp() throws Exception {
		
		activity = new FragmentActivityTemplate<ModelToViewBindingFragment>(ModelToViewBindingFragment.class);
		activity.onCreate(null);
	}

	@Test
	public final void testTextBinding() throws Exception {
	
		assertEquals(((TextView)activity.fragment.getView().findViewById(R.id.name)).getText().toString(), "Lahiru Sahan J.");
	}
	
	@Test
	public final void testCustomTextBinder() throws Exception {
		
		assertEquals(((TextView)activity.fragment.getView().findViewById(R.id.github)).getText().toString(), "https://github.com/sahan");
	}
	
	@Test
	public final void testImageBinderDrawable() throws Exception {
		
		assertNotNull(((ImageView)activity.fragment.getView().findViewById(R.id.img_drawable)).getDrawable());
	}
	
	@Test
	public final void testImageBinderBitmap() throws Exception {
		
		assertNotNull(((ImageView)activity.fragment.getView().findViewById(R.id.img_bitmap)).getDrawable());
	}
	
	@Test
	public final void testImageBinderPrimitiveBytes() throws Exception {
		
		assertNotNull(((ImageView)activity.fragment.getView().findViewById(R.id.img_primitive_bytes)).getDrawable());
	}
	
	@Test
	public final void testImageBinderWrapperBytes() throws Exception {
		
		assertNotNull(((ImageView)activity.fragment.getView().findViewById(R.id.img_wrapper_bytes)).getDrawable());
	}
	
	@Test
	public final void testImageBinderPrimitiveInt() throws Exception {
		
		assertNotNull(((ImageView)activity.fragment.getView().findViewById(R.id.img_primitive_int)).getDrawable());
	}
	
	@Test
	public final void testImageBinderWrapperInt() throws Exception {
		
		assertNotNull(((ImageView)activity.fragment.getView().findViewById(R.id.img_wrapper_int)).getDrawable());
	}
	
	@Test
	public final void testExpressiveBinding() throws Exception {
		
		String text = ((TextView)activity.fragment.getView().findViewById(R.id.expressive)).getText().toString();
		assertEquals("Email lahiru@lonepulse.com, Mobile 201184919", text);
	}
	
	@Test
	public final void testElvisBinding() throws Exception {
		
		String text = ((TextView)activity.fragment.getView().findViewById(R.id.inventory)).getText().toString();
		assertEquals("Item 1 is unavailable and Item 2 is bot", text);
	}
	
	@Test
	public final void testStringElementBinding() throws Exception {
		
		String text = ((TextView)activity.fragment.getView().findViewById(R.id.location)).getText().toString();
		assertEquals("Location 4 is Loc4", text);
	}
}
