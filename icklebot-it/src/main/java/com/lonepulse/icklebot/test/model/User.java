package com.lonepulse.icklebot.test.model;

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


import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.lonepulse.icklebot.annotation.bind.Bind;
import com.lonepulse.icklebot.annotation.bind.BindImage;
import com.lonepulse.icklebot.annotation.bind.BindText;
import com.lonepulse.icklebot.annotation.bind.Expressive;
import com.lonepulse.icklebot.annotation.bind.Model;
import com.lonepulse.icklebot.test.R;
import com.lonepulse.icklebot.test.binder.GitHubBinder;

/**
 * <p>This entity represents the current user of the application. It's a 
 * <b>Model</b> which can be bound to a {@link ViewGroup}.
 * 
 * @category test
 * <br><br>
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@Model
public class User implements Serializable {
	

	private static final long serialVersionUID = -2427123276457090149L;

	private long id; //this model attribute will not be bound
	
	@BindText(R.id.name)
	private String fullName;
	
	@Bind(ids = R.id.github, type = GitHubBinder.class)
	private String githubUsername;
	
	@Expressive @BindText(R.id.expressive)
	private Contact contact;
	
	@Expressive @BindText({R.id.inventory, R.id.location})
	private Inventory inventory;
	
	@BindImage(R.id.img_drawable)
	private Drawable icDrawable;
	
	@BindImage(R.id.img_bitmap)
	private Bitmap icBitmap;
	
	@BindImage(R.id.img_primitive_int)
	private int icInt;
	
	@BindImage(R.id.img_wrapper_int)
	private Integer icWrapperInt;
	
	@BindImage(R.id.img_primitive_bytes)
	private byte[] icPrimitiveBytes;
	
	@BindImage(R.id.img_wrapper_bytes)
	private Byte[] icWrapperBytes;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGithubUsername() {
		return githubUsername;
	}

	public void setGithubUsername(String githubUsername) {
		this.githubUsername = githubUsername;
	}

	public Drawable getIcDrawable() {
		return icDrawable;
	}

	public void setIcDrawable(Drawable icDrawable) {
		this.icDrawable = icDrawable;
	}

	public Bitmap getIcBitmap() {
		return icBitmap;
	}

	public void setIcBitmap(Bitmap icBitmap) {
		this.icBitmap = icBitmap;
	}

	public int getIcInt() {
		return icInt;
	}

	public void setIcInt(int icInt) {
		this.icInt = icInt;
	}

	public Integer getIcWrapperInt() {
		return icWrapperInt;
	}

	public void setIcWrapperInt(Integer icWrapperInt) {
		this.icWrapperInt = icWrapperInt;
	}

	public byte[] getIcPrimitiveBytes() {
		return icPrimitiveBytes;
	}

	public void setIcPrimitiveBytes(byte[] icPrimitiveBytes) {
		this.icPrimitiveBytes = icPrimitiveBytes;
	}

	public Byte[] getIcWrapperBytes() {
		return icWrapperBytes;
	}

	public void setIcWrapperBytes(Byte[] icWrapperBytes) {
		this.icWrapperBytes = icWrapperBytes;
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
}
