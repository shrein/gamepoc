package am.okk.amoeba;

import static am.okk.amoeba.MathUtil.lim;
import static am.okk.amoeba.MathUtil.randc;

public class AmoebaModel {
	private float KT;
	private AmoebaBit[] bits;
	private float dt;
	private float x;
	private float y;
	private float vx;
	private float vy;
	private float ax;
	private float ay;
	private float targetX;
	private float targetY;

	public AmoebaModel(float kT) {
		KT = kT;
	}

	public float getKT() {
		return KT;
	}

	public void setKT(float kT) {
		KT = kT;
	}

	public AmoebaBit[] getBits() {
		return bits;
	}

	public void setBits(AmoebaBit[] bits) {
		this.bits = bits;
	}

	public float getDt() {
		return dt;
	}

	public void setDt(float dt) {
		this.dt = dt;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getAx() {
		return ax;
	}

	public void setAx(float ax) {
		this.ax = ax;
	}

	public float getAy() {
		return ay;
	}

	public void setAy(float ay) {
		this.ay = ay;
	}

	public float getTargetX() {
		return targetX;
	}

	public float getTargetY() {
		return targetY;
	}

	public void setTarget(float targetX, float targetY) {
		this.targetX = targetX;
		this.targetY = targetY;
	}

	public void update(Amoeba amoeba) {
		float _tx, _ty;
		setKT((float) (getKT() * 0.95));
		_tx = getTargetX() - getX();
		_ty = getTargetY() - getY();
		float tsq2 = (float) Math.sqrt(_tx * _tx + _ty * _ty);
		_tx = _tx / tsq2;
		_ty = _ty / tsq2;
		setVx(getVx() + (getAx() - getVx() * Amoeba.KV1 + getKT() * _tx)
				* getDt());
		setVy(getVy() + (getAy() - getVy() * Amoeba.KV1 + getKT() * _ty)
				* getDt());
		setX(getX() + getVx() * getDt());
		setY(getY() + getVy() * getDt());
		for (int i = 0; i < getBits().length; i++) {
			getBits()[i].update(0, lim(getKT(), Amoeba.K1 / 2, Amoeba.K1)
					* (getX() - getBits()[i].x),
					lim(getKT(), Amoeba.K1 / 2, Amoeba.K1)
							* (getY() - getBits()[i].y));
		}
	}

	public void setup(Amoeba amoeba) {
		setBits(new AmoebaBit[4]);
		getBits()[0] = new AmoebaBit(amoeba, 0, getDt(), Amoeba.R_NUCLEUS, 0,
				getX(), getY(), getVx(), getVy(), new int[] { 255, 255, 255 });
		getBits()[1] = new AmoebaBit(amoeba, 1, getDt(), Amoeba.R_BIT, 0,
				getX() + randc(Amoeba.RADIUS1), getY() + randc(Amoeba.RADIUS1),
				getVx() + randc(0.05f * getVx()), getVy()
						+ randc(0.05f * getVy()), new int[] { 100, 100, 100 });
		getBits()[2] = new AmoebaBit(amoeba, 2, getDt(), Amoeba.R_BIT, 0,
				getX() + randc(Amoeba.RADIUS1), getY() + randc(Amoeba.RADIUS1),
				getVx() + randc(0.05f * getVx()), getVy()
						+ randc(0.05f * getVy()), new int[] { 100, 100, 100 });
		getBits()[3] = new AmoebaBit(amoeba, 3, getDt(), Amoeba.R_BIT, 0,
				getX() + randc(Amoeba.RADIUS1), getY() + randc(Amoeba.RADIUS1),
				getVx() + randc(0.05f * getVx()), getVy()
						+ randc(0.05f * getVy()), new int[] { 100, 100, 100 });
	}
}