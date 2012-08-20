package engine;

import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import ddf.minim.AudioSample;
import ddf.minim.Minim;

/**
 * This class plays every sound. No workarounds.
 * 
 * @author shrein
 *
 */
public class SoundManager {
	private PApplet parent;
	Minim minim;
	AudioSample bulletSound, circleSound, enemySound;
	Map<SoundEnum, AudioSample> sounds;
	// TODO: Find out how manage volume and mute directly with Minim
	boolean muted;

	public SoundManager(PApplet parent) {
		this.parent = parent;
		muted = false;
	}

	public void setup() {
		minim = new Minim(parent);
		sounds = new HashMap<SoundEnum, AudioSample>();
		for (SoundEnum s : SoundEnum.values()) {
			AudioSample as = null;
			if (s.bufferSize != null) {
				as = minim.loadSample(s.filename, s.bufferSize);
			} else {
				as = minim.loadSample(s.filename);
			}
			if (s.volume != null) {
				as.setVolume(s.volume);
			}
			sounds.put(s, as);
		}
	}

	public void mute() {
		muted = true;
		// minim.getLineOut().mute();
	}

	public void unmute() {
		 muted = false;
		// minim.getLineOut().unmute();
	}

	public boolean isMuted() {
		return muted;
		// return minim.getLineOut().isMuted();
	}

	public void setVolume(float volume) {
		throw new UnsupportedOperationException();
		// minim.getLineOut().setVolume(volume);
	}

	public float getVolume() {
		throw new UnsupportedOperationException();
		// return minim.getLineOut().getVolume();
	}
	
	public void play(SoundEnum s) {
		if (!muted) {
			try {
				AudioSample as = sounds.get(s);
				as.trigger();
			} catch (Exception e) {
				System.err.println("Error playing sound with key " + s);
				e.printStackTrace();
			}
		}
	}
}
