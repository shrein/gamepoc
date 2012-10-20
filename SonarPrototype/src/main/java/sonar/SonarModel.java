package sonar;

import engine.Model;

/**
 * Model class for all models in Sonar. Provides a collision class to test for
 * collisions.
 * 
 * @author shrein
 */
public abstract class SonarModel extends Model {

	/**
	 * @return The collision class of the model. Corresponds to
	 *         CollisionEnum.valueOf(this) of each implementing class.
	 */
	public abstract CollisionEnum getCollisionClass();

}
