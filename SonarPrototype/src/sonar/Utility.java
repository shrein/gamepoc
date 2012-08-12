package sonar;

import processing.core.PVector;

class Utility {
  /**
	 * 
	 */
	private SonarPrototype005 sonarPrototype005;

Utility(SonarPrototype005 sonarPrototype005) {
	this.sonarPrototype005 = sonarPrototype005;
  }

  public PVector loopSpace(PVector pPos) {
    float x, y;
    
    x=pPos.x;
    y=pPos.y;
    
    if (pPos.x>this.sonarPrototype005.width) {
      x=0;
    }
    if (pPos.x<0) {
      x=this.sonarPrototype005.width;
    }
    if (pPos.y>this.sonarPrototype005.height) {
      y=0;
    }
    if (pPos.y<0) {
      y=this.sonarPrototype005.height;
    }
    return new PVector(x, y);
  }
}