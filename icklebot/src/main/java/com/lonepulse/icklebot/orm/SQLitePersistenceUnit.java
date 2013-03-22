/**
 * 
 */
package com.lonepulse.icklebot.orm;

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
