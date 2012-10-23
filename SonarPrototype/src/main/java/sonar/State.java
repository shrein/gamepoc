package sonar;

public interface State{
	public abstract void setup();
	public abstract void draw();
	public abstract void update();
}