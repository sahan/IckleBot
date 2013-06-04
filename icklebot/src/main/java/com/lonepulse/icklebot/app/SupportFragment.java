package com.lonepulse.icklebot.app;

import com.lonepulse.icklebot.activity.IckleSupportManager;

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


/**
 * <p>This contract is followed by Fragments which offer services 
 * supported by the {@link IckleSupportManager}.
 * 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface SupportFragment extends Fragment {

	/**
	 * <p>Retrieves the instance of the {@link IckleSupportManager} 
	 * associated with this {@link SupportFragment}. 
	 *
	 * @return the associated {@link IckleSupportManager}
	 * 
	 * @since 1.1.0
	 */
	IckleSupportManager getSupportManager();
}
