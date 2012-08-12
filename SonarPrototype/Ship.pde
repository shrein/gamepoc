class Ship {

  ArrayList myCircles;
  ArrayList myBullets;

  BBox boundingBox;

  PVector pos, vel;
  float dir;
  float speed=16;//speed multiplier
  float drag=0.9;
  int maxBullets=20;
  int maxCircles=5;
  float bulletDelay=0.5;
  float bulletElapsed;
  float circleDelay=2;
  float circleElapsed;

  boolean alive;

  Ship() {

    alive=true;

    boundingBox=new BBox(CENTER);
    myBullets=new ArrayList();

    for (int i=0; i<maxBullets;i++) {
      myBullets.add(new Bullet());
    }

    myCircles=new ArrayList();
    for (int i=0; i<maxCircles;i++) {
      myCircles.add(new Circle());
    }

    bulletElapsed=bulletDelay;
    circleElapsed=circleDelay;

    pos=new PVector(width/2, height/2);
    println(pos);
    vel=new PVector(0, 0); //remember not to create new objects in runtime
  }

  void update(boolean[] pKeys) {
    
    for (int i=0; i<myCircles.size();i++) { 
      ((Circle)myCircles.get(i)).update();
      ((Circle)myCircles.get(i)).drawBuffer();
    }

    if (alive) {

      controlCall(pKeys);

      for (int i=0; i<myBullets.size();i++) { 
        ((Bullet)myBullets.get(i)).update();
      }
      bulletElapsed+=elapsed;


      circleElapsed+=elapsed;

      vel.mult(drag);
      pos.add(new PVector(vel.x*elapsed, vel.y*elapsed));
      pos=Utility.loopSpace(pos);
      if (vel.x!=0||vel.y!=0) {
        dir=atan2(vel.x, -vel.y);
      }
      boundingBox.update(pos, 15);
    }
  }

  void draw() {

    for (int i=0; i<myCircles.size();i++) { 
      ((Circle)myCircles.get(i)).draw();
    }

    if (alive) {


      for (int i=0; i<myBullets.size();i++) { 
        ((Bullet)myBullets.get(i)).draw();
      }

      pushMatrix();
      translate(pos.x, pos.y);
      rotate(dir);
      stroke(255);
      noFill();
      beginShape();
      vertex(-3, -6);
      vertex(3, -6);
      vertex(0, 6);
      vertex(-3, -6);
      endShape();
      popMatrix();
    }
  }




  void controlCall(boolean[] pKeys) {

    if (pKeys[_UP]) {
      vel.y-=speed;
    }


    if (pKeys[_DOWN]) {
      vel.y+=speed;
    }


    if (pKeys[_LEFT]) {
      vel.x-=speed;
    }


    if (pKeys[_RIGHT]) {
      vel.x+=speed;
    }


    if (pKeys[_C]||pKeys[_CLICK]) {
      if (bulletElapsed>bulletDelay) {
        bulletElapsed=0;
        for (int i=0; i<myBullets.size();i++) { 
          Bullet currentBullet=(Bullet)myBullets.get(i);
          if (!currentBullet.alive) {
            if (pKeys[_CLICK])currentBullet.spawn(pos, atan2(mouseX-pos.x, -(mouseY-pos.y)));
            if (pKeys[_C])currentBullet.spawn(pos, dir);
            bulletSound.trigger();
            break;
          }
        }
      }
    }


    if (pKeys[_X]) {
      if (circleElapsed>circleDelay) {
        circleElapsed=0;
        for (int i=0; i<myCircles.size();i++) { 
          Circle currentCircle=(Circle)myCircles.get(i);
          if (!currentCircle.alive) {
            currentCircle.spawn(pos);
            circleSound.trigger();
            break;
          }
        }
      }
    }



    if (pKeys[_Z]) {
    }
  }
}

