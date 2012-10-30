package sonar;

import processing.core.PVector;
import engine.Environment;
import engine.SoundEnum;


public class Enemy {

	private SonarPrototype005 myApplet;
	private LevelState myState;
	private Environment e;

	BBox boundingBox;

	Circle myCircle;

	PVector pos, vel;
	float dir;
	float speed = 25;// speed multiplier
	float drag = 0.95f;

	boolean alive;
	boolean lucky;

	public Enemy(SonarPrototype005 pApplet, LevelState pState) {
		myApplet = pApplet;
		myState=pState;
		this.e = pApplet.getE();
		boundingBox = new BBox(SonarPrototype005.CENTER);
		myCircle = new Circle(myApplet);

		pos = new PVector(
				myApplet.random(myApplet.width),
				myApplet.random(myApplet.height));
		vel = new PVector(0, 0); // remember not to create new objects in
									// runtime
		alive = true;
		lucky = false;
	}

	public void update() {
		myCircle.update();
		myCircle.drawBuffer();

		if (alive) {
			if (myApplet.random(1) > 0.95f) {
				vel.y += (4 * speed) - myApplet.random(speed * 8);
			}
			if (myApplet.random(1) > 0.95f) {
				vel.x += (4 * speed) - myApplet.random(speed * 8);
			}

			vel.mult(drag);
			pos.add(new PVector(vel.x * myApplet.elapsed, vel.y
					* myApplet.elapsed));
			pos = myState.utility.loopSpace(pos);

			dir = SonarPrototype005.atan2(vel.x, -vel.y);

			boundingBox.update(pos, 20);

			// ESTO DEBERIA ESTAR EN LA logica, NO EN LOS ENEMIGOS ...
			if (boundingBox
					.collisionTest(myState.myShip.boundingBox)) {
				alive = false;
				myState.myShip.vel.set(0, 0, 0);
				myState.myShip.kill();
				for (int i = 0; i < myState.myShip.myCircles
						.size(); i++) {
					Circle currentCircle = (Circle) myState.myShip.myCircles
							.get(i);
					if (!currentCircle.alive) {
						currentCircle.setScale(1024);
						currentCircle.spawn(pos);
						e.play(SoundEnum.CIRCLE);
						e.play(SoundEnum.BULLET);
						break;
					}
				}
				e.play(SoundEnum.ENEMY);
				// myApplet.enemySound.trigger();
			}

			for (int i = 0; i < myState.myShip.myBullets.size(); i++) {
				Bullet tempBullet = ((Bullet) myState.myShip.myBullets
						.get(i));
				if (tempBullet.alive) {
					if (boundingBox.collisionTest(tempBullet.boundingBox)) {
						// ///////////daaamnnn//////////////////

						if (myApplet.random(1) > 0.9f) {
							lucky = true;
						} else {
							lucky = false;
						}

						if (!lucky) {
							myCircle.setScale(256);
						}

						alive = false;
						tempBullet.alive = false;
						myState.myShip.vel.set(0, 0, 0);
						e.play(SoundEnum.ENEMY);
						// myApplet.enemySound.trigger();

						myCircle.spawn(pos);
						myState.enemiesKilled++;
					}
				}
			}
		}
	}

	public void draw() {
		myCircle.draw();

		if (alive) {
			myApplet.pushMatrix();
			myApplet.translate(pos.x, pos.y);
			myApplet.rotate(dir);
			myApplet.stroke(255);
			myApplet.noFill();
			myApplet.beginShape();
			myApplet.vertex(-4, -4);
			myApplet.vertex(-4, 4);
			myApplet.vertex(4, 4);
			myApplet.vertex(4, -4);
			myApplet.vertex(-4, -4);
			myApplet.endShape();
			myApplet.popMatrix();
		}
	}
}