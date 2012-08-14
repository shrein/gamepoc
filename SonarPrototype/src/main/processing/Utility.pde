class Utility {
  Utility() {
  }

  PVector loopSpace(PVector pPos) {
    float x, y;
    
    x=pPos.x;
    y=pPos.y;
    
    if (pPos.x>width) {
      x=0;
    }
    if (pPos.x<0) {
      x=width;
    }
    if (pPos.y>height) {
      y=0;
    }
    if (pPos.y<0) {
      y=height;
    }
    return new PVector(x, y);
  }
}

