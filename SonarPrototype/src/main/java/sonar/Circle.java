package sonar;

import processing.core.PVector;


public class Circle {
  /**
	 * 
	 */
	private SonarPrototype005 myApplet;
	PVector pos;
  float scale;
  float scaleVel;
  boolean alive;
  float scaler;
  int circleColor;

  public Circle(SonarPrototype005 pApplet) {
    myApplet = pApplet;
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
    myApplet.alphaBuffer.beginDraw();
    //alphaBuffer.background(0);
    myApplet.alphaBuffer.endDraw();
  }

  public void update() {
    if (alive) {
      scaleVel*=0.975f;
      //scaleVel*=1;
      scale+=scaleVel*myApplet.elapsed;
      if (scaleVel<=64) {
        alive=false;
      }
    }
  }

  public void draw() {
      if (alive) {
      for (int i=0;i<=5;i++) {
        myApplet.stroke(scaleVel/8, scaleVel/4, scaleVel/2, 255-(i*50));
        //need to change this to make it easy to change the color
        myApplet.ellipseMode(SonarPrototype005.CENTER);
        myApplet.ellipse(pos.x, pos.y, scale-i*8, scale-i*8);
      }
    }
  }

  public void drawBuffer() {
    if (alive) {
        for (int i=0;i<=5;i++) {
        	myApplet.alphaBuffer.beginDraw();
        	myApplet.alphaBuffer.stroke(255);
        	myApplet.alphaBuffer.strokeWeight(8);
        	myApplet.alphaBuffer.noFill();
        	myApplet.alphaBuffer.ellipseMode(SonarPrototype005.CENTER);
        	myApplet.alphaBuffer.ellipse(pos.x, pos.y, scale-i*16, scale-i*16);
        	myApplet.alphaBuffer.endDraw();
          }    }
  }
  
  public void setScale(float pScaleVel){
    scaler=pScaleVel;
  }
  
  
}