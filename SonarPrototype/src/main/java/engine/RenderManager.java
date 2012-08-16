package engine;

import java.util.Collections;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;

public class RenderManager {

	private PGraphics pg;
	private PApplet p;
	
	public RenderManager (PApplet parent) {
		this.p = parent;
		pg = p.createGraphics(parent.width,parent.height,PApplet.P3D);
		setup();
	}
	
	public void setup() {
		pg.smooth();
		pg.ellipseMode(PApplet.CENTER);
		pg.rectMode(PApplet.CORNER);
		pg.background(0);
	}
	
	public void draw(List<View> viewList) {
		Collections.sort(viewList);
		pg.beginDraw();
		pg.background(255);
		for (View view : viewList) {
			for (Model model : view.getModelReferencesIterable()) {
				view.draw(this, model);
			}
		}
		pg.endDraw();
		p.image(pg, 0, 0);
	}

	
}
