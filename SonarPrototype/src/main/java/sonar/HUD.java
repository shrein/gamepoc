package sonar;

import processing.core.PFont;

public class HUD {

  /**
	 * 
	 */
	private SonarPrototype005 myApplet;
	private LevelState myState;

PFont infoFont;

  float maxCircle;
  float circleTiming;

  float maxBullet;
  float bulletTiming;

  public HUD(SonarPrototype005 pApplet,LevelState pState) {
	myState=pState;
    myApplet = pApplet;
	infoFont=myApplet.loadFont("SansSerif-10.vlw");
    myApplet.textFont(infoFont);
    myState.enemiesKilled=0;
  }

  public void update(Ship pShip) {
    maxCircle=pShip.circleDelay;
    circleTiming=pShip.circleElapsed;

    maxBullet=pShip.bulletDelay;
    bulletTiming=pShip.bulletElapsed;
  }

  public void draw() {
    myApplet.pushStyle();
    myApplet.stroke(255);
    myApplet.noStroke();
    myApplet.fill(128);

    myApplet.pushMatrix();
    myApplet.translate(5, 5);
    myApplet.rect(0, 0, 50, 2);
    myApplet.translate(0, 4);
    myApplet.rect(0, 0, 50, 2);
    myApplet.popMatrix();

    myApplet.pushMatrix();
    myApplet.fill(255);
    myApplet.translate(5, 5);
    myApplet.rect(0, 0, SonarPrototype005.constrain(50+(maxCircle-circleTiming)/-maxCircle*50.0f, 0, 50), 2);
    myApplet.translate(0, 4);
    myApplet.rect(0, 0, SonarPrototype005.constrain(50+(maxBullet-bulletTiming)/-maxBullet*50.0f, 0, 50), 2);
    myApplet.popMatrix();


    myApplet.text(SonarPrototype005.nf(myApplet.frameRate, 2, 2)+"fps", 60, 12);
    myApplet.text(SonarPrototype005.nf(myState.enemiesKilled, 2, 0)+"  enemies killed", 5, 24);
    myApplet.text("sonar (working title) POC v005. x,c + arrows or wasd + mouse clicks. backspace or enter: reset. m: mute", 5, myApplet.height-5);
    myApplet.popStyle();
  }
}