/**
 * 
 */
package com.lonepulse.icklebot.orm;

import java.io.FileNotFoundException;

/**
 * <p>This {@link RuntimeException} is thrown when the <b>ickle.orm.properties</b> 
 * does not exist in the <b>assets directory</b>. 
 * 
 * @version 1.1.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public class PropertyFileNotFoundException extends RuntimeException {


	private static final long serialVersionUID = 412578574206793676L;

	
	/**
	 * <p>See {@link RuntimeException#RuntimeException()}.
	 * 
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(FileNotFoundException fnfe) {
		
		this("File orm.properties is missing in the assets directory. " + 
			  "Please create the file and add fill the required properties.", fnfe);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(String detailMessage) {
		super(detailMessage);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * <p>See {@link RuntimeException#RuntimeException(String, Throwable)}.
	 *
	 * @since 1.1.0
	 */
	public PropertyFileNotFoundException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}
}
