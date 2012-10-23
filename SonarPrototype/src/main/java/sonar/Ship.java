package sonar;

import java.util.ArrayList;

import processing.core.PVector;
import engine.Environment;
import engine.SoundEnum;

public class Ship {

	/**
	 * 
	 */
	private SonarPrototype005 sonarPrototype005;
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

	public Ship(SonarPrototype005 sonarPrototype005, LevelState pState) {
		myState=pState;
		this.sonarPrototype005 = sonarPrototype005;
		this.e = sonarPrototype005.getE();
		alive = true;

		boundingBox = new BBox(SonarPrototype005.CENTER);
		myBullets = new ArrayList<Bullet>();

		for (int i = 0; i < maxBullets; i++) {
			myBullets.add(new Bullet(this.sonarPrototype005));
		}

		myCircles = new ArrayList<Circle>();
		for (int i = 0; i < maxCircles; i++) {
			myCircles.add(new Circle(this.sonarPrototype005));
		}

		bulletElapsed = bulletDelay;
		circleElapsed = circleDelay;

		pos = new PVector(this.sonarPrototype005.width / 2,
				this.sonarPrototype005.height / 2);
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
			bulletElapsed += this.sonarPrototype005.elapsed;

			circleElapsed += this.sonarPrototype005.elapsed;

			vel.mult(drag);
			pos.add(new PVector(vel.x * this.sonarPrototype005.elapsed, vel.y
					* this.sonarPrototype005.elapsed));
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

			this.sonarPrototype005.pushMatrix();
			this.sonarPrototype005.translate(pos.x, pos.y);
			this.sonarPrototype005.rotate(dir);
			this.sonarPrototype005.stroke(255);
			this.sonarPrototype005.noFill();
			this.sonarPrototype005.beginShape();
			this.sonarPrototype005.vertex(-3, -6);
			this.sonarPrototype005.vertex(3, -6);
			this.sonarPrototype005.vertex(0, 6);
			this.sonarPrototype005.vertex(-3, -6);
			this.sonarPrototype005.endShape();
			this.sonarPrototype005.popMatrix();
		}
	}

	public void controlCall(boolean[] pKeys) {

		if (pKeys[this.sonarPrototype005._UP]) {
			vel.y -= speed;
		}

		if (pKeys[this.sonarPrototype005._DOWN]) {
			vel.y += speed;
		}

		if (pKeys[this.sonarPrototype005._LEFT]) {
			vel.x -= speed;
		}

		if (pKeys[this.sonarPrototype005._RIGHT]) {
			vel.x += speed;
		}

		if (pKeys[this.sonarPrototype005._C]
				|| pKeys[this.sonarPrototype005._CLICK]) {
			if (bulletElapsed > bulletDelay) {
				bulletElapsed = 0;
				for (int i = 0; i < myBullets.size(); i++) {
					Bullet currentBullet = (Bullet) myBullets.get(i);
					if (!currentBullet.alive) {
						if (pKeys[this.sonarPrototype005._CLICK])
							currentBullet.spawn(pos, SonarPrototype005.atan2(
									this.sonarPrototype005.mouseX - pos.x,
									-(this.sonarPrototype005.mouseY - pos.y)));
						if (pKeys[this.sonarPrototype005._C])
							currentBullet.spawn(pos, dir);
						e.play(SoundEnum.BULLET);
						// this.sonarPrototype005.bulletSound.trigger();
						break;
					}
				}
			}
		}

		if (pKeys[this.sonarPrototype005._X]) {
			if (circleElapsed > circleDelay) {
				circleElapsed = 0;
				for (int i = 0; i < myCircles.size(); i++) {
					Circle currentCircle = (Circle) myCircles.get(i);
					if (!currentCircle.alive) {
						currentCircle.spawn(pos);
						e.play(SoundEnum.CIRCLE);
						// this.sonarPrototype005.circleSound.trigger();
						break;
					}
				}
			}
		}

		if (pKeys[this.sonarPrototype005._Z]) {
		}
	}
}