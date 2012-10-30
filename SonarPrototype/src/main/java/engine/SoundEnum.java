package engine;

public enum SoundEnum {
	BULLET ("pac.wav", null, null),
	CIRCLE ("puwuw.wav", null, null),
	ENEMY ("puc.wav", null, 0.001f),
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