package am.okk.amoeba;

public class MathUtil {
	public static float randc(float radius) {
		return (float) (Math.random() * 2 * radius - radius);
	}

	public static float max(float a, float b) {
		return Math.max(a, b);
	}

	public static float min(float a, float b) {
		return Math.min(a, b);
	}

	public static float signum(float a) {
		return Math.signum(a);
	}

	public static float lim(float r, float a, float b) {
		if (r <= a)
			return a;
		else if (r >= b)
			return b;
		else
			return r;
	}
}
