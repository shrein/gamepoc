package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Set;

import processing.core.PApplet;

/**
 * Subclasses of this class handle both inputs to the game (via AWT Events from
 * Processing) and outputs from the game (using both the SoundManager and the
 * DisplayManager). A regular game loop is: Handle Events --> Update Model
 * (including physics) --> Update View
 * 
 * @author shrein
 */
public abstract class Controller implements KeyListener, MouseListener,
		ActionListener {

	PApplet parent;
	SoundManager sm;
	PhysicsManager pm;
	RenderManager dm;

	/**
	 * The set of model instances to be updated in the game loop.
	 */
	Set<Model> modelSet;

	/**
	 * The list of (stateless) views, sorted by depth, to be rendered and (its
	 * sounds) played.
	 */
	List<Model> viewList;

	public Controller(PApplet parent) {
		this.parent = parent;
		sm = new SoundManager(parent);
		pm = new PhysicsManager(parent);
		dm = new RenderManager(parent);
	}

	/**
	 * Setup method. It should be overridden to setup the specifics of the
	 * subclass, first calling super.setup().
	 */
	public void setup() {
		sm.setup();
		pm.setup();
		dm.setup();
	}

	/**
	 * Method used to update the model. It may include any logic of model
	 * dynamics, and also physics processing (collisions, etc).
	 */
	public abstract void updateModel();

	/**
	 * Method used to update the view. Called after model update.
	 */
	public abstract void updateView();

	// --- Event handling ---

	@Override
	public void actionPerformed(ActionEvent e) {
		// Does nothing if not overridden.
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Does nothing if not overridden.
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Does nothing if not overridden.
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Does nothing if not overridden.
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Does nothing if not overridden.
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Does nothing if not overridden.
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// Does nothing if not overridden.
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Does nothing if not overridden.
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Does nothing if not overridden.
	}
}
