package sonar;

import processing.core.PVector;
import engine.Environment;
import engine.SoundEnum;


public class Enemy {

	private SonarPrototype005 sonarPrototype005;
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

	public Enemy(SonarPrototype005 sonarPrototype005, LevelState pState) {
		this.sonarPrototype005 = sonarPrototype005;
		myState=pState;
		this.e = sonarPrototype005.getE();
		boundingBox = new BBox(SonarPrototype005.CENTER);
		myCircle = new Circle(this.sonarPrototype005);

		pos = new PVector(
				this.sonarPrototype005.random(this.sonarPrototype005.width),
				this.sonarPrototype005.random(this.sonarPrototype005.height));
		vel = new PVector(0, 0); // remember not to create new objects in
									// runtime
		alive = true;
		lucky = false;
	}

	public void update() {
		myCircle.update();
		myCircle.drawBuffer();

		if (alive) {
			if (this.sonarPrototype005.random(1) > 0.95f) {
				vel.y += (4 * speed) - this.sonarPrototype005.random(speed * 8);
			}
			if (this.sonarPrototype005.random(1) > 0.95f) {
				vel.x += (4 * speed) - this.sonarPrototype005.random(speed * 8);
			}

			vel.mult(drag);
			pos.add(new PVector(vel.x * this.sonarPrototype005.elapsed, vel.y
					* this.sonarPrototype005.elapsed));
			pos = myState.utility.loopSpace(pos);

			dir = SonarPrototype005.atan2(vel.x, -vel.y);

			boundingBox.update(pos, 20);

			// ESTO DEBERIA ESTAR EN LA NAVE, NO EN LOS ENEMIGOS ...
			if (boundingBox
					.collisionTest(myState.myShip.boundingBox)) {
				//alive = false;
				myState.myShip.vel.set(0, 0, 0);
				myState.myShip.alive = false;
				for (int i = 0; i < myState.myShip.myCircles
						.size(); i++) {
					Circle currentCircle = (Circle) myState.myShip.myCircles
							.get(i);
					if (!currentCircle.alive) {
						currentCircle.setScale(1024);
						currentCircle.spawn(pos);
						e.play(SoundEnum.CIRCLE);
						e.play(SoundEnum.BULLET);
						// this.sonarPrototype005.circleSound.trigger();
						// this.sonarPrototype005.bulletSound.trigger();
						break;
					}
				}
				e.play(SoundEnum.ENEMY);
				// this.sonarPrototype005.enemySound.trigger();
			}

			for (int i = 0; i < myState.myShip.myBullets.size(); i++) {
				Bullet tempBullet = ((Bullet) myState.myShip.myBullets
						.get(i));
				if (tempBullet.alive) {
					if (boundingBox.collisionTest(tempBullet.boundingBox)) {
						// ///////////daaamnnn//////////////////

						if (this.sonarPrototype005.random(1) > 0.9f) {
							lucky = true;
						} else {
							lucky = false;
						}

						if (!lucky) {
							myCircle.setScale(128);
						}

						alive = false;
						tempBullet.alive = false;
						myState.myShip.vel.set(0, 0, 0);
						e.play(SoundEnum.ENEMY);
						// this.sonarPrototype005.enemySound.trigger();

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
			this.sonarPrototype005.pushMatrix();
			this.sonarPrototype005.translate(pos.x, pos.y);
			this.sonarPrototype005.rotate(dir);
			this.sonarPrototype005.stroke(255);
			this.sonarPrototype005.noFill();
			this.sonarPrototype005.beginShape();
			this.sonarPrototype005.vertex(-4, -4);
			this.sonarPrototype005.vertex(-4, 4);
			this.sonarPrototype005.vertex(4, 4);
			this.sonarPrototype005.vertex(4, -4);
			this.sonarPrototype005.vertex(-4, -4);
			this.sonarPrototype005.endShape();
			this.sonarPrototype005.popMatrix();
		}
	}
}