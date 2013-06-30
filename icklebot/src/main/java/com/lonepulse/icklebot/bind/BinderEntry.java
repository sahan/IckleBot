package com.lonepulse.icklebot.bind;

import java.lang.reflect.Field;

import android.view.View;

/**
 * <p>This entity aggregates a {@link Field} and an {@link AbstractBinder}. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class BinderEntry {

	
	/**
	 * <p>The {@link Field} to which contains the binding data and metadata. 
	 */
	private Field field;
	
	/**
	 * <p>The {@link AbstractBinder} which performs binding on the resolved 
	 * {@link View} using the data provided by {@link #field}.
	 */
	private AbstractBinder<? extends View, ? extends Object> binder;

	
	/**
	 * <p>Default constructor for lazy initialization when required.
	 *
	 * @since 1.1.0
	 */
	public BinderEntry() {}
	
	/**
	 * <p>Parameterized contstructor whihc initializes {@link #field} and {@link #binder}.
	 * 
	 * @param field
	 * 			the {@link Field} to which contains the binding data and metadata
	 * 
	 * @param binder
	 * 			the {@link AbstractBinder} which performs binding on the resolved 
	 * 			{@link View} using the data provided by {@link #field}
	 *
	 * @since 1.1.0
	 */
	public BinderEntry(Field field, AbstractBinder<? extends View, ? extends Object> binder) {
		
		this.field = field;
		this.binder = binder;
	}

	/**
	 * <p>Accessor for field.
	 *
	 * @return the field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * <p>Mutator for field.
	 *
	 * @param field 
	 *			the field to set
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * <p>Accessor for binder.
	 *
	 * @return the binder
	 */
	public AbstractBinder<? extends View, ? extends Object> getBinder() {
		return binder;
	}

	/**
	 * <p>Mutator for binder.
	 *
	 * @param binder 
	 *			the binder to set
	 */
	public void setBinder(AbstractBinder<? extends View, ? extends Object> binder) {
		this.binder = binder;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("BinderEntry [field=");
		builder.append(field);
		builder.append(", binder=");
		builder.append(binder);
		builder.append("]");
		
		return builder.toString();
	}
}
