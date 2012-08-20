package engine;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Abstract class to be implemented for concrete views of Model classes. Each
 * view has the logic (but not the state) to draw and play the sounds (a.k.a.
 * sing).
 * 
 * @author shrein
 * 
 */
public abstract class View implements Comparable<View> {

	private final int viewDepth;
	private List<Model> modelReferences;

	public View(int depth) {
		super();
		this.viewDepth = depth;
		modelReferences = new ArrayList<Model>();
	}

	/**
	 * @return View depth.
	 */
	public int getViewDepth() {
		return viewDepth;
	}

	/**
	 * Returns the referenced models collection as an iterable.
	 * 
	 * @return Model references iterable.
	 */
	public Iterable<Model> getModelReferencesIterable() {
		return modelReferences;
	}

	/**
	 * Returns the first referenced model in the collection.
	 * 
	 * @return First Model instance in the references.
	 */
	public Model getFirstModelReference() {
		return modelReferences.get(0);
	}

	/**
	 * Adds a model instance to the referenced models collection.
	 * 
	 * @param m
	 *            Model to be referenced
	 */
	public void addModelReference(Model m) {
		modelReferences.add(m);
	}

	/**
	 * Removes a model instance to the referenced models collection.
	 * 
	 * @param m
	 *            Model to be removed.
	 */
	public void removeModelReference(Model m) {
		modelReferences.remove(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see engine.IView#compareTo(java.lang.Integer)
	 */
	@Override
	public int compareTo(View otherView) {
		return (viewDepth - otherView.getViewDepth());
	}

	/**
	 * Load view resources. Override as needed.
	 */
	public void setup(SoundManager sm, RenderManager rm) {
		// Do nothing by default.
	}

	/**
	 * Uses the RenderManager to draw the model instances (referenced by the
	 * View) on the scene.
	 * 
	 * @param r
	 *            The render manager.
	 */
	public abstract void draw(RenderManager r);

	/**
	 * Uses the SoundManager to play the sounds from the model instance.
	 * 
	 * @param r
	 *            The render manager.
	 */
	public abstract void sing(SoundManager s);

}
