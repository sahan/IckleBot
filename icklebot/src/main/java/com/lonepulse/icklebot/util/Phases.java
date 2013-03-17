package com.lonepulse.icklebot.util;

/**
 * <p>{@link Phases} does not offer a contract and is available as an enum to 
 * oppose the notion of a marker interface. It serves as a contract aggregator 
 * for each life-cycle phase. 
 * 
 * @version 1.1.0
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
enum Phases { 

	;
	
	/**
	 * <p>This contract declares the services offered for creating 
	 * a stateful object.
	 * 
	 * @since 1.1.0
	 */
	public static interface Create {
		
		void onCreate();
	}
	
	/**
	 * <p>This contract declares the services offered for destroying 
	 * a stateful object.
	 * 
	 * @since 1.1.0
	 */
	public static interface Destroy {
		
		void onDestroy();
	}
}

