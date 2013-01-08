package com.lonepulse.icklebot;

/*
 * #%L
 * IckleBot
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

import android.app.Activity;
import android.os.Bundle;

import com.lonepulse.icklebot.state.InstanceStateService;
import com.lonepulse.icklebot.task.BackgroundTaskService;
import com.lonepulse.icklebot.task.UITaskService;

/**
 * <p>This templates abstracts most of the <i>boiler plate code</i> when 
 * working with an {@link Activity} and allows custom extensions to hook 
 * into the template via simple annotations.</p>
 * 
 * @version 1.0.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BoilerPlateActivity extends Activity {


	/**
	 * {@inheritDoc}
	 * 
	 * <p><b>Saves</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		
		InstanceStateService.INSTANCE.save(BoilerPlateActivity.this, outState);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p><b>Restores</b> instance variables annotated with {@code @Stateful}.</p>
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		
		super.onRestoreInstanceState(savedInstanceState);
		
		InstanceStateService.INSTANCE.restore(BoilerPlateActivity.this, savedInstanceState);
	}
	
	/**
	 * <p>See {@link BackgroundTaskService#execute(InjectionActivity, int, Object...)}.</p>
	 * 
	 * @since 1.0.0
	 */
	protected void runBackgroundTask(int backgroundTaskId, Object... args) {
		
		BackgroundTaskService.INSTANCE.execute(BoilerPlateActivity.this, backgroundTaskId, args);
	}
	
	/**
	 * <p>See {@link UITaskService#execute(InjectionActivity, int, Object...)}.</p>
	 * 
	 * @since 1.0.0
	 */
	protected void runUITask(int uiTaskId, final Object... args) {
		
		UITaskService.INSTANCE.execute(BoilerPlateActivity.this, uiTaskId, args);
	}
}
