package am.okk.amoeba;


public class Amoeba extends AbstractDrawable {
	static final int R_BIT = 20;
	static final int RADIUS1 = 5 * R_BIT;
	static final int R_NUCLEUS = 40;
	static final float KV1 = 0.025f;
	static final float K1 = 0.1f;

	Character[] chars;
	AmoebaModel model = new AmoebaModel(1f);

	public Amoeba(float dt, float x, float y, float vx, float vy, float ax,
			float ay) {
		super();
		this.model.setDt(dt);
		this.model.setX(x);
		this.model.setY(y);
		this.model.setVx(vx);
		this.model.setVy(vy);
		this.model.setAx(ax);
		this.model.setAy(ay);
		System.out.println(dt);
	}

	/**
	 * @deprecated Use {@link am.okk.amoeba.AmoebaModel#setup(am.okk.amoeba.Amoeba)} instead
	 */
	@Override
	public void setup() {
		model.setup(this);
	}

	/**
	 * @deprecated Use {@link am.okk.amoeba.AmoebaModel#update(am.okk.amoeba.Amoeba)} instead
	 */
	@Override
	public void update() {
		model.update(this);
	}

	@Override
	public void draw() {
		for (int i = 0; i < model.getBits().length; i++) {
			model.getBits()[i].draw();
		}
	}

	public void keyPressed(char c) {
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != null && c == chars[i]) {
				model.getBits()[i].setPressed(true);
			} else {
				model.getBits()[i].setPressed(false);
			}
		}
	}

	public void keyReleased(char c) {
		model.setKT((float)(model.getKT() + 0.25));
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != null && c == chars[i]) {
				model.getBits()[i].setPressed(false);
			}
		}
	}

}
