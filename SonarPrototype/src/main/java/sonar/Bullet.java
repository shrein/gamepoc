package sonar;

import processing.core.PVector;
import engine.Vector;

public class Bullet extends SonarModel {

	private double speed = 480d;

	private int life;
	private int age;

	public Bullet(Ship ship) {
		life = 2;
		age = 0;
		position = new Vector(0, 0);
		velocity = new Vector(0, 0);
		alive = false;
	}

	public void spawn(Vector _position, double _theta) {
		position.set(_position);
		theta = _theta;
		velocity = new Vector(Math.sin(_theta), Math.cos(_theta));
		velocity.mult(speed);
		alive = true;
		age = 0;
	}

	public void update(double elapsed) {
		if (alive) {
			position.add(velocity.x * elapsed, velocity.y * elapsed));
			age += elapsed;

			if (age >= life) {
				alive = false;
			}
		}
	}

	public void draw() {
		if (alive) {

			this.sonarPrototype005.pushMatrix();
			this.sonarPrototype005.translate(pos.x, pos.y);
			this.sonarPrototype005.rotate(dir);
			this.sonarPrototype005.stroke(255);
			this.sonarPrototype005.noFill();
			this.sonarPrototype005.beginShape();
			this.sonarPrototype005.vertex(-1, -2);
			this.sonarPrototype005.vertex(1, -2);
			this.sonarPrototype005.vertex(0, 2);
			this.sonarPrototype005.vertex(-1, -2);
			this.sonarPrototype005.endShape();
			this.sonarPrototype005.popMatrix();
		}
	}

	@Override
	public CollisionEnum getCollisionClass() {
		return CollisionEnum.BULLET;
	}

	@Override
	public void clearEvents() {
		// TODO Auto-generated method stub

	}

}