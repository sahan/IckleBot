package com.lonepulse.icklebot.util;

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
 * <p>This contract is an extension of all the life-cycle {@link Phases}. It can 
 * be implemented to conform to a <b>complete</b> life-cycle. In addition it offers 
 * delegates to the each phase contract so that they may be implemented in isolation. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface LifeCycle extends Phases.Create, Phases.Destroy {

	/** 
	 * <p>A delegate to the {@link Phases.Create} contract.
	 * 
	 * @since 1.1.0
	 */ 
	public static interface Create extends Phases.Create {}
	
	/** 
	 * <p>A delegate to the {@link Phases.Destroy} contract.
	 * 
	 * @since 1.1.0
	 */ 
	public static interface Destroy extends Phases.Destroy {}
}
