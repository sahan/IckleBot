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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.lonepulse.icklebot.orm.PropertyReader.PROPERTY;

/**
 * <p>This is a concrete implementation of {@link PersistenceUnit} which 
 * realizes the domain as an <b>SQLite</b> database.
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public abstract class SQLitePersistenceUnit extends SQLiteOpenHelper 
implements PersistenceUnit {

	
	/**
	 * <p>See {@link SQLiteOpenHelper#SQLiteOpenHelper(Context, String, CursorFactory, int)}.
	 */
	public SQLitePersistenceUnit(Context context) {
		
		super(context, PropertyReader.read(context, PROPERTY.NAME), 
			   null, Integer.parseInt(PropertyReader.read(context, PROPERTY.VERSION)));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {

		Set<Class<Serializable>> entitySet = entities();
		
		//TODO read metadata and create schema
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

		Set<Class<Serializable>> entitySet = entities();
		
		//TODO read metadata and create schema
	}
}
