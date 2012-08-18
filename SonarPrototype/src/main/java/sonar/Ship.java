package sonar;

import java.util.ArrayList;

import processing.core.PVector;
import engine.Environment;
import engine.SoundEnum;

public class Ship {

	/**
	 * 
	 */
	private SonarPrototype005 myPApplet;
	ArrayList<Circle> myCircles;
	ArrayList<Bullet> myBullets;

	BBox boundingBox;

	PVector pos, vel;
	float dir;
	float speed = 16;// speed multiplier
	float drag = 0.9f;
	int maxBullets = 20;
	int maxCircles = 5;
	float bulletDelay = 0.5f;
	float bulletElapsed;
	float circleDelay = 2;
	float circleElapsed;

	boolean alive;
	private Environment e;

	public Ship(SonarPrototype005 pPApplet) {

		myPApplet = pPApplet;
		e = myPApplet.getE();
		alive = true;

		boundingBox = new BBox(SonarPrototype005.CENTER);
		myBullets = new ArrayList<Bullet>();

		for (int i = 0; i < maxBullets; i++) {
			myBullets.add(new Bullet(myPApplet));
		}

		myCircles = new ArrayList<Circle>();
		for (int i = 0; i < maxCircles; i++) {
			myCircles.add(new Circle(myPApplet));
		}

		bulletElapsed = bulletDelay;
		circleElapsed = circleDelay;

		pos = new PVector(myPApplet.width / 2,
				myPApplet.height / 2);
		SonarPrototype005.println(pos);
		vel = new PVector(0, 0); // remember not to create new objects in
									// runtime
	}

	public void update(boolean[] pKeys) {

		for (int i = 0; i < myCircles.size(); i++) {
			((Circle) myCircles.get(i)).update();
			((Circle) myCircles.get(i)).drawBuffer();
		}

		if (alive) {

			controlCall(pKeys);

			for (int i = 0; i < myBullets.size(); i++) {
				((Bullet) myBullets.get(i)).update();
			}
			bulletElapsed += myPApplet.elapsed;

			circleElapsed += myPApplet.elapsed;

			vel.mult(drag);
			pos.add(new PVector(vel.x * myPApplet.elapsed, vel.y
					* myPApplet.elapsed));
			pos = myPApplet.utility.loopSpace(pos);
			if (vel.x != 0 || vel.y != 0) {
				dir = SonarPrototype005.atan2(vel.x, -vel.y);
			}
			boundingBox.update(pos, 15);
		}
	}

	public void draw() {

		for (int i = 0; i < myCircles.size(); i++) {
			((Circle) myCircles.get(i)).draw();
		}

		if (alive) {

			for (int i = 0; i < myBullets.size(); i++) {
				((Bullet) myBullets.get(i)).draw();
			}

			myPApplet.pushMatrix();
			myPApplet.translate(pos.x, pos.y);
			myPApplet.rotate(dir);
			myPApplet.stroke(255);
			myPApplet.noFill();
			myPApplet.beginShape();
			myPApplet.vertex(-3, -6);
			myPApplet.vertex(3, -6);
			myPApplet.vertex(0, 6);
			myPApplet.vertex(-3, -6);
			myPApplet.endShape();
			myPApplet.popMatrix();
		}
	}

	public void controlCall(boolean[] pKeys) {

		if (pKeys[myPApplet._UP]) {
			vel.y -= speed;
		}

		if (pKeys[myPApplet._DOWN]) {
			vel.y += speed;
		}

		if (pKeys[myPApplet._LEFT]) {
			vel.x -= speed;
		}

		if (pKeys[myPApplet._RIGHT]) {
			vel.x += speed;
		}

		if (pKeys[myPApplet._C]
				|| pKeys[myPApplet._CLICK]) {
			if (bulletElapsed > bulletDelay) {
				bulletElapsed = 0;
				for (int i = 0; i < myBullets.size(); i++) {
					Bullet currentBullet = (Bullet) myBullets.get(i);
					if (!currentBullet.alive) {
						if (pKeys[myPApplet._CLICK])
							currentBullet.spawn(pos, SonarPrototype005.atan2(
									myPApplet.mouseX - pos.x,
									-(myPApplet.mouseY - pos.y)));
						if (pKeys[myPApplet._C])
							currentBullet.spawn(pos, dir);
						e.play(SoundEnum.BULLET);
						// myPApplet.bulletSound.trigger();
						break;
					}
				}
			}
		}

		if (pKeys[myPApplet._X]) {
			if (circleElapsed > circleDelay) {
				circleElapsed = 0;
				for (int i = 0; i < myCircles.size(); i++) {
					Circle currentCircle = (Circle) myCircles.get(i);
					if (!currentCircle.alive) {
						currentCircle.spawn(pos);
						e.play(SoundEnum.CIRCLE);
						// myPApplet.circleSound.trigger();
						break;
					}
				}
			}
		}

		if (pKeys[myPApplet._Z]) {
		}
	}
}