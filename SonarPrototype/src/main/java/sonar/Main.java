package sonar;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PGraphics;
import sonar.Enemy;
import sonar.HUD;
import sonar.Ship;
import sonar.SonarPrototype005;
import sonar.Utility;
import engine.DepthPainter;
import engine.Environment;

public class Main extends PApplet {

	private static final long serialVersionUID = -1828447365732253316L;
	private static final int ENEMY_COUNT = 30;
	private static final int SCREEN_HEIGHT = 400;
	private static final int SCREEN_WIDTH = 640;
	
	private final DepthPainter painter = null;
	private final Environment e = null;

	private Utility utility = null;
	private Ship myShip = null;
	private HUD myHUD = null;
	private ArrayList<Enemy> myEnemies = null;
	private PGraphics alphaBuffer = null;

	int enemiesKilled;
	// bbox constants
	int pMillis;
	public float elapsed;

	@Override
	public void setup() {
		setupGraphics();
		setupObjects();
//		setupSound();
		e.setup();
	}
	
	private void setupGraphics() {
		size(SCREEN_WIDTH, SCREEN_HEIGHT, P3D);
		println("Rendering with: " + g.getClass().toString());

		painter = new DepthPainter(this);
		// hint(DISABLE_DEPTH_TEST);
		// hint(ENABLE_NATIVE_FONTS);
		// noSmooth();
		// smooth();
		alphaBuffer = createGraphics(width, height, P3D);
		alphaBuffer.beginDraw();
		// alphaBuffer.background(0);
		alphaBuffer.endDraw();

		frameRate(60);
		fill(0);
		noStroke();
		rect(-1, -1, width + 2, height + 2);
	}
	
	private void setupObjects() {
		e = new Environment(this);
		pMillis = 0;
		enemiesKilled = 0;
		utility = new Utility(this);
		myShip = new Ship(this);
		myHUD = new HUD(this);
		myEnemies = new ArrayList<Enemy>();
		for (int i = 0; i < ENEMY_COUNT; i++) {
			myEnemies.add(new Enemy(this));
		}
	}
		

	@Override
	public void draw() {
		// amoeba.setTarget(mouseX, mouseY);
		painter.update();
		painter.draw();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		amoeba.keyPressed(c);
		// dispatcher.keyPressed(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		char c = arg0.getKeyChar();
		amoeba.keyReleased(c);
	}

}
