package com.lonepulse.icklebot.event.resolver;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import com.lonepulse.icklebot.annotation.event.Click;
import com.lonepulse.icklebot.annotation.event.ItemClick;
import com.lonepulse.icklebot.annotation.event.Touch;

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
 * <p>An implementation of {@link EventResolver} which caters to 
 * listener event linking.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
class BasicEventResolver implements EventResolver {

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<EventCategory> resolve(Method method) {

		Set<EventCategory> categories = new HashSet<EventCategory>();
		
		if(method.isAnnotationPresent(Click.class))
			categories.add(EventCategory.CLICK);
		
		if(method.isAnnotationPresent(ItemClick.class))
			categories.add(EventCategory.ITEM_CLICK);
		
		if(method.isAnnotationPresent(Touch.class))
			categories.add(EventCategory.TOUCH);
		
		return categories;
	}
}
