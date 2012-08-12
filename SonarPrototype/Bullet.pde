class Bullet {

  PVector pos, vel;
  float dir; 
  float speed=480;
  BBox boundingBox;

  boolean alive;
  float life=2;
  float age;

  Bullet() {

    boundingBox=new BBox(CENTER);

   
    pos=new PVector(0, 0);
    vel=new PVector(0, 0);
    alive=false;
  }

  void spawn(PVector pPos, float pDir) {
    pos.set(pPos);
    dir=pDir;
    vel=new PVector(sin(dir), cos(dir));
    vel.mult(speed);
    alive=true;
    age=0;
  }

  void update() {
    if (alive) {
      pos.add(new PVector(vel.x*elapsed, -vel.y*elapsed));
      age+=elapsed;

      boundingBox.update(pos, 5);

      if (age>=life) {
        alive=false;
      }
    }
  }


  void draw() {
    if (alive) {

      pushMatrix();
      translate(pos.x, pos.y);
      rotate(dir);
      stroke(255);
      noFill();
      beginShape();
      vertex(-1, -2);
      vertex(1, -2);
      vertex(0, 2);
      vertex(-1, -2);
      endShape();
      popMatrix();
    }
  }
}

