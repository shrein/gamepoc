package sonar;

import java.util.List;

import processing.core.PGraphics;
import engine.RenderManager;
import engine.SoundEnum;
import engine.SoundManager;
import engine.View;

public class ShipView extends View {

	private SonarView sv;
	private BulletView bv;

	public ShipView(int depth, Ship ship, SonarView sv, BulletView bv) {
		super(depth);
		this.addModelReference(ship);
		Sonar sonar = ship.getSonar();
		sv.addModelReference(sonar);
		List<Bullet> bullets = ship.getMyBullets();
		for (Bullet bullet : bullets) {
			bv.addModelReference(bullet);
		}
	}
	
	@Override
	public void draw(RenderManager r) {
		Ship ship = (Ship) this.getFirstModelReference();

		PGraphics g = r.getGraphicsByDepth(this.getViewDepth());
		drawShip(ship, g);

		sv.draw(r);
		bv.draw(r);
	}

	private void drawShip(Ship ship, PGraphics g) {
		if (ship.isAlive()) {
			g.pushMatrix();
			g.translate(ship.getPosition().x, ship.getPosition().y);
			g.rotate((float) ship.getTheta());
			g.stroke(255);
			g.noFill();
			g.beginShape();
			g.vertex(-3, -6);
			g.vertex(3, -6);
			g.vertex(0, 6);
			g.vertex(-3, -6);
			g.endShape();
			g.popMatrix();
		}
	}

	@Override
	public void sing(SoundManager sm) {
		Ship ship = (Ship) this.getFirstModelReference();
		if(ship.isDieEvent()) {
			sm.play(SoundEnum.ENEMY);
		}
		if(ship.getSonar().isSonarEvent()) {
			sm.play(SoundEnum.CIRCLE);
		}
	}

}
