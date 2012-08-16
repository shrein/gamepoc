package sonar;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import engine.Environment;

public class SonarPrototype005 extends PApplet {
	private static final long serialVersionUID = -6522220673321568582L;
	
	// TODO: hacer crclrand para ayudar a spawnear
	// TODO: poner efectos de sonido mas decentes


	private final Environment e;

	// enumerador chambon para tener mis constantes de teclado
	final int _X = 0;
	final int _C = 1;
	final int _Z = 2;
	final int _UP = 3;
	final int _DOWN = 4;
	final int _LEFT = 5;
	final int _RIGHT = 6;// constantes de teclado para el enumerador.
	final int _CLICK = 7;// de eventos mas bien porque este es el mouse
	// mwhahahaa

	boolean[] keys = new boolean[8];

	

	

	









	public void draw() {
		tint(255, 255);

		blendMode(BLEND);
		elapsed = PApplet.parseFloat(millis() - pMillis) / 1000;
		pMillis = millis();

		myShip.update(keys);
		myHUD.update(myShip);

		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).update();
		}

		fill(0, 64);
		noStroke();
		rect(-1, -1, width + 2, height + 2);

		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).draw();
		}

		// blendMode(BLEND);
		alphaBuffer.beginDraw();
		alphaBuffer.fill(0, 16);
		alphaBuffer.noStroke();
		alphaBuffer.rect(-1, -1, width + 2, height + 2);
		// alphaBuffer.background(0);
		alphaBuffer.endDraw();

		noStroke();
		// blend(alphaBuffer, 0, 0, width, height, 0, 0, width, height,
		// MULTIPLY);

		blendMode(DARKEST);
		image(alphaBuffer, 0, 0);

		blendMode(BLEND);
		myShip.draw();
		myHUD.draw();
	}

	// ////////////////////////////Close Events////////////////////////////

	public void stop() {
		// always close Minim audio classes when you are done with them
		// bulletSound.close();
		// circleSound.close();
		// minim.stop();

		super.stop();
	}

	// //////////////////////////CONTROLLER EVENTS///////////////////////////

	public void keyPressed() {

		if (key == 'x' || key == 'X') {
			keys[_X] = true;
		}
		if (key == 'c' || key == 'C' || key == ' ') {
			keys[_C] = true;
		}
		if (key == 'z' || key == 'Z') {
			keys[_Z] = true;
		}

		if ((key == CODED && keyCode == UP) || key == 'w' || key == 'W') {
			keys[_UP] = true;
		}

		if ((key == CODED && keyCode == DOWN) || key == 'S' || key == 's') {
			keys[_DOWN] = true;
		}

		if ((key == CODED && keyCode == LEFT) || key == 'A' || key == 'a') {
			keys[_LEFT] = true;
		}

		if ((key == CODED && keyCode == RIGHT) || key == 'D' || key == 'd') {
			keys[_RIGHT] = true;
		}

		if ((key == DELETE || key == BACKSPACE || key == RETURN || key == ENTER)
				|| (key == 'r' || key == 'R')) {
			setupObjects();
		}
		
		if(key == 'm' || key == 'M') {
			if(e.isMuted()) {
				e.unmute();
			} else {
				e.mute();
			}
		}
	}

	public void keyReleased() {
		if (key == 'i' || key == 'I') {
			saveFrame(split(this.toString(), "[")[0] + "-" + day() + "."
					+ month() + "." + year() + "-" + hour() + "." + minute()
					+ "." + second() + ".png");
		}// pretty way to store progress in a logical way?

		if (key == 'x' || key == 'X') {
			keys[_X] = false;
		}
		if (key == 'c' || key == 'C' || key == ' ') {
			keys[_C] = false;
		}
		if (key == 'z' || key == 'Z') {
			keys[_Z] = false;
		}

		if ((key == CODED && keyCode == UP) || key == 'w' || key == 'W') {
			keys[_UP] = false;
		}

		if ((key == CODED && keyCode == DOWN) || key == 'S' || key == 's') {
			keys[_DOWN] = false;
		}

		if ((key == CODED && keyCode == LEFT) || key == 'A' || key == 'a') {
			keys[_LEFT] = false;
		}

		if ((key == CODED && keyCode == RIGHT) || key == 'D' || key == 'd') {
			keys[_RIGHT] = false;
		}
	}

	public void mousePressed() {
		if (mouseButton == LEFT) {
			keys[_CLICK] = true;
		}
		if (mouseButton == RIGHT) {
			keys[_X] = true;
		}
	}

	public void mouseReleased() {
		if (mouseButton == LEFT) {
			keys[_CLICK] = false;
		}
		if (mouseButton == RIGHT) {
			keys[_X] = false;
		}
	}

	// manejo de todas las teclas, cuando se presionan y cuando se sueltan
	// ////////////////////CONTROLLER EVENTS
	// END///////////////////////////////////

	public int sketchWidth() {
		return 640;
	}

	public int sketchHeight() {
		return 400;
	}

	public String sketchRenderer() {
		return P3D;
	}

	
	public Environment getE() {
		return e;
	}

	static public void main(String args[]) {
		PApplet.main(new String[] { "sonar.SonarPrototype005" });
	}
}
