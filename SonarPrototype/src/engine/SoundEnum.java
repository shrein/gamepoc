package engine;

public enum SoundEnum {
	BULLET ("pac.wav", 256, null),
	CIRCLE ("puwuw.wav", 256, null),
	ENEMY ("puc.wav", 256, 0.001f);
	
	public final String filename;
	public final int bufferSize;
	public final Float volume;
	SoundEnum(String filename, int bufferSize, Float volume){
		this.filename = filename;
		this.bufferSize = bufferSize;
		this.volume = volume;
	}
}