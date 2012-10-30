package sonar;

import java.util.ArrayList;
import engine.Utility;
import processing.core.*;

public class LevelState implements State {
	private SonarPrototype005 myApplet;
	
	static final int ENEMY_COUNT = 30;
	static final int MCGUFFIN_COUNT = 5;

	Utility utility;
	Ship myShip;
	HUD myHUD;
	ArrayList<Enemy> myEnemies;
	ArrayList<McGuffin> myMcGuffins;
	int enemiesKilled;
	int mcGuffinsGrabbed;
	
	public LevelState(SonarPrototype005 pParent){
		myApplet=pParent;
	}
	

	@Override
	public  void setup(){
			setupObjects();	
	}
	
	private void setupObjects() {
		enemiesKilled = 0;
		mcGuffinsGrabbed=0;
		utility = new Utility(myApplet);
		myShip = new Ship(myApplet,this);
		myShip.setup();
		myHUD = new HUD(myApplet,this);
		myEnemies = new ArrayList<Enemy>();
		myMcGuffins= new ArrayList<McGuffin>();
		for (int i = 0; i < ENEMY_COUNT; i++) {
			myEnemies.add(new Enemy(myApplet,this));
		}
		for (int i = 0; i < MCGUFFIN_COUNT; i++) {
			myMcGuffins.add(new McGuffin(myApplet,this));
		}
		
	}
	
	@Override
	public  void update(){
		myShip.update(myApplet.keys);
		myHUD.update(myShip);// this is nice
		
	}

	@Override
	public  void draw(){
		myApplet.tint(255, 255);

		myApplet.blendMode(PApplet.BLEND);



//estos se pueden cambiar a funciones cuando haya un prototipo del sonar object
		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).update();
		}
		for (int i = 0; i < myMcGuffins.size(); i++) {
			((McGuffin) myMcGuffins.get(i)).update();
		}

		myApplet.fill(0, 64);
		myApplet.noStroke();
		myApplet.rect(-1, -1, myApplet.width + 2, myApplet.height + 2);

		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).draw();
		}
		for (int i = 0; i < myMcGuffins.size(); i++) {
			((McGuffin) myMcGuffins.get(i)).draw();
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


	
}