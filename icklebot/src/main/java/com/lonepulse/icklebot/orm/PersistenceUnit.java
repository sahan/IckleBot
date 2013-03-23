package com.lonepulse.icklebot.orm;

/*
 * #%L
 * IckleBot Library
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
import java.util.Set;

/**
 * <p>This contract specifies the services offered on a single <i>persistence 
 * unit</i> having multiple entities with metadata. 
 * 
 * @version 1.1.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface PersistenceUnit {
	
	/**
	 * <p>Override this method to provide {@link Class} types of 
	 * the set of entities associated with this persistence unit.
	 * 
	 * @return the set of entities which this persistence unit 
	 * 		   encompasses
	 *
	 * @since 1.1.0
	 */
	public abstract <Entity extends Serializable> Set<Class<Entity>> entities();
}
