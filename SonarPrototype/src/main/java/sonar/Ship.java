package sonar;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import engine.Vector;

/**
 * This is the Ship model class, and the root class in the aggregate
 * Ship-Sonar-Bullets.
 * 
 * @author fdevant, shrein
 * 
 */
public class Ship extends SonarModel {

	private static final double SHIP_RADIUS = 15d;

	@Inject
	private Utility utility;

	// Model objects managed by this root model object.
	private Sonar sonar;
	private List<Bullet> bullets;

	// Custom state variables.
	private double drag = 0.9d;
	private int maxBullets = 20;
	private double gunDelay = 0.5d;
	private double gunElapsed = 0d;
	private double acceleration = 16d;

	// Event variables
	private boolean dieEvent = false;

	public Ship(Vector _position) {
		super();
		alive = true;
		radius = SHIP_RADIUS;
		
		sonar = new Sonar();
		bullets = new ArrayList<Bullet>();
		for (int i = 0; i < maxBullets; i++) {
			Bullet bullet = new Bullet(this);
			bullets.add(bullet);
		}

		position = new Vector(_position);
		velocity = new Vector();
	}

	/**
	 * Apply a force to the ship ("All power to the engines!").
	 * 
	 * @param angle
	 *            The angle in which the force is applied.
	 * @param elapsed
	 *            The elapsed time since the last time-step.
	 */
	public void applyForce(double angle, double elapsed) {
		theta = angle;
		velocity.x += sin(angle) * acceleration * elapsed;
		velocity.y += cos(angle) * acceleration * elapsed;
	}

	@Override
	public void clearEvents() {
		this.dieEvent = false;
		sonar.clearEvents();
		for (Bullet bullet : bullets) {
			bullet.clearEvents();
		}
	}

	/**
	 * Triggers the DieEvent(TM), so that the view can react accordingly.
	 * 
	 */
	public void die() {
		this.dieEvent = true;
		this.alive = false;
		sonar.triggerSonarEvent(position, Sonar.SonarType.DIE_SONAR);
	}

	/**
	 * Fires a bullet if the elapsed time since the last fired bullet is greater
	 * than gunDelay.
	 * 
	 * @param target
	 *            The position vector targeted by the bullet. If NULL, the
	 *            current ship angle is used.
	 */
	public void fireBullet(Vector target) {
		if (gunElapsed > gunDelay) {
			gunElapsed = 0;
			for (int i = 0; i < bullets.size(); i++) {
				Bullet currentBullet = (Bullet) bullets.get(i);
				if (!currentBullet.alive) {
					double dir = theta;
					if (target != null) {
						dir = atan2(target.x - position.x,
								-(target.y - position.y));
					}
					currentBullet.spawn(position, dir);
					break;
				}
			}
		}
	}

	/**
	 * Fires the sonar.
	 */
	public void fireSonar() {
		sonar.triggerSonarEvent(position, Sonar.SonarType.NORMAL_SONAR);
	}

	@Override
	public void update(double elapsed) {
		sonar.update(elapsed);

		for (int i = 0; i < bullets.size(); i++) {
			((Bullet) bullets.get(i)).update(elapsed);
		}

		gunElapsed += elapsed;

		velocity.mult(drag);
		position.add(new Vector(velocity.x * elapsed, velocity.y * elapsed));
		position = utility.loopSpace(position);
	}

	/**
	 * @return Sonar model for this ship
	 */
	public Sonar getSonar() {
		return sonar;
	}

	/**
	 * @return Bullets models for this ship.
	 */
	public List<Bullet> getMyBullets() {
		return bullets;
	}

	/**
	 * @return True if this Ship died since the last update.
	 */
	public boolean isDieEvent() {
		return dieEvent;
	}

	@Override
	public CollisionEnum getCollisionClass() {
		return CollisionEnum.SHIP;
	}	
}