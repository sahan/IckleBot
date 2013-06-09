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
import java.util.Arrays;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.lonepulse.icklebot.annotation.bind.Bind;
import com.lonepulse.icklebot.annotation.bind.Model;
import com.lonepulse.icklebot.bind.Binder;
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
	
	@Bind(R.id.name)
	private String fullName;
	
	@Bind(widgetId = R.id.github, binderType = GitHubBinder.class)
	private String githubUsername;
	
	@Bind(widgetId = R.id.img_drawable, binder = Binder.IMAGE)
	private Drawable icDrawable;
	
	@Bind(widgetId = R.id.img_bitmap, binder = Binder.IMAGE)
	private Bitmap icBitmap;
	
	@Bind(widgetId = R.id.img_primitive_int, binder = Binder.IMAGE)
	private int icInt;
	
	@Bind(widgetId = R.id.img_wrapper_int, binder = Binder.IMAGE)
	private Integer icWrapperInt;
	
	@Bind(widgetId = R.id.img_primitive_bytes, binder = Binder.IMAGE)
	private byte[] icPrimitiveBytes;
	
	@Bind(widgetId = R.id.img_wrapper_bytes, binder = Binder.IMAGE)
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

	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("User [id=");
		builder.append(id);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", githubUsername=");
		builder.append(githubUsername);
		builder.append(", icDrawable=");
		builder.append(icDrawable);
		builder.append(", icBitmap=");
		builder.append(icBitmap);
		builder.append(", icInt=");
		builder.append(icInt);
		builder.append(", icWrapperInt=");
		builder.append(icWrapperInt);
		builder.append(", icPrimitiveBytes=");
		builder.append(Arrays.toString(icPrimitiveBytes));
		builder.append(", icWrapperBytes=");
		builder.append(Arrays.toString(icWrapperBytes));
		builder.append("]");
		
		return builder.toString();
	}
}
