package sonar;

import processing.core.PFont;

public class HUD {

  /**
	 * 
	 */
	private SonarPrototype005 sonarPrototype005;

PFont infoFont;

  float maxCircle;
  float circleTiming;

  float maxBullet;
  float bulletTiming;

  public HUD(SonarPrototype005 sonarPrototype005) {
    this.sonarPrototype005 = sonarPrototype005;
	infoFont=this.sonarPrototype005.loadFont("SansSerif-10.vlw");
    this.sonarPrototype005.textFont(infoFont);
    this.sonarPrototype005.enemiesKilled=0;
  }

  public void update(Ship pShip) {
    maxCircle=pShip.circleDelay;
    circleTiming=pShip.circleElapsed;

    maxBullet=pShip.bulletDelay;
    bulletTiming=pShip.bulletElapsed;
  }

  public void draw() {
    this.sonarPrototype005.pushStyle();
    this.sonarPrototype005.stroke(255);
    this.sonarPrototype005.noStroke();
    this.sonarPrototype005.fill(128);

    this.sonarPrototype005.pushMatrix();
    this.sonarPrototype005.translate(5, 5);
    this.sonarPrototype005.rect(0, 0, 50, 2);
    this.sonarPrototype005.translate(0, 4);
    this.sonarPrototype005.rect(0, 0, 50, 2);
    this.sonarPrototype005.popMatrix();

    this.sonarPrototype005.pushMatrix();
    this.sonarPrototype005.fill(255);
    this.sonarPrototype005.translate(5, 5);
    this.sonarPrototype005.rect(0, 0, SonarPrototype005.constrain(50+(maxCircle-circleTiming)/-maxCircle*50.0f, 0, 50), 2);
    this.sonarPrototype005.translate(0, 4);
    this.sonarPrototype005.rect(0, 0, SonarPrototype005.constrain(50+(maxBullet-bulletTiming)/-maxBullet*50.0f, 0, 50), 2);
    this.sonarPrototype005.popMatrix();


    this.sonarPrototype005.text(SonarPrototype005.nf(this.sonarPrototype005.frameRate, 2, 2)+"fps", 60, 12);
        this.sonarPrototype005.text(SonarPrototype005.nf(this.sonarPrototype005.enemiesKilled, 2, 0)+"  enemies killed", 5, 24);
    this.sonarPrototype005.text("sonar (working title) POC v005. x,c + arrows or wasd + mouse clicks. backspace or enter: reset. m: mute", 5, this.sonarPrototype005.height-5);
    this.sonarPrototype005.popStyle();
  }
}