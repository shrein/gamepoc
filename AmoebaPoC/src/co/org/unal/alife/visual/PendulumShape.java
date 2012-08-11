package co.org.unal.alife.visual;

import processing.core.PApplet;

public class PendulumShape {
	private float _poleLength = 200;
	private float _poleWidth = 15;
	private float _baseRadius = 25;
	private float _tipRadius = 20;

	private PApplet p;
	float x = 0; // horizontal location
	float y = 0; // horizontal location
	float speed = 5; // horizontal speed
	float theta = 0; // angular position
	float omega = 0.01f; // angular speed
	boolean mouse; // state of stripe (mouse is over or not?)

	public PendulumShape(PApplet parent) {
		super();
		this.p = parent;
		x = p.width / 2;
		y = p.height / 2;
	}

	// Draw stripe
	void display() {
		p.fill(240,128);
		p.stroke(0);
		p.rect(0, y-_baseRadius-_poleWidth, p.width, _poleWidth);
		p.rect(0, y+_baseRadius, p.width, _poleWidth);
		p.pushMatrix();
		p.translate(x, y);
		p.rotate(-theta);
		p.fill(47, 91, 213, 255);
		p.ellipse(0, 0, 2 * _baseRadius, 2 * _baseRadius);
		p.ellipse(_poleLength, 0, 2 * _tipRadius, 2 * _tipRadius);
		p.fill(200, 255);
		p.rect(0, -_poleWidth / 2, _poleLength, _poleWidth);
		p.fill(255, 255);
		p.ellipse(0, 0, _poleWidth, _poleWidth);
		p.ellipse(_poleLength, 0, _poleWidth, _poleWidth);
		p.popMatrix();
	}

	// Move stripe
	void move() {
		x += speed;
		theta += omega;
		if (x > p.width + _poleLength)
			x = -_poleLength;
		if (omega > Math.PI)
			omega = (float) Math.PI;
	}
}
