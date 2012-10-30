package sonar;

import processing.core.PVector;
import engine.Environment;
import engine.SoundEnum;


public class McGuffin {

	private SonarPrototype005 myApplet;
	private LevelState myState;
	private Environment e;

	BBox boundingBox;

	Circle myCircle;
	
	PVector pos;
	
	float timer;
	float period;

	boolean alive;

	public McGuffin(SonarPrototype005 pApplet, LevelState pState) {
		
		myApplet = pApplet;
		myState=pState;
		this.e = pApplet.getE();
		
		boundingBox = new BBox(SonarPrototype005.CENTER);
		
		myCircle = new Circle(myApplet);
		myCircle.setScale(256);
		
		pos = new PVector(
				myApplet.random(myApplet.width),
				myApplet.random(myApplet.height));
		alive = true;

		boundingBox.update(pos, 20);

		timer=myApplet.random(2);

		period=2;
	}

	public void update() {

		if (alive) {
			timer+=myApplet.elapsed;
			SonarPrototype005.println(timer);
			if(timer>=period){
				timer=timer-period;
				
				myCircle.spawn(pos);
			}
			myCircle.update();
			myCircle.drawBuffer();

//tengo que revisar como va a funcionar bien la logica de colisiones 
			//porque esto es una chambonada		
			if (boundingBox
					.collisionTest(myState.myShip.boundingBox)) {
				alive = false;
				myState.myShip.vel.set(0, 0, 0);
				for (int i = 0; i < myState.myShip.myCircles.size(); i++) {
					Circle currentCircle = (Circle) myState.myShip.myCircles
							.get(i);
					if (!currentCircle.alive) {
						//currentCircle.setScale(1024);/// uuum interesting
						break;
					}
				}
				e.play(SoundEnum.MCGUFFIN);
				myState.enemiesKilled++;
				myState.mcGuffinsGrabbed++;
			}
		}
	}

	public void draw() {
		myCircle.draw();

		if (alive) {
			myApplet.pushMatrix();
			myApplet.translate(pos.x, pos.y);
			myApplet.stroke(255);
			myApplet.noFill();
			myApplet.ellipse(0,0,8,8);
			myApplet.popMatrix();
		}
	}
}