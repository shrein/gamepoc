package sonar;

import java.util.ArrayList;

import processing.core.PVector;
import engine.Environment;
import engine.SoundEnum;

public class Ship {

	/**
	 * 
	 */
	private SonarPrototype005 myApplet;
	private LevelState myState;
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

	public Ship(SonarPrototype005 pApplet, LevelState pState) {
		
		myState=pState;
		myApplet = pApplet;
		this.e = pApplet.getE();
	
	}
	
	void setup(){
		alive = true;
		//SonarPrototype005.println("new ship created "+alive);
		
		boundingBox = new BBox(SonarPrototype005.CENTER);
		myBullets = new ArrayList<Bullet>();

		for (int i = 0; i < maxBullets; i++) {
			myBullets.add(new Bullet(myApplet));
		}

		myCircles = new ArrayList<Circle>();
		for (int i = 0; i < maxCircles; i++) {
			myCircles.add(new Circle(myApplet));
		}

		bulletElapsed = bulletDelay;
		circleElapsed = circleDelay;

		pos = new PVector(myApplet.width / 2,
				myApplet.height / 2);
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
			bulletElapsed += myApplet.elapsed;

			circleElapsed += myApplet.elapsed;

			vel.mult(drag);
			pos.add(new PVector(vel.x * myApplet.elapsed, vel.y
					* myApplet.elapsed));
			pos = myState.utility.loopSpace(pos);
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

			myApplet.pushMatrix();
			myApplet.translate(pos.x, pos.y);
			myApplet.rotate(dir);
			myApplet.stroke(255);
			myApplet.noFill();
			myApplet.beginShape();
			myApplet.vertex(-3, -6);
			myApplet.vertex(3, -6);
			myApplet.vertex(0, 6);
			myApplet.vertex(-3, -6);
			myApplet.endShape();
			myApplet.popMatrix();
		}
	}
	
	public void kill(){
		alive=false;
		
	}

	public void controlCall(boolean[] pKeys) {

		if (pKeys[myApplet._UP]) {
			vel.y -= speed;
		}

		if (pKeys[myApplet._DOWN]) {
			vel.y += speed;
		}

		if (pKeys[myApplet._LEFT]) {
			vel.x -= speed;
		}

		if (pKeys[myApplet._RIGHT]) {
			vel.x += speed;
		}

		if (pKeys[myApplet._C]
				|| pKeys[myApplet._CLICK]) {
			if (bulletElapsed > bulletDelay) {
				bulletElapsed = 0;
				for (int i = 0; i < myBullets.size(); i++) {
					Bullet currentBullet = (Bullet) myBullets.get(i);
					if (!currentBullet.alive) {
						if (pKeys[myApplet._CLICK])
							currentBullet.spawn(pos, SonarPrototype005.atan2(
									myApplet.mouseX - pos.x,
									-(myApplet.mouseY - pos.y)));
						if (pKeys[myApplet._C])
							currentBullet.spawn(pos, dir);
						e.play(SoundEnum.BULLET);
						// myApplet.bulletSound.trigger();
						break;
					}
				}
			}
		}

		if (pKeys[myApplet._X]) {
			if (circleElapsed > circleDelay) {
				circleElapsed = 0;
				for (int i = 0; i < myCircles.size(); i++) {
					Circle currentCircle = (Circle) myCircles.get(i);
					if (!currentCircle.alive) {
						currentCircle.spawn(pos);
						e.play(SoundEnum.CIRCLE);
						// myApplet.circleSound.trigger();
						break;
					}
				}
			}
		}

		if (pKeys[myApplet._Z]) {
		}
	}
}