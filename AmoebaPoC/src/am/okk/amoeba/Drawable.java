package am.okk.amoeba;

import processing.core.PGraphics;

public interface Drawable extends Comparable<Drawable>{
	
	void setGraphics(PGraphics pg);
	
	PGraphics getPGraphics();
	
	void setup();
	
	void update();
	
	void draw();
	
	int getDepth();
	
	void setDepth(int depth);
}
