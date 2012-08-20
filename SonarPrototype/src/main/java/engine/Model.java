package engine;

/**
 * Abstract class that represents Model (stateful) objects. Views depend on
 * Models, but not the other way around. Models may reference other models (i.e.
 * the root Model in an aggregate references all its contained Models).
 * 
 * @author shrein
 * 
 */
public abstract class Model {
	// Time step
	protected Vector position = null;
	protected double radius = 0d;
	protected Vector velocity = null;
	protected double theta = 0d;
	protected double omega = 0d;
	protected boolean visible = true;
	protected boolean alive = true;
	protected boolean ghost = false;

	private Vector tempV = new Vector(0f, 0f);

	/**
	 * Updates the model (specially on physically meaningful variables). Is
	 * called by the PhysicsManager. Override as needed.
	 * 
	 * @param elapsed
	 *            The time elapsed since the last time-step.
	 */
	public void update(double elapsed) {
		// By default move following inertia.
		position.add(new Vector(velocity.x * elapsed, velocity.y * elapsed));
	}

	// /**
	// * Triggers the events related to the collision of this model instance
	// with
	// * another model instance (Accept method on the Visitor pattern). Usually
	// no
	// * need to override.
	// *
	// * @param m
	// * The model that collided with this one.
	// * @return true if there was an event triggered, or false otherwise.
	// */
	// public boolean acceptCollision(Model m) {
	// return m.visitCollision(this);
	// }

	// /**
	// * Triggers the events related to the collision of this model instance
	// with
	// * another model instance (Visit method on the Visitor pattern). Usually
	// no need to override BUT there is a need to
	// *
	// * @param model
	// * @return
	// */
	// public boolean visitCollision(Model model) {
	// // Do nothing by default.
	// return false;
	// }

	/**
	 * @return Position vector.
	 */
	public Vector getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            Position vector.
	 */
	public void setPosition(Vector position) {
		this.position = position;
	}

	/**
	 * @return Radius of the circumscribed circle.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            Radius of the circumscribed circle.
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * @return Velocity vector.
	 */
	public Vector getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity
	 *            Velocity vector.
	 */
	public void setVelocity(Vector velocity) {
		this.velocity = velocity;
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
	public double getTheta() {
		return theta;
	}

	/**
	 * @param theta
	 *            Counterclockwise (CCW) angle respect the X-axis.
	 */
	public void setTheta(double theta) {
		this.theta = theta;
	}

	/**
	 * @return Counterclockwise (CCW) angular velocity;
	 */
	public double getOmega() {
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

	/**
	 * @param elapsed
	 *            Time-step for this step.
	 * @return The position in the next time step. Useful for colission
	 *         evaluation.
	 */
	public Vector getNextPosition(double elapsed) {
		Vector.mult(velocity, elapsed, tempV);
		Vector nextPosition = Vector.add(position, tempV);
		return nextPosition;
	}

	/**
	 * Clears all events on this model instance and on the inner models
	 * instances managed by this one.
	 */
	public abstract void clearEvents();

}
