package sonar;

import processing.core.PVector;
import engine.Environment;
import engine.SoundEnum;

public class Enemy {

	private SonarPrototype005 myPApplet;
	private Environment e;

	BBox boundingBox;

	Circle myCircle;

	PVector pos, vel;
	float dir;
	float speed = 25;// speed multiplier
	float drag = 0.95f;
	boolean lucky;

	boolean alive;

	public Enemy(SonarPrototype005 pPApplet) {
		myPApplet = pPApplet;
		this.e = pPApplet.getE();
		boundingBox = new BBox(SonarPrototype005.CENTER);
		myCircle = new Circle(myPApplet);

		pos = new PVector(
				myPApplet.random(myPApplet.width),
				myPApplet.random(myPApplet.height));
		vel = new PVector(0, 0); // remember not to create new objects in
									// runtime
		alive = true;
		lucky = false;
	}

	public void update() {
		myCircle.update();
		myCircle.drawBuffer();

		if (alive) {
			if (myPApplet.random(1) > 0.95f) {
				vel.y += (4 * speed) - myPApplet.random(speed * 8);
			}
			if (myPApplet.random(1) > 0.95f) {
				vel.x += (4 * speed) - myPApplet.random(speed * 8);
			}

			vel.mult(drag);
			pos.add(new PVector(vel.x * myPApplet.elapsed, vel.y
					* myPApplet.elapsed));
			pos = myPApplet.utility.loopSpace(pos);

			dir = SonarPrototype005.atan2(vel.x, -vel.y);

			boundingBox.update(pos, 20);

			//TODO: ESTO DEBERIA ESTAR EN LA NAVE, NO EN LOS ENEMIGOS ...
			if (boundingBox.collisionTest(myPApplet.myShip.boundingBox)) {
				/*TODO:este myPApplet.myShip.... me incomoda, ser‡ que es buena idea crear al principio
				 un Ship myShip que se conecte con myPAplet.myShip? no se como manejar eso
				 tampoco se como poner preguntas :P
				*/
				//alive = false;
				if(myPApplet.myShip.alive){
				e.play(SoundEnum.CIRCLE);
				e.play(SoundEnum.BULLET);
				e.play(SoundEnum.ENEMY);
				myPApplet.myShip.vel.set(0, 0, 0);
				myPApplet.myShip.alive = false;
				
				for (int i = 0; i < myPApplet.myShip.myCircles
						.size(); i++) {
					Circle currentCircle = (Circle) myPApplet.myShip.myCircles
							.get(i);
					if (!currentCircle.alive) {
						currentCircle.setScale(1024);
						currentCircle.spawn(pos);
							break;
						}
					}
				}
				// myPApplet.circleSound.trigger();
				// myPApplet.bulletSound.trigger();
				// myPApplet.enemySound.trigger();
			}

			for (int i = 0; i < myPApplet.myShip.myBullets.size(); i++) {
				Bullet tempBullet = ((Bullet) myPApplet.myShip.myBullets
						.get(i));
				if (tempBullet.alive) {
					if (boundingBox.collisionTest(tempBullet.boundingBox)) {
						// ///////////daaamnnn//////////////////

						if (myPApplet.random(1) > 0.9f) {
							lucky = true;
						} else {
							lucky = false;
						}

						if (!lucky) {
							myCircle.setScale(256);
						}

						alive = false;
						tempBullet.alive = false;
						myPApplet.myShip.vel.set(0, 0, 0);
						e.play(SoundEnum.ENEMY);
						// myPApplet.enemySound.trigger();

						myCircle.spawn(pos);
						myPApplet.enemiesKilled++;
					}
				}
			}
		}
	}

	public void draw() {
		myCircle.draw();

		if (alive) {
			myPApplet.pushMatrix();
			myPApplet.translate(pos.x, pos.y);
			myPApplet.rotate(dir);
			myPApplet.stroke(255);
			myPApplet.noFill();
			myPApplet.beginShape();
			myPApplet.vertex(-4, -4);
			myPApplet.vertex(-4, 4);
			myPApplet.vertex(4, 4);
			myPApplet.vertex(4, -4);
			myPApplet.vertex(-4, -4);
			myPApplet.endShape();
			myPApplet.popMatrix();
		}
	}
}