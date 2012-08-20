package sonar;

import engine.Model;
import engine.RenderManager;
import engine.SoundManager;
import engine.View;

public class SonarView extends View {

	@Override
	public void draw(RenderManager r, Model m) {
		// TODO Auto-generated method stub

	}
	
//	public void draw() {
//		if (alive) {
//			for (int i = 0; i <= 5; i++) {
//				this.sonarPrototype005.stroke(scaleSpeed / 8, scaleSpeed / 4,
//						scaleSpeed / 2, 255 - (i * 50));
//				this.sonarPrototype005.ellipseMode(sonarPrototype005.CENTER);
//				this.sonarPrototype005.ellipse(pos.x, pos.y, scale - i * 8,
//						scale - i * 8);
//			}
//		}
//	}
	
//	public void drawBuffer() {
//		if (alive) {
//			for (int i = 0; i <= 5; i++) {
//				this.sonarPrototype005.alphaBuffer.beginDraw();
//				this.sonarPrototype005.alphaBuffer.stroke(255);
//				this.sonarPrototype005.alphaBuffer.strokeWeight(8);
//				this.sonarPrototype005.alphaBuffer.noFill();
//				this.sonarPrototype005.alphaBuffer
//						.ellipseMode(sonarPrototype005.CENTER);
//				this.sonarPrototype005.alphaBuffer.ellipse(pos.x, pos.y, scale
//						- i * 16, scale - i * 16);
//				this.sonarPrototype005.alphaBuffer.endDraw();
//			}
//		}
//	}


	@Override
	public void sing(SoundManager s, Model m) {
		// TODO Auto-generated method stub

	}

}
