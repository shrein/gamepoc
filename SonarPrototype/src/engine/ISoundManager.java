package engine;

public interface ISoundManager {

	public abstract void setup();

	public abstract void mute();

	public abstract void unmute();

	public abstract void setVolume(float volume);

	public abstract float getVolume();

	public abstract void play(SoundEnum s);

	public abstract boolean isMuted();

}