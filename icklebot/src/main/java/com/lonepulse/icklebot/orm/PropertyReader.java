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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;

/**
 * <p>This utility class offers services for reading the <b>ickle.orm.properties</b> 
 * file in the <i>assets</i> directory which contains the configuration information 
 * for a single persistence unit.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
final class PropertyReader {

	
	/**
	 * <p>This enum identifies the name of the property to retrieve.
	 * 
	 * @version 1.1.0
	 * <br><br>
	 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
	 */
	public static enum PROPERTY {
		
		/**
		 * <p>The name of the persitence unit. All flat-files will follow the 
		 * naming convention {@code <name>.db}.
		 * 
		 * @since 1.1.0
		 */
		NAME("name"),
		
		/**
		 * <p>The current version of the database. To perform database upgrades 
		 * simply increment this number in the properties file.
		 * 
		 * @since 1.1.0
		 */
		VERSION("version");
		
		
		/**
		 * <p>The textual representation of the property identifier to be used 
		 * on the properties file.
		 */
		private final String key;
		
		
		/**
		 * <p>Default constructor which initialized {@link #key}.
		 * 
		 * @param key
		 * 			initialized {@link #key}
		 */
		private PROPERTY(String key) {
			
			this.key = key;
		}

		/**
		 * <p>Accessor for {@link #key}.
		 *
		 * @return {@link #key}
		 * 
		 * @since 1.1.0
		 */
		public String getKey() {
			
			return key;
		}
	}
	
	
	/**
	 * <p>The name of the property file to search for in the assets directory.
	 */
	private static final String PROPERTY_FILE = "ickle.orm.properties";
	
	/**
	 * <p>Constructor visibility restricted. Instantiation is non-sensical.
	 *
	 * @since 1.1.0
	 */
	private PropertyReader() {}
	
	
	/**
	 * <p>Takes a {@link PROPERTY} and reads it's value form the ickle.orm.properties 
	 * file in the asstes directory.
	 * 
	 * @param context
	 * 			the {@link Context} of the caller
	 * 
	 * @param property
	 * 			the {@link PROPERTY} identifier
	 * 
	 * @return the value of the property
	 * 
	 * @throws PropertyNotFoundException
	 * 			if the specified property is missing from ickle.orm.properties
	 * 
	 * @throws PropertyFileNotFoundException
	 * 			if <b>ickle.orm.properties</b> is missing from the assets directory
	 * 
	 * @throws PropertyReadException
	 * 			if the specified property is missing or cannot be read from 
	 * 			ickle.orm.properties
	 *
	 * @since 1.1.0
	 */
	public static final String read(Context context, PROPERTY property) {
		
		try {
		
			InputStream inputStream = context.getAssets().open(PROPERTY_FILE);
			
			Properties properties = new Properties();
			properties.load(inputStream);
			
			inputStream.close();
			
			String propertyValue = properties.getProperty(property.getKey());
			
			if(propertyValue == null) 
				throw new PropertyNotFoundException(propertyValue);
			
			return propertyValue;
		}
		catch(FileNotFoundException fnfe) {
			
			throw new PropertyFileNotFoundException(fnfe);
			
		}
		catch (Exception e) {
			
			throw new PropertyReadException(property, e);
		}
	}
}
