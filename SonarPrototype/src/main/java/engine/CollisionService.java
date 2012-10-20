package engine;

public interface CollisionService {

	/**
	 * A generic method that triggers collisions.
	 * 
	 * @param m1
	 *            some model
	 * @param m2
	 *            another model
	 * @return whether the models collide
	 */
	public abstract boolean triggerCollision(Model m1, Model m2);

	/**
	 * Adds a model to the collision testing structure.
	 * 
	 * @param m
	 *            the model to add
	 */
	public abstract void addModel(Model m);

	/**
	 * Uses the collision testing structure for collisions, and triggers the
	 * collisions found.
	 */
	public abstract void performCollisions();

}