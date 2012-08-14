package sonar;


import processing.core.PVector;

public class BBox {

  PVector pos1, pos2, pos3, pos4;
  int mode;

  public BBox(int pMode) {
    pos1=new PVector();
    pos2=new PVector();
    pos3=new PVector();
    pos4=new PVector();

    mode=pMode;
  }

  public void update(PVector pPos1, PVector pPos2) {
    pos1=pPos1;
    pos2=new PVector(pPos1.x, pPos2.y);
    pos3=new PVector(pPos2.x, pPos1.y);
    pos4=pPos2;
  }

  public void update(PVector pPos, float pSize) {
    if (mode==SonarPrototype005.CENTER) {
      pos1.set(pPos.x-pSize/2, pPos.y-pSize/2, 0);
      pos4.set(pPos.x+pSize/2, pPos.y+pSize/2, 0);
    }
    if (mode==SonarPrototype005.CORNER) {
      pos1.set(pPos.x, pPos.y, 0);
      pos4.set(pPos.x+pSize, pPos.y+pSize, 0);
    }
    update(pos1, pos4);
    /*
    noFill();
     stroke(128);
     rectMode(CORNERS);
     rect(pos1.x, pos1.y, pos4.x, pos4.y);
     rect(pos2.x, pos2.y, pos3.x, pos3.y);*/
  }


  public boolean collisionTest(BBox pBBox) {

    boolean xIntersection1=dCollision(pBBox.pos1.x, pos1.x, pos4.x);
    boolean yIntersection1=dCollision(pBBox.pos1.y, pos1.y, pos4.y);
    boolean xIntersection2=dCollision(pBBox.pos2.x, pos1.x, pos4.x);
    boolean yIntersection2=dCollision(pBBox.pos2.y, pos1.y, pos4.y);
    boolean xIntersection3=dCollision(pBBox.pos3.x, pos1.x, pos4.x);
    boolean yIntersection3=dCollision(pBBox.pos3.y, pos1.y, pos4.y);
    boolean xIntersection4=dCollision(pBBox.pos4.x, pos1.x, pos4.x);
    boolean yIntersection4=dCollision(pBBox.pos4.y, pos1.y, pos4.y);

    if (xIntersection1 && yIntersection1||
      xIntersection2 && yIntersection2||
      xIntersection3 && yIntersection3||
      xIntersection4 && yIntersection4) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean dCollision(float testX, float x1, float x2) {
    if (testX > x1 && testX < x2) {
      return true;
    } 
    else if (testX > x2 && testX < x1) {
      return true;
    } 
    else { 
      return false;
    }
  }
}