package textTospeechAPI;

public interface TextToSpeechApi {
	public void play(String textToPlay);
	public void setVolume(int volume);
	public void setPich(double pitch);
	public void setSpeed(double speed);
}
