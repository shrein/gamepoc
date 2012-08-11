package am.okk.amoeba;

import processing.core.PGraphics;

public abstract class AbstractDrawable implements Drawable {

	PGraphics pg = null;
	int depth;
	
	@Override
	public int compareTo(Drawable o) {
		return this.getDepth()-o.getDepth();
	}

	@Override
	public void setGraphics(PGraphics pg) {
		this.pg = pg;
	}

	@Override
	public PGraphics getPGraphics() {
		return this.pg;
	}

	@Override
	public int getDepth() {
		return this.depth;
	}
	
	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

}
