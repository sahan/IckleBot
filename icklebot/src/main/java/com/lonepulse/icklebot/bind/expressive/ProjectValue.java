package com.lonepulse.icklebot.bind.expressive;

import java.util.Map;
import java.util.Properties;

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
 * <p>This {@link Operator} exposes value in {@link Map}s or {@link Properties} which 
 * are <b>keyed using {@link String}s</b>, {@link Integer}s or {@link Double}s. For 
 * {@link Map}s, if the argument enclosed within [' and '] is a whole number it will 
 * be parsed to a {@link Long}, or if it is a fraction it will be parsed to a {@link Double}.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class ProjectValue extends AbstractOperator {


	/**
	 * <p>Instantiates a new {@link ProjectValue} operator. 
	 *
	 * @since 1.1.0
	 */
	public ProjectValue() {
		
		super(new Symbol("['", "']"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object onEvaluate(Object target, Object... args) {

		if(args == null || args.length != 1) {
			
			StringBuilder errorContext = new StringBuilder()
			.append(getClass().getName())
			.append(" requires a single String parameter as an argument. ");
			
			throw new IllegalArgumentException(errorContext.toString());
		}
		
		Object key = args[0];
		Object value = null;
		
		try {
			
			if(target instanceof Map<?, ?>)
				value = ((Map<?, ?>) target).get(key);
			
			else if(target instanceof Properties)
				value = ((Properties) target).getProperty(key.toString());
		}
		catch(Exception e) {
			
			throw new OperationFailedException(this, target, key, e);
		}
		
		if(value == null) {
			
			StringBuilder errorContext = new StringBuilder()
			.append("No entry was found in Map or Properties for the given key ")
			.append(key.toString());
			
			throw new OperationFailedException(errorContext.toString());
		}
		
		return value;
	}
}
