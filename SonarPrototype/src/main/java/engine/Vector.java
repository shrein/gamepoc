package engine;

import processing.core.PVector;

/**
 * A wrapper Vector class to hide the not-so-cool PVector. Also, it handles
 * double math.
 * 
 * @author shrein
 * 
 */
public class Vector extends PVector {

	private static final long serialVersionUID = 7267134542339175481L;

	// Constructors
	public Vector() {
		super();
	}

	public Vector(PVector v) {
		super(v.x, v.y);
	}

	public Vector(double x, double y) {
		super((float) x, (float) y);
	}

	// Static methods
	public static void mult(Vector inputV, double x, Vector outputV) {
		PVector.mult(inputV, (float) x, outputV);
	}

	public static Vector add(Vector v1, Vector v2) {
		return new Vector(PVector.add(v1, v2));
	}

	// Instance methods
	public void add(double x, double y, double z) {
		super.add((float) x, (float) y, (float) z);
	}

	public void div(double n) {
		super.div((float) n);
	}

	public float dot(double x, double y, double z) {
		return super.dot((float) x, (float) y, (float) z);
	}

	public void limit(double max) {
		super.limit((float) max);
	}

	public void mult(double n) {
		super.mult((float) n);
	}

	public void sub(double x, double y, double z) {
		super.sub((float) x, (float) y, (float) z);
	}

}
