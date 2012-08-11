/**
 * 
 */
package am.okk.amoeba;

import static am.okk.amoeba.MathUtil.lim;
import static am.okk.amoeba.MathUtil.randc;
import static am.okk.amoeba.MathUtil.signum;
import processing.core.PGraphics;

public class AmoebaBit {
	private static final float KV2 = 0.01f;
	private static final float KR = 0.1f;
	private static final float K2 = 0.1f;
	/**
	 * 
	 */
	private final Amoeba amoeba;
	int index;
	float dt;
	float r, vr;
	int[] rgb;
	float x, y, vx, vy;
	float targetX, targetY, targetR;
	boolean pressed;

	public AmoebaBit(Amoeba amoeba, int index, float dt, float r, float vr,
			float x, float y, float vx, float vy, int[] rgb) {
		super();
		this.amoeba = amoeba;
		this.index = index;
		this.dt = dt;
		this.r = r;
		targetR = r + randc(r * 0.2f);
		this.vr = vr;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.rgb = rgb;
	}

	public void update(float ar, float ax, float ay) {
		if (pressed) {
			rgb[0] = 255;
			rgb[1] = 255;
			rgb[2] = 255;
		} else {
			rgb[0] = (int) (255f * 2 * r / targetR - 283);
			rgb[1] = (int) (255f * 2 * r / targetR - 383);
			rgb[2] = (int) (255f * 2 * r / targetR - 383);
		}
		vr += (ar + lim(this.amoeba.model.getKT(), Amoeba.K1 / 2, Amoeba.K1)
				* (targetR - r) - vr * KV2)
				* dt;
		vx += (ax - vx * KV2) * dt;
		vy += (ay - vy * KV2) * dt;
		r += vr * dt;
		x += vx * dt;
		y += vy * dt;
	}

	public void draw() {
		PGraphics g = this.amoeba.getPGraphics();
		g.fill(rgb[0], rgb[1], rgb[2], 128);
		g.ellipse(x, y, 2 * r, 2 * r);
		if (pressed) {
			g.textAlign(PGraphics.CENTER);
			g.fill(255, 0, 0);
			g.text(this.amoeba.chars[index], x, y + 5);
		}
	}

	public void setPressed(boolean pressed) {
		if (this.pressed && !pressed) {
			vr += signum(vr) * KR;
			vx += signum(vx) * K2;
			vy += signum(vy) * K2;
		}
		this.pressed = pressed;
	}
}