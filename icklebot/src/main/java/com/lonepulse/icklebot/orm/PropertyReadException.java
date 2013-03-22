/**
 * 
 */
package com.lonepulse.icklebot.orm;

import com.lonepulse.icklebot.orm.PropertyReader.PROPERTY;

/**
 * <p>This {@link RuntimeException} is thrown when a given property 
 * is not found or cannot be read from the <b>ickle.orm.properties</b> file. 
 * 
 * @version 1.1.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class PropertyReadException extends RuntimeException {

	
	private static final long serialVersionUID = 2295492366218411918L;
	

	/**
	 * <p>Provides additional information about the missing property.
	 * 
	 * @param property
	 * 			the missing {@link PROPERTY}
	 * 
	 * @since 1.1.0
	 */
	public PropertyReadException(PROPERTY property, Throwable rootCause) {
	
		this("Failed to read " + property.getKey() + " from file ickle.orm.properties." +
			 " Please add the " + property.getKey() + " property with suitable value. ", rootCause);
	}
	
	/**
	 * <p>See {@link RuntimeException#RuntimeException()}.
	 * 
	 * @since 1.1.0
	 */
	public PropertyReadException() {}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyReadException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyReadException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyReadException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
