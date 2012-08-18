package sonar;

import processing.core.PVector;

public class Circle {
  /**
	 * 
	 */
	private SonarPrototype005 myPApplet;
PVector pos;
  float scale;
  float scaleVel;
  boolean alive;
  float scaler;

  public Circle(SonarPrototype005 pPApplet) {
    myPApplet = pPApplet;
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
    myPApplet.alphaBuffer.beginDraw();
    //alphaBuffer.background(0);
    myPApplet.alphaBuffer.endDraw();
  }

  public void update() {
    if (alive) {
      scaleVel*=0.975f;
      //scaleVel*=1;
      scale+=scaleVel*myPApplet.elapsed;
      if (scaleVel<=64) {
        alive=false;
      }
    }
  }

  public void draw() {
      if (alive) {
      for (int i=0;i<=5;i++) {
        myPApplet.stroke(scaleVel/8, scaleVel/4, scaleVel/2, 255-(i*50));
        myPApplet.ellipseMode(SonarPrototype005.CENTER);
        myPApplet.ellipse(pos.x, pos.y, scale-i*8, scale-i*8);
      }
    }
  }

  public void drawBuffer() {
    if (alive) {
        for (int i=0;i<=5;i++) {
        	myPApplet.alphaBuffer.beginDraw();
        	myPApplet.alphaBuffer.stroke(255);
        	myPApplet.alphaBuffer.strokeWeight(8);
        	myPApplet.alphaBuffer.noFill();
        	myPApplet.alphaBuffer.ellipseMode(SonarPrototype005.CENTER);
        	myPApplet.alphaBuffer.ellipse(pos.x, pos.y, scale-i*16, scale-i*16);
        	myPApplet.alphaBuffer.endDraw();
          }    }
  }
  
  public void setScale(float pScaleVel){
    scaler=pScaleVel;
  }
}