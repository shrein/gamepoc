package engine;

import processing.core.PApplet;

public class Environment implements ISoundManager {
	private ISoundManager sm;
	private PhysicsManager pm;
	private DisplayManager dm;

	public Environment(PApplet parent) {
		sm = new SoundManager(parent);
		pm = new PhysicsManager(parent);
		dm = new DisplayManager(parent);
	}

	public void setup() {
		sm.setup();
		pm.setup();
		dm.setup();
	}

	//SoundManager delegation
	@Override
	public void mute() {
		sm.mute();
	}

	@Override
	public void unmute() {
		sm.unmute();

	}

	@Override
	public void setVolume(float volume) {
		sm.setVolume(volume);
	}

	@Override
	public float getVolume() {
		return sm.getVolume();
	}

	@Override
	public void play(SoundEnum s) {
		sm.play(s);
	}

	public boolean isMuted() {
		return sm.isMuted();
	}
}
