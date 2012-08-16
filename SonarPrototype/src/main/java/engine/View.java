package engine;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Abstract class to be implemented for concrete views of Model classes. Each
 * view has the logic (but not the state) to draw and play the sounds (a.k.a.
 * sing)
 * 
 * @author shrein
 * 
 */
public abstract class View implements Comparable<View> {

	final int viewDepth;
	List<Model> modelReferences;

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
	 * Uses the RenderManager to draw the model instance on the scene.
	 * 
	 * @param r
	 *            The render manager.
	 * @param m
	 *            The specific model instance (e.g. Ship, Enemy, etc.)
	 */
	public abstract void draw(RenderManager r, Model m);

	/**
	 * Uses the SoundManager to play the sounds from the model instance.
	 * 
	 * @param r
	 *            The render manager.
	 * @param m
	 *            The specific model instance (e.g. Ship, Enemy, etc.)
	 */
	public abstract void sing(SoundManager s, Model m);

}
