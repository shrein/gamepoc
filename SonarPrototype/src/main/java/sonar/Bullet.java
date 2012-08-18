package sonar;

import processing.core.PVector;

public class Bullet {

  /**
	 * 
	 */
	private SonarPrototype005 myPApplet;
PVector pos, vel;
  float dir; 
  float speed=480;
  BBox boundingBox;

  boolean alive;
  float life=2;
  float age;

  public Bullet(SonarPrototype005 sonarPrototype005) {

    myPApplet = sonarPrototype005;
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
      pos.add(new PVector(vel.x*myPApplet.elapsed, -vel.y*myPApplet.elapsed));
      age+=myPApplet.elapsed;

      boundingBox.update(pos, 5);

      if (age>=life) {
        alive=false;
      }
    }
  }


  public void draw() {
    if (alive) {

      myPApplet.pushMatrix();
      myPApplet.translate(pos.x, pos.y);
      myPApplet.rotate(dir);
      myPApplet.stroke(255);
      myPApplet.noFill();
      myPApplet.beginShape();
      myPApplet.vertex(-1, -2);
      myPApplet.vertex(1, -2);
      myPApplet.vertex(0, 2);
      myPApplet.vertex(-1, -2);
      myPApplet.endShape();
      myPApplet.popMatrix();
    }
  }
}