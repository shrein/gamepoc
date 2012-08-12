class Circle {
  PVector pos;
  float scale;
  float scaleVel;
  boolean alive;
  float scaler;

  Circle() {
    pos=new PVector(0, 0);
    scale=0;
    alive=false;
    scaler=512;
  }

  void spawn(PVector pPos) {
    pos.set(pPos);
    alive=true;
    scale=0;
    scaleVel=scaler;
    alphaBuffer.beginDraw();
    //alphaBuffer.background(0);
    alphaBuffer.endDraw();
  }

  void update() {
    if (alive) {
      scaleVel*=0.975;
      //scaleVel*=1;
      scale+=scaleVel*elapsed;
      if (scaleVel<=64) {
        alive=false;
      }
    }
  }

  void draw() {
      if (alive) {
      for (int i=0;i<=5;i++) {
        stroke(scaleVel/8, scaleVel/4, scaleVel/2, 255-(i*50));
        //stroke(scaleVel/8, (scaleVel+scale/2)/6, scaleVel/2, 255);
        ellipseMode(CENTER);
        ellipse(pos.x, pos.y, scale-i*8, scale-i*8);
          //ellipse(pos.x, pos.y, scale, scale);
      }
    }
  }

  void drawBuffer() {
    if (alive) {

      alphaBuffer.beginDraw();
      alphaBuffer.stroke(255);
      alphaBuffer.strokeWeight(8);
      alphaBuffer.noFill();
      alphaBuffer.ellipseMode(CENTER);
      alphaBuffer.ellipse(pos.x, pos.y, scale, scale);
      //alphaBuffer.ellipse(pos.x, pos.y, scale, scale);
      alphaBuffer.endDraw();
    }
  }
  
  void setScale(float pScaleVel){
    scaler=pScaleVel;
  }
}

