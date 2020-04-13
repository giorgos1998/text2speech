package textTospeechAPI;

public interface TextToSpeechApi {
	public void play(String textToPlay);
	public void setVolume(float volume);
	public void setPich(float pitch);
	public void setRate(float rate);
	public void deallocate();
}
