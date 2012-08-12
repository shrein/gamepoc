//hacer crclrand para ayudar a spawnear
//poner efectos de sonido mas desentes

import ddf.minim.*;

Utility Utility;

Ship myShip;
HUD myHUD;
ArrayList myEnemies;


Minim minim;
AudioSample bulletSound, circleSound, enemySound;
PGraphics alphaBuffer;

boolean[] keys=new boolean[8];//enumerador chambon para tener mis constantes
//de teclado
final int _X=0;
final int _C=1;
final int _Z=2;
final int _UP=3;
final int _DOWN=4;
final int _LEFT=5;
final int _RIGHT=6;//constantes de teclado para el enumerador.
final int _CLICK=7;//de eventos mas bien porque este es el mouse mwhahahaa

final int enemyCount=30;
int enemiesKilled;

//bbox constants

int pMillis;
public float elapsed;

PGraphics g;

void setup() {
  size(640, 400, P3D);
  hint(DISABLE_DEPTH_TEST);
  //hint(ENABLE_NATIVE_FONTS);
  noSmooth();
  //smooth();
  alphaBuffer=createGraphics(width, height, P3D);

  alphaBuffer.beginDraw();
  //alphaBuffer.background(0);
  alphaBuffer.endDraw();

  frameRate(60);



  pMillis=0;

  Utility=new Utility();

  myShip=new Ship();
  myHUD=new HUD();

  myEnemies=new ArrayList();
  for (int i=0; i<enemyCount;i++) {
    myEnemies.add(new Enemy());
  }

  fill(0);
  noStroke();
  rect(-1, -1, width+2, height+2);

  minim = new Minim(this);
  bulletSound = minim.loadSample("pac.wav", 256);
  circleSound = minim.loadSample("puwuw.wav", 256);  
  enemySound = minim.loadSample("puc.wav", 256);

  bulletSound.setVolume(0.001);
}

void draw() {
  tint(255, 255);

  blendMode(BLEND);
  elapsed=float(millis()-pMillis)/1000;
  pMillis=millis();

  myShip.update(keys);
  myHUD.update(myShip);

  for (int i=0; i<myEnemies.size();i++) { 
    ((Enemy)myEnemies.get(i)).update();
  }

  fill(0, 64);
  noStroke();
  rect(-1, -1, width+2, height+2);

  for (int i=0; i<myEnemies.size();i++) { 
    ((Enemy)myEnemies.get(i)).draw();
  }

  //blendMode(BLEND);
  alphaBuffer.beginDraw();
  alphaBuffer.fill(0, 16);
  alphaBuffer.noStroke();
  alphaBuffer.rect(-1, -1, width+2, height+2);
  //alphaBuffer.background(0);
  alphaBuffer.endDraw();

  noStroke();
  //  blend(alphaBuffer, 0, 0, width, height, 0, 0, width, height, MULTIPLY);

  blendMode(DARKEST);
  image(alphaBuffer, 0, 0);

  blendMode(BLEND);
  myShip.draw();
  myHUD.draw();
}

//////////////////////////////Close Events////////////////////////////

void stop()
{
  // always close Minim audio classes when you are done with them
  bulletSound.close();
  circleSound.close();
  minim.stop();

  super.stop();
}


////////////////////////////CONTROLLER EVENTS///////////////////////////

void keyPressed() {

  if (key=='x'||key=='X') {
    keys[_X]=true;
  }
  if (key=='c'||key=='C'||key==' ') {
    keys[_C]=true;
  }
  if (key=='z'||key=='Z') {
    keys[_Z]=true;
  }

  if ((key==CODED&&keyCode==UP)||key=='w'||key=='W') {
    keys[_UP]=true;
  }

  if ((key==CODED&&keyCode==DOWN)||key=='S'||key=='s') {
    keys[_DOWN]=true;
  }

  if ((key==CODED&&keyCode==LEFT)||key=='A'||key=='a') {
    keys[_LEFT]=true;
  }

  if ((key==CODED&&keyCode==RIGHT)||key=='D'||key=='d') {
    keys[_RIGHT]=true;
  }

  if ((key==DELETE||key==BACKSPACE||key==RETURN||key==ENTER)||(key=='r'||key=='R')) {
    enemiesKilled=0;
    myShip=new Ship();
    myEnemies=new ArrayList();
    for (int i=0; i<enemyCount;i++) {
      myEnemies.add(new Enemy());
    }
  }
}

void keyReleased() {
  if (key=='i'||key=='I') {
    saveFrame(split(this.toString(), "[")[0]+
      "-"+day()+"."+month()+"."+year()+
      "-"+hour()+"."+minute()+"."+second()+
      ".png");
  }//pretty way to store progress in a logical way?

  if (key=='x'||key=='X') {
    keys[_X]=false;
  }
  if (key=='c'||key=='C'||key==' ') {
    keys[_C]=false;
  }
  if (key=='z'||key=='Z') {
    keys[_Z]=false;
  }

  if ((key==CODED&&keyCode==UP)||key=='w'||key=='W') {
    keys[_UP]=false;
  }

  if ((key==CODED&&keyCode==DOWN)||key=='S'||key=='s') {
    keys[_DOWN]=false;
  }

  if ((key==CODED&&keyCode==LEFT)||key=='A'||key=='a') {
    keys[_LEFT]=false;
  }

  if ((key==CODED&&keyCode==RIGHT)||key=='D'||key=='d') {
    keys[_RIGHT]=false;
  }
}

void mousePressed() {
  if (mouseButton==LEFT) {
    keys[_CLICK]=true;
  }
  if (mouseButton==RIGHT) {
    keys[_X]=true;
  }
}

void mouseReleased() {
  if (mouseButton==LEFT) {
    keys[_CLICK]=false;
  }
  if (mouseButton==RIGHT) {
    keys[_X]=false;
  }
}
//manejo de todas las teclas, cuando se presionan y cuando se sueltan
//////////////////////CONTROLLER EVENTS END///////////////////////////////////

