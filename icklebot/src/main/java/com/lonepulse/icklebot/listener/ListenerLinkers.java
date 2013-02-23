package com.lonepulse.icklebot.listener;

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
import android.util.Log;
import android.view.View;

/**
 * <p>Maintains a set of {@link ListenerLinker} <i>singletons</i> which are used 
 * by any {@link Activity} which implements the {@link Activity}.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public enum ListenerLinkers implements ListenerLinker {

	/**
	 * <p>This {@link ListenerLinker} is responsible for linking 
	 * the {@link View.OnClickListener}s of an {@link Activity}.</p>
	 * 
	 * @since 1.1.0
	 */
	CLICK(ClickListenerLinker.INSTANCE);
	
	
	/**
	 * <p>The wrapped {@link ListenerLinker} <i>singleton</i>.</p>
	 * 
	 * @since 1.1.0
	 */
	private ListenerLinker listenerLinker;
	
	/**
	 * <p>A parameterized constructor which initializes the 
	 * {@link #injector}.</p>
	 * 
	 * @param listenerLinker
	 * 			populates {@link #listenerLinker}
	 * <br><br>
	 * @since 1.1.0
	 */
	private ListenerLinkers(ListenerLinker listenerLinker) {
		
		this.listenerLinker = listenerLinker;
	}

	/**
	 * <p>Delegate for invoking the {@link #listenerLinker}'s 
	 * {@link ListenerLinker#inject(com.lonepulse.icklebot.listener.ListenerLinker.Configuration))} 
	 * service.</p>
	 * 
	 * @since 1.1.0
	 */
	@Override
	public void link(Configuration config) {
		
		try {
			
			listenerLinker.link(config);
		}
		catch(Exception e) {
			
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("Listener linking using ");
			stringBuilder.append(listenerLinker.getClass().getName());
			stringBuilder.append(" failed on activity ");
			stringBuilder.append(config.getActivity().getClass().getName());
			stringBuilder.append(". ");
			
			Log.e(getClass().getName(), stringBuilder.toString(), e);
		}
	}
}
