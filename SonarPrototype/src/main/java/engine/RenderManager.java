package engine;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * This class provides access to each View to the correct PGraphics to draw
 * into.
 * 
 * @author shrein
 * 
 */
public class RenderManager {

	private List<PGraphics> pgs;
	private PApplet p;

	public RenderManager(PApplet parent, int depth) {
		this.p = parent;
		pgs = new ArrayList<PGraphics>();
		for (int i = 0; i < pgs.size(); i++) {
			pgs.add(p.createGraphics(parent.width, parent.height, PApplet.P3D));
		}
		setup();
	}

	public void setup() {
		for (PGraphics pg : pgs) {
			pg.smooth();
			pg.ellipseMode(PApplet.CENTER);
			pg.rectMode(PApplet.CORNER);
			pg.background(0);
		}
	}

	public void draw() {
		// Collections.sort(viewList);
		// int currentDepth;
		// for (View view : viewList) {
		//
		// for (Model model : view.getModelReferencesIterable()) {
		// view.draw(this, model);
		// }
		// }
		//
		// pg.beginDraw();
		// pg.background(255);
		// pg.endDraw();
		// p.image(pg, 0, 0);
	}

	public PGraphics getGraphicsByDepth(int depth) {
		return pgs.get(depth);
	}

}
