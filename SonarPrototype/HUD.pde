class HUD {

  PFont infoFont;

  float maxCircle;
  float circleTiming;

  float maxBullet;
  float bulletTiming;

  HUD() {
    infoFont=loadFont("SansSerif-10.vlw");
    textFont(infoFont);
    enemiesKilled=0;
  }

  void update(Ship pShip) {
    maxCircle=pShip.circleDelay;
    circleTiming=pShip.circleElapsed;

    maxBullet=pShip.bulletDelay;
    bulletTiming=pShip.bulletElapsed;
  }

  void draw() {
    pushStyle();
    stroke(255);
    noStroke();
    fill(128);

    pushMatrix();
    translate(5, 5);
    rect(0, 0, 50, 2);
    translate(0, 4);
    rect(0, 0, 50, 2);
    popMatrix();

    pushMatrix();
    fill(255);
    translate(5, 5);
    rect(0, 0, constrain(50+(maxCircle-circleTiming)/-maxCircle*50.0, 0, 50), 2);
    translate(0, 4);
    rect(0, 0, constrain(50+(maxBullet-bulletTiming)/-maxBullet*50.0, 0, 50), 2);
    popMatrix();


    text(nf(frameRate, 2, 2)+"fps", 60, 12);
        text(nf(enemiesKilled, 2, 0)+"  enemies killed", 5, 24);
    text("sonar (working title) POC v001. x,c + arrows or wasd + mouse clicks. backspace or enter to reset.", 5, height-5);
    popStyle();
  }
}

