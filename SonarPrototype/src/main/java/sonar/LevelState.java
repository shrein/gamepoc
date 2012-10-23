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
		this.myApplet=pParent;
	}
	

	@Override
	public  void setup(){

			setupObjects();	
	}
	
	private void setupObjects() {
		enemiesKilled = 0;
		utility = new Utility(this.myApplet);
		myShip = new Ship(this.myApplet,this);
		myHUD = new HUD(this.myApplet,this);
		myEnemies = new ArrayList<Enemy>();
		for (int i = 0; i < ENEMY_COUNT; i++) {
			myEnemies.add(new Enemy(this.myApplet,this));
		}
	}


	@Override
	public  void draw(){
		this.myApplet.tint(255, 255);

		this.myApplet.blendMode(PApplet.BLEND);
		this.myApplet.elapsed = PApplet.parseFloat(this.myApplet.millis() - this.myApplet.pMillis) / 1000;
		this.myApplet.pMillis = this.myApplet.millis();

		myShip.update(this.myApplet.keys);
		myHUD.update(myShip);

		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).update();
		}

		this.myApplet.fill(0, 64);
		this.myApplet.noStroke();
		this.myApplet.rect(-1, -1, this.myApplet.width + 2, this.myApplet.height + 2);

		for (int i = 0; i < myEnemies.size(); i++) {
			((Enemy) myEnemies.get(i)).draw();
		}

		// blendMode(BLEND);
		this.myApplet.alphaBuffer.beginDraw();
		this.myApplet.alphaBuffer.fill(0, 16);
		this.myApplet.alphaBuffer.noStroke();
		this.myApplet.alphaBuffer.rect(-1, -1, this.myApplet.width + 2, this.myApplet.height + 2);
		// alphaBuffer.background(0);
		this.myApplet.alphaBuffer.endDraw();

		this.myApplet.noStroke();
		// blend(alphaBuffer, 0, 0, width, height, 0, 0, width, height,
		// MULTIPLY);

		this.myApplet.blendMode(PApplet.DARKEST);
		this.myApplet.image(this.myApplet.alphaBuffer, 0, 0);

		this.myApplet.blendMode(PApplet.BLEND);
		myShip.draw();
		myHUD.draw();
	}
	@Override
	public  void update(){
		
		
	}

	
}