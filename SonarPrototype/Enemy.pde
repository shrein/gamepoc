class Enemy {

  BBox boundingBox;

  Circle myCircle;

  PVector pos, vel;
  float dir;
  float speed=25;//speed multiplier
  float drag=0.95;
  boolean lucky;


  boolean alive;

  Enemy() {

    boundingBox=new BBox(CENTER);
    myCircle=new Circle();

    pos=new PVector(random(width), random(height));
    vel=new PVector(0, 0); //remember not to create new objects in runtime
    alive=true;
    lucky=false;
  }

  void update() {
      myCircle.update();
      myCircle.drawBuffer();
    
    if (alive) {
      if (random(1)>0.95) {
        vel.y+=(4*speed)-random(speed*8);
      }
      if (random(1)>0.95) {
        vel.x+=(4*speed)-random(speed*8);
      }

      vel.mult(drag);
      pos.add(new PVector(vel.x*elapsed, vel.y*elapsed));
      pos=Utility.loopSpace(pos);

      dir=atan2(vel.x, -vel.y);

      boundingBox.update(pos, 20);

      //ESTO DEBERIA ESTAR EN LA NAVE, NO EN LOS ENEMIGOS ...
      if (boundingBox.collisionTest(myShip.boundingBox)) {
        alive=false;
        myShip.vel.set(0, 0, 0);
        myShip.alive=false;
        for (int i=0; i<myShip.myCircles.size();i++) { 
          Circle currentCircle=(Circle)myShip.myCircles.get(i);
          if (!currentCircle.alive) {
            currentCircle.setScale(1024);
            currentCircle.spawn(pos);
            circleSound.trigger();
            bulletSound.trigger();
            break;
          }
        }
        enemySound.trigger();
      }

      for (int i=0; i<myShip.myBullets.size();i++) { 
        Bullet tempBullet=((Bullet)myShip.myBullets.get(i));
        if (tempBullet.alive) {
          if (boundingBox.collisionTest(tempBullet.boundingBox)) {
            /////////////daaamnnn//////////////////

            if (random(1)>0.9) {
              lucky=true;
            }
            else {
              lucky=false;
            }

            if (!lucky) {
              myCircle.setScale(128);
            }

            alive=false;
            tempBullet.alive=false;
            myShip.vel.set(0, 0, 0);
            enemySound.trigger();

            myCircle.spawn(pos);
            enemiesKilled++;
          }
        }
      }
    }
  }


  void draw() {
      myCircle.draw();
    
    if (alive) {
      pushMatrix();
      translate(pos.x, pos.y);
      rotate(dir);
      stroke(255);
      noFill();
      beginShape();
      vertex(-4, -4);
      vertex(-4, 4);
      vertex(4, 4);
      vertex(4, -4);
      vertex(-4, -4);
      endShape();
      popMatrix();
    }
  }
}

