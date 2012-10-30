package sonar;

import processing.core.PVector;

public class Bullet {

  /**
	 * 
	 */
	private SonarPrototype005 myApplet;
PVector pos, vel;
  float dir; 
  float speed=480;
  BBox boundingBox;

  boolean alive;
  float life=2;
  float age;

  public Bullet(SonarPrototype005 pApplet) {

    myApplet = pApplet;
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
      pos.add(new PVector(vel.x*myApplet.elapsed, -vel.y*myApplet.elapsed));
      age+=myApplet.elapsed;

      boundingBox.update(pos, 5);

      if (age>=life) {
        alive=false;
      }
    }
  }


  public void draw() {
    if (alive) {

      myApplet.pushMatrix();
      myApplet.translate(pos.x, pos.y);
      myApplet.rotate(dir);
      myApplet.stroke(255);
      myApplet.noFill();
      myApplet.beginShape();
      myApplet.vertex(-1, -2);
      myApplet.vertex(1, -2);
      myApplet.vertex(0, 2);
      myApplet.vertex(-1, -2);
      myApplet.endShape();
      myApplet.popMatrix();
    }
  }
}