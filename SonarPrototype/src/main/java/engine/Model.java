package engine;

import processing.core.PVector;

/**
 * Abstract class that represents Model (stateful) objects.
 * 
 * @author shrein
 * 
 */
public abstract class Model implements Comparable<Integer> {
	private PVector position;
	private PVector velocity;
	private float theta;
	private float omega;
	private int modelDepth;
	private boolean visible;
	private boolean alive;
	private boolean ghost;

	@Override
	public int compareTo(Integer otherModelDepth) {
		return (modelDepth - otherModelDepth);
	}

	/**
	 * @return Position vector.
	 */
	public PVector getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            Position vector.
	 */
	public void setPosition(PVector position) {
		this.position = position;
	}

	/**
	 * @return Velocity vector.
	 */
	public PVector getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity
	 *            Velocity vector.
	 */
	public void setVelocity(PVector velocity) {
		this.velocity = velocity;
	}

	/**
	 * @return Model depth.
	 */
	public int getModelDepth() {
		return modelDepth;
	}

	/**
	 * @param modelDepth
	 *            Model depth.
	 */
	public void setModelDepth(int modelDepth) {
		this.modelDepth = modelDepth;
	}

	/**
	 * @return X-axis position component.
	 */
	public float getX() {
		return position.x;
	}

	/**
	 * @param x
	 *            X-axis position component.
	 */
	public void setX(float x) {
		position.x = x;
	}

	/**
	 * @return Y-axis position component.
	 */
	public float getY() {
		return position.y;
	}

	/**
	 * @param y
	 *            Y-axis position component.
	 */
	public void setY(float y) {
		position.y = y;
	}

	/**
	 * @return X-axis velocity component.
	 */
	public float getVx() {
		return velocity.x;
	}

	/**
	 * @param vx
	 *            X-axis velocity component.
	 */
	public void setVx(float vx) {
		velocity.x = vx;
	}

	/**
	 * @return Y-axis velocity component.
	 */
	public float getVy() {
		return velocity.y;
	}

	/**
	 * @param vy
	 *            Y-axis velocity component.
	 */
	public void setVy(float vy) {
		velocity.y = vy;
	}

	/**
	 * @return Counterclockwise (CCW) angle respect the X-axis.
	 */
	public float getTheta() {
		return theta;
	}

	/**
	 * @param theta
	 *            Counterclockwise (CCW) angle respect the X-axis.
	 */
	public void setTheta(float theta) {
		this.theta = theta;
	}

	/**
	 * @return Counterclockwise (CCW) angular velocity;
	 */
	public float getOmega() {
		return omega;
	}

	/**
	 * @param omega
	 *            Counterclockwise (CCW) angular velocity;
	 */
	public void setOmega(float omega) {
		this.omega = omega;
	}

	/**
	 * @return Depth in the scene.
	 */
	public int getDepth() {
		return modelDepth;
	}

	/**
	 * @param depth
	 *            Depth in the scene.
	 */
	public void setDepth(int depth) {
		this.modelDepth = depth;
	}

	/**
	 * @return Whether this object can be seen or not
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            Whether this object can be seen or not
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @return Whether this object exists in the scene or not.
	 */
	public boolean isAlive() {
		return alive;

	}

	/**
	 * @param alive
	 *            Whether this object exists in the scene or not.
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return Whether this object is affected by physics.
	 */
	public boolean isGhost() {
		return ghost;
	}

	/**
	 * @param ghost
	 *            Whether this object is affected by physics.
	 */
	public void setGhost(boolean ghost) {
		this.ghost = ghost;
	}

}
