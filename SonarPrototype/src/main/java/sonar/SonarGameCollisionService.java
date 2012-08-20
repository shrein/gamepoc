package sonar;

import engine.Model;

public class SonarGameCollisionService {

	// TODO: Maybe there is no need of this method, only the specific ones...
	public boolean triggerCollision(Model m1, Model m2) {
		if (m1 instanceof Enemy) {
			if (m2 instanceof Bullet) {
				return collideEnemyWithBullet((Enemy) m1, (Bullet) m2);
			} else if (m2 instanceof Ship) {
				return collideShipWithEnemy((Ship) m2, (Enemy) m1);
			}
		} else if (m1 instanceof Bullet) {
			if (m2 instanceof Enemy) {
				return collideEnemyWithBullet((Enemy) m2, (Bullet) m1);
			}

		} else if (m1 instanceof Ship) {
			if (m2 instanceof Enemy) {
				return collideShipWithEnemy((Ship) m1, (Enemy) m2);
			}
		}
		return false;
	}

	public boolean collideEnemyWithBullet(Enemy m1, Bullet m2) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean collideShipWithEnemy(Ship m1, Enemy m2) {
		// TODO Auto-generated method stub
		return false;
	}
}
