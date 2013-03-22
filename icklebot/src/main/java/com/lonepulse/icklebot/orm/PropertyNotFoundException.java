/**
 * 
 */
package com.lonepulse.icklebot.orm;


/**
 * <p>This {@link RuntimeException} is thrown when a particular property 
 * is not found in <b>ickle.orm.properties</b>. 
 * 
 * @version 1.1.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class PropertyNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 2314517192321917002L;
	

	/**
	 * <p>Takes the missing property and prints a detailed 
	 * message.
	 *
	 * @param property
	 * 			the property which was missing
	 * 
	 * @since 1.1.0
	 */
	public PropertyNotFoundException(String property) {
		
		super("Property " + property + " is missing in the assets directory. ");
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyNotFoundException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyNotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
