/**
 * 
 */
package com.lonepulse.icklebot.orm;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>This contract specifies the services offered on a single <i>persistence 
 * unit</i> having multiple entities with metadata. 
 * 
 * @version 1.1.0
 * <br><br> 
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public interface PersistenceUnit {
	
	/**
	 * <p>Override this method to provide {@link Class} types of 
	 * the set of entities associated with this persistence unit.
	 * 
	 * @return the set of entities which this persistence unit 
	 * 		   encompasses
	 *
	 * @since 1.1.0
	 */
	public abstract <Entity extends Serializable> Set<Class<Entity>> entities();
}
