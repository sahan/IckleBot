package com.lonepulse.icklebot.util;


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
