package sonar;

import java.util.ArrayList;
import engine.Utility;
import processing.core.*;

public class LevelState implements State {
	private SonarPrototype005 myApplet;
	
	private static final int ENEMY_COUNT = 30;

	Utility utility;
	Ship myShip;
	HUD myHUD;
	ArrayList<Enemy> myEnemies;
	int enemiesKilled;
	
	public LevelState(SonarPrototype005 pParent){
		myApplet=pParent;
	}
	

	@Override
	public  void setup(){

			setupObjects();	
	}
	
	private void setupObjects() {
		enemiesKilled = 0;
		utility = new Utility(myApplet);
		myShip = new Ship(myApplet,this);
		myShip.setup();
		myHUD = new HUD(myApplet,this);
		myEnemies = new ArrayList<Enemy>();
		for (int i = 0; i < ENEMY_COUNT; i++) {
			myEnemies.add(new Enemy(myApplet,this));
		}
	}


	@Override
	public  void draw(){
		myApplet.tint(255, 255);

		myApplet.blendMode(PApplet.BLEND);
		myApplet.elapsed = PApplet.parseFloat(myApplet.millis() - myApplet.pMillis) / 1000;
		myApplet.pMillis = myApplet.millis();

		myShip.update(myApplet.keys);
		myHUD.update(myShip);

		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).update();
		}

		myApplet.fill(0, 64);
		myApplet.noStroke();
		myApplet.rect(-1, -1, myApplet.width + 2, myApplet.height + 2);

		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).draw();
		}

		// blendMode(BLEND);
		myApplet.alphaBuffer.beginDraw();
		myApplet.alphaBuffer.fill(0, 16);
		myApplet.alphaBuffer.noStroke();
		myApplet.alphaBuffer.rect(-1, -1, myApplet.width + 2, myApplet.height + 2);
		// alphaBuffer.background(0);
		myApplet.alphaBuffer.endDraw();

		myApplet.noStroke();
		// blend(alphaBuffer, 0, 0, width, height, 0, 0, width, height,
		// MULTIPLY);

		myApplet.blendMode(PApplet.DARKEST);
		myApplet.image(myApplet.alphaBuffer, 0, 0);

		myApplet.blendMode(PApplet.BLEND);
		myShip.draw();
		myHUD.draw();
	}
	@Override
	public  void update(){
		
		
	}

	
}