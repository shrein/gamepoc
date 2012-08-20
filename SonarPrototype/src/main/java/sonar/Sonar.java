package sonar;

import engine.Model;
import engine.Vector;

public class Sonar extends Model {

	private static final double SCALE_DECAY = 0.975d;
	private final int NORMAL_SCALER = 512;
	private final int ENEMY_SCALER = 128;
	private final int DIE_SCALER = 1024;

	public enum SonarType {
		NORMAL_SONAR, ENEMY_SONAR, DIE_SONAR;
	}

	// Custom state variables.
	private double circleDelay = 2d;
	private double circleElapsed = 0d;
	private double scale = 0d;
	private double scaleSpeed = 0d;

	// Event variables.
	private boolean normalSonarEvent = false;
	private boolean enemySonarEvent = false;
	private boolean dieSonarEvent = false;

	public Sonar() {
		position = new Vector(0d, 0d);
		velocity = new Vector(0d, 0d);
		scale = 0d;
		alive = false;
	}

	public void update(double elapsed) {
		if (alive) {
			scaleSpeed *= SCALE_DECAY;
			scale += scaleSpeed * elapsed;
			if (scaleSpeed <= 64) {
				alive = false;
			}
		}
	}

	public void triggerSonarEvent(Vector position, SonarType type) {
		if (circleElapsed > circleDelay) {
			this.position.set(position);
			circleElapsed = 0d;
			alive = true;
			scale = 0;
			if (type == SonarType.NORMAL_SONAR) {
				scaleSpeed = NORMAL_SCALER;
			} else if (type == SonarType.ENEMY_SONAR) {
				scaleSpeed = ENEMY_SCALER;
			} else if (type == SonarType.DIE_SONAR) {
				scaleSpeed = DIE_SCALER;
			}

		}
	}

	public boolean isNormalSonarEvent() {
		return normalSonarEvent;
	}

	public boolean isEnemySonarEvent() {
		return enemySonarEvent;
	}

	public boolean isDieSonarEvent() {
		return dieSonarEvent;
	}

	@Override
	public void clearEvents() {
		normalSonarEvent = false;
		enemySonarEvent = false;
		dieSonarEvent = false;
	}

	public double getScale() {
		return scale;
	}

}