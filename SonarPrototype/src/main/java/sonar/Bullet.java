package sonar;

import processing.core.PVector;

public class Bullet {

  /**
	 * 
	 */
	private SonarPrototype005 sonarPrototype005;
PVector pos, vel;
  float dir; 
  float speed=480;
  BBox boundingBox;

  boolean alive;
  float life=2;
  float age;

  public Bullet(SonarPrototype005 sonarPrototype005) {

    this.sonarPrototype005 = sonarPrototype005;
	boundingBox=new BBox(SonarPrototype005.CENTER);

   
    pos=new PVector(0, 0);
    vel=new PVector(0, 0);
    alive=false;
  }

  public void spawn(PVector pPos, float pDir) {
    pos.set(pPos);
    dir=pDir;
    vel=new PVector(SonarPrototype005.sin(dir), SonarPrototype005.cos(dir));
    vel.mult(speed);
    alive=true;
    age=0;
  }

  public void update() {
    if (alive) {
      pos.add(new PVector(vel.x*this.sonarPrototype005.elapsed, -vel.y*this.sonarPrototype005.elapsed));
      age+=this.sonarPrototype005.elapsed;

      boundingBox.update(pos, 5);

      if (age>=life) {
        alive=false;
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
}