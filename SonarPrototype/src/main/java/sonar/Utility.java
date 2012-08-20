package sonar;

import processing.core.PApplet;
import processing.core.PVector;
import engine.PhysicsManager;
import engine.Vector;

/**
 * @author fdevant
 */
public class Utility extends PhysicsManager {

	private int height;
	private int width;

	public Utility(PApplet parent) {
		super(parent);

		this.height = parent.getHeight();
		this.width = parent.getWidth();
	}

	public Vector loopSpace(PVector pPos) {
		float x, y;

		x = pPos.x;
		y = pPos.y;

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