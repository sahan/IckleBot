package com.lonepulse.icklebot.test.binder;

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


import android.widget.TextView;

import com.lonepulse.icklebot.bind.AbstractBinder;

/**
 * <p>This is a simple AbstractBinder implementation which binds 
 * GitHub URLs to {@link TextView}s. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class GitHubBinder extends AbstractBinder<TextView, Object> {


	public GitHubBinder(TextView view, String slug) {
		
		super(view, slug);
	}

	@Override
	protected void onBind(TextView view, Object slug) {
		
		String url = "https://github.com/" + slug;
		
		view.setText(url);
		view.setLinksClickable(true);
	}
}
