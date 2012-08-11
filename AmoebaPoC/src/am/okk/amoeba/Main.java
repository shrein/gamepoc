package am.okk.amoeba;

import java.awt.event.KeyEvent;

import processing.core.*;

public class Main extends PApplet {

	private static final long serialVersionUID = -1828447365732253316L;
	private DepthPainter painter;
	Amoeba amoeba;

	@Override
	public void setup() {
		size(800, 600, OPENGL);
		println("Rendering with: " + g.getClass().toString());
		painter = new DepthPainter(this);
		amoeba = new Amoeba(0.1f, 200, 200, 10, 10, 0.1f, 0.1f);
		amoeba.setDepth(2);
		painter.addDrawable(amoeba);
		painter.setup();
	}

	@Override
	public void draw() {
//		amoeba.setTarget(mouseX, mouseY);
		painter.update();
		painter.draw();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		amoeba.keyPressed(c);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		amoeba.keyReleased(c);
	}

}
