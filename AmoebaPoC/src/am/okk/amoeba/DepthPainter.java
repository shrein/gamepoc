package am.okk.amoeba;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;

public class DepthPainter {

	private List<Drawable> list;
	private PGraphics pg;
	private PApplet p;
	
	public DepthPainter(PApplet parent) {
		this.p = parent;
		list = new ArrayList<Drawable>();
		pg = p.createGraphics(800,600,PGraphics.JAVA2D);
		setup();
	}
	
	public void setup() {
		pg.smooth();
		pg.ellipseMode(PApplet.CENTER);
		pg.rectMode(PApplet.CORNER);
		pg.background(0);
//		PFont font = p.loadFont("DraconianMicroLiner001.ttf");
//		pg.textFont(font, 10);
		for (Drawable d : list) {
			d.setGraphics(pg);
			d.setup();
		}
	}
	
	public void addDrawable(Drawable d) {
		list.add(d);
	}
	
	public void update() {
		for (Drawable d : list) {
			d.update();
		}
	}
	
	public void draw() {
		Collections.sort(list);
		pg.beginDraw();
		pg.background(255);
		for (Drawable d : list) {
			d.draw();
		}
		pg.endDraw();
		p.image(pg, 0, 0);
	}

}
