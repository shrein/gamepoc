package sonar;

import processing.core.PFont;

public class HUD {

  /**
	 * 
	 */
	private SonarPrototype005 myPApplet;

PFont infoFont;

  float maxCircle;
  float circleTiming;

  float maxBullet;
  float bulletTiming;

  public HUD(SonarPrototype005 sonarPrototype005) {
    myPApplet = sonarPrototype005;
	infoFont=myPApplet.loadFont("SansSerif-10.vlw");
    myPApplet.textFont(infoFont);
    myPApplet.enemiesKilled=0;
  }

  public void update(Ship pShip) {
    maxCircle=pShip.circleDelay;
    circleTiming=pShip.circleElapsed;

    maxBullet=pShip.bulletDelay;
    bulletTiming=pShip.bulletElapsed;
  }

  public void draw() {
    myPApplet.pushStyle();
    myPApplet.stroke(255);
    myPApplet.noStroke();
    myPApplet.fill(128);

    myPApplet.pushMatrix();
    myPApplet.translate(5, 5);
    myPApplet.rect(0, 0, 50, 2);
    myPApplet.translate(0, 4);
    myPApplet.rect(0, 0, 50, 2);
    myPApplet.popMatrix();

    myPApplet.pushMatrix();
    myPApplet.fill(255);
    myPApplet.translate(5, 5);
    myPApplet.rect(0, 0, SonarPrototype005.constrain(50+(maxCircle-circleTiming)/-maxCircle*50.0f, 0, 50), 2);
    myPApplet.translate(0, 4);
    myPApplet.rect(0, 0, SonarPrototype005.constrain(50+(maxBullet-bulletTiming)/-maxBullet*50.0f, 0, 50), 2);
    myPApplet.popMatrix();


    myPApplet.text(SonarPrototype005.nf(myPApplet.frameRate, 2, 2)+"fps", 60, 12);
        myPApplet.text(SonarPrototype005.nf(myPApplet.enemiesKilled, 2, 0)+"  enemies killed", 5, 24);
    myPApplet.text("sonar (working title) POC v005. x,c + arrows or wasd + mouse clicks. backspace or enter: reset. m: mute", 5, myPApplet.height-5);
    myPApplet.popStyle();
  }
}