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


import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.lonepulse.icklebot.activity.IckleActivity;
import com.lonepulse.icklebot.activity.IckleSupportManager;
import com.lonepulse.icklebot.annotation.inject.Fullscreen;
import com.lonepulse.icklebot.annotation.inject.InjectIckleService;
import com.lonepulse.icklebot.annotation.inject.InjectView;
import com.lonepulse.icklebot.annotation.inject.Layout;
import com.lonepulse.icklebot.bind.BindManager;
import com.lonepulse.icklebot.test.R;
import com.lonepulse.icklebot.test.model.Contact;
import com.lonepulse.icklebot.test.model.Inventory;
import com.lonepulse.icklebot.test.model.User;

/** 
 * <p>An extension of {@link Activity} which is used to test 
 * {@link IckleSupportManager}'s <b>Unidirectional Model to View Binding</b>.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Fullscreen
@Layout(R.layout.act_model_to_view_binding)
public class SupportedModelToViewBindingActivity extends IckleActivity {
	

	@InjectView(R.id.root)
	View rootView;
	
	@InjectIckleService
	BindManager bindManager;

	User user;
	
	@SuppressWarnings("unused")
	private IckleSupportManager supportManager;
	{
		supportManager = new IckleSupportManager.Builder(this)
		.enableInjectionSupport()
		.build();
	}
	
	
	/**
	 * <p>Exposes {@link #onCreate(Bundle)} and allows unit 
	 * tests to invoke injection from an external context.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		user = new User();
		user.setFullName("Lahiru Sahan J.");
		user.setGithubUsername("sahan");
		
		Contact contact = new Contact();
		contact.setEmail("lahiru@lonepulse.com");
		contact.setMobile("201184919");
		user.setContact(contact);
		
		Inventory inventory = new Inventory();
		inventory.setItem1(null);
		inventory.setItem2("bot");
		
		Map<String, String> items = new HashMap<String, String>();
		items.put("SKU1111", "Item1");
		items.put("SKU2222", "Item2");
		items.put("SKU3333", "Item3");
		items.put("SKU4444", "Item4");
		
		inventory.setItems(items);
		inventory.setLocations(new String[] {"Loc1", "Loc2", "Loc3", "Loc4"});
		user.setInventory(inventory);
		
		Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher); 
		Bitmap decodedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		decodedBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		
		byte[] primitiveBytes = baos.toByteArray();
		
		Byte[] wrapperBytes = new Byte[primitiveBytes.length];
		
		for (int i = 0; i < primitiveBytes.length; i++)
			wrapperBytes[i] = Byte.valueOf(primitiveBytes[i]);
		
		user.setIcInt(R.drawable.ic_launcher);
		user.setIcWrapperInt(Integer.valueOf(R.drawable.ic_launcher));
		user.setIcDrawable(drawable);
		user.setIcBitmap(decodedBitmap);
		user.setIcPrimitiveBytes(primitiveBytes);
		user.setIcWrapperBytes(wrapperBytes);
		
		bindManager.bind(rootView, user);
	}
}
