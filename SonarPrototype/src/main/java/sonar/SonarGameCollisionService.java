package sonar;

import engine.Model;
import engine.NaiveCollisionService;

/**
 * The collision service for Sonar. If you are looking where to define what
 * happens when two objects collide, this is right the place.
 * 
 * @author shrein
 */
public class SonarGameCollisionService extends NaiveCollisionService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see sonar.CollisionService#triggerCollision(engine.Model, engine.Model)
	 */
	@Override
	public boolean triggerCollision(Model m1, Model m2) {
		/*
		 * Implementation notes: Visitor pattern? That one that requires O(n^2)
		 * changes to classes? I rather prefer type-unsafety. And better than
		 * that, real pattern matching (or at least default implementations for
		 * interface methods). OK, rant mode off.
		 */

		// Sort by collisionClass order.
		CollisionEnum class1 = ((SonarModel) m1).getCollisionClass();
		CollisionEnum class2 = ((SonarModel) m2).getCollisionClass();
		CollisionEnum lowerClass, higherClass;
		SonarModel lowerModel, higherModel;
		if (class1.compareTo(class2) <= 0) {
			lowerClass = class1;
			higherClass = class2;
			lowerModel = (SonarModel) m1;
			higherModel = (SonarModel) m2;
		} else {
			lowerClass = class2;
			higherClass = class1;
			lowerModel = (SonarModel) m2;
			higherModel = (SonarModel) m1;
		}

		// Poor man's pattern matching!!
		switch (lowerClass) {
		case BULLET:
			switch (higherClass) {
			case ENEMY:
				return collideBulletWithEnemy((Bullet) lowerModel,
						(Enemy) higherModel);
			}
			break;
		case ENEMY:
			switch (higherClass) {
			case SHIP:
				return collideEnemyWithShip((Enemy) lowerModel,
						(Ship) higherModel);
			}
			break;
		}
		return false;
	}

	public boolean collideBulletWithEnemy(Bullet b, Enemy e) {
		b.hit();
		e.die();
		return true;
	}

	public boolean collideEnemyWithShip(Enemy e, Ship s) {
		s.die();
		return false;
	}
}
