package model.textToSpeechAPI;

/**
 * <h1> FakeTTS Api Adapter </h1>
 * 
 * A fake TTS adapter, does not actually do anything more than
 * print the parameters given per method to System.out
 * 
 * @author John Rizos
 */

public class FakeTTSadapter implements TextToSpeechApi {
	
	@Override
	public void play(String textToPlay) {
		System.out.println("This is a fake text to speech, it can only convert text to text.");
		System.out.println("Here's your text:");
		System.out.println(textToPlay);
	}
	@Override
	public void setVolume(float volume) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Volume is a property of sound, you tried to change it to: "+ volume);
	}
	@Override
	public void setPich(float pitch) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Pitch is a property of sound, you tried to change it to: "+ pitch);
	}
	@Override
	public void setRate(float rate) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Rate controls words per minute, you tried to change it to: "+ rate);
	}
	@Override
	public void deallocate() {
		System.out.println("Fake tts was deallocated.");
	}
}
