package sonar;
import processing.core.PFont;

class TitleState implements State {
	private SonarPrototype005 myApplet;
	private PFont infoFont;
	
	public TitleState(SonarPrototype005 pParent){
		this.myApplet=pParent;
		
	}
	
	@Override
	public  void setup(){
		infoFont=myApplet.loadFont("SansSerif-10.vlw");
	    myApplet.textFont(infoFont);

	}
	@Override
	public  void update(){
		if(myApplet.keyPressed){
			myApplet.currentState=myApplet.myLevelState;
			//myApplet.changeState(myApplet.myLevelState);
		}

	}
	@Override
	public  void draw(){
		   myApplet.background(0);
		   myApplet.fill(255);
		   myApplet.text("sonar (working title) POC v007 \npress any key to play", myApplet.width/4, myApplet.height*2/3);	   
		    myApplet.text("x,c + arrows. backspace or enter to retry. m: mute", myApplet.width/4, myApplet.height*2/3+40);
	}

	
}