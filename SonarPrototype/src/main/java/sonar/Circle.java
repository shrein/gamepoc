package sonar;

import processing.core.PVector;

public class Circle {
  /**
	 * 
	 */
	private SonarPrototype005 sonarPrototype005;
PVector pos;
  float scale;
  float scaleVel;
  boolean alive;
  float scaler;

  public Circle(SonarPrototype005 sonarPrototype005) {
    this.sonarPrototype005 = sonarPrototype005;
	pos=new PVector(0, 0);
    scale=0;
    alive=false;
    scaler=512;
  }

  public void spawn(PVector pPos) {
    pos.set(pPos);
    alive=true;
    scale=0;
    scaleVel=scaler;
    this.sonarPrototype005.alphaBuffer.beginDraw();
    //alphaBuffer.background(0);
    this.sonarPrototype005.alphaBuffer.endDraw();
  }

  public void update() {
    if (alive) {
      scaleVel*=0.975f;
      //scaleVel*=1;
      scale+=scaleVel*this.sonarPrototype005.elapsed;
      if (scaleVel<=64) {
        alive=false;
      }
    }
  }

  public void draw() {
      if (alive) {
      for (int i=0;i<=5;i++) {
        this.sonarPrototype005.stroke(scaleVel/8, scaleVel/4, scaleVel/2, 255-(i*50));
        this.sonarPrototype005.ellipseMode(SonarPrototype005.CENTER);
        this.sonarPrototype005.ellipse(pos.x, pos.y, scale-i*8, scale-i*8);
      }
    }
  }

  public void drawBuffer() {
    if (alive) {
        for (int i=0;i<=5;i++) {
        	this.sonarPrototype005.alphaBuffer.beginDraw();
        	this.sonarPrototype005.alphaBuffer.stroke(255);
        	this.sonarPrototype005.alphaBuffer.strokeWeight(8);
        	this.sonarPrototype005.alphaBuffer.noFill();
        	this.sonarPrototype005.alphaBuffer.ellipseMode(CENTER);
        	this.sonarPrototype005.alphaBuffer.ellipse(pos.x, pos.y, scale-i*16, scale-i*16);
        	this.sonarPrototype005.alphaBuffer.endDraw();
          }    }
  }
  
  public void setScale(float pScaleVel){
    scaler=pScaleVel;
  }
}