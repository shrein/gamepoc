package co.org.unal.alife.visual;

import processing.core.*;

public class PendulumVis extends PApplet {

	private static final long serialVersionUID = -1828447365732253316L;
	private PendulumShape s;
	
	@Override
	public void setup() {
		size(800, 600, JAVA2D);
		println("Rendering with: "+ g.getClass().toString()); 
		smooth();
		ellipseMode(CENTER);
		rectMode(CORNER);
		s = new PendulumShape(this);
		background(255);
	}

	@Override
	public void draw() {
		fill(255,3);
		rect(0,0,width,height);
		s.move();
		s.display();
	}

}
