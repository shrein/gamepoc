package sonar;

import javax.inject.Inject;

import engine.PhysicsManager;
import engine.Vector;

/**
 * @author fdevant, shrein
 */
public class Utility {

	@Inject PhysicsManager manager;
	private int height;
	private int width;

	public Utility() {
		this.height = manager.getParent().getHeight();
		this.width = manager.getParent().getWidth();
	}

	public Vector loopSpace(Vector pPos) {
		double x = pPos.x;
		double y = pPos.y;

		if (pPos.x > height) {
			x = 0;
		}
		if (pPos.x < 0) {
			x = width;
		}
		if (pPos.y > height) {
			y = 0;
		}
		if (pPos.y < 0) {
			y = height;
		}
		return new Vector(x, y);
	}
}