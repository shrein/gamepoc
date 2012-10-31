package engine;

public enum SoundEnum {
	BULLET ("pac.wav", null, 0.1f),
	CIRCLE ("puwuw.wav", null, 0.1f),
	ENEMY ("puc.wav", null, 0.0005f),
	MCGUFFIN ("pururururu.wav", null, 0.001f);
	
	public final String filename;
	public final Integer bufferSize;
	public final Float volume;
	SoundEnum(String filename, Integer bufferSize, Float volume){
		this.filename = filename;
		this.bufferSize = bufferSize;
		this.volume = volume;
	}
}