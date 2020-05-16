package model.textToSpeechAPI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h1> FakeTTS Api Adapter </h1>
 * 
 * A fake TTS adapter, does not actually do anything more than
 * print the parameters given per method to System.out
 * 
 * @author John Rizos
 */

public class FakeTTSadapter implements TextToSpeechApi {
	
	private File apiOutput;
	
	public FakeTTSadapter() {
		apiOutput = new File("FakeTTSApiOut.txt");
	}
	
	@Override
	public void play(String textToPlay) {
		System.out.println("This is a fake text to speech, it can only convert text to text.");
		System.out.println("Here's your text:");
		System.out.println(textToPlay);
		try {
			FileWriter fr = new FileWriter(apiOutput);
			fr.write(textToPlay);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void setVolume(int volume) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Volume is a property of sound, you tried to change it to: "+ volume);
	}
	@Override
	public void setPich(int pitch) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Pitch is a property of sound, you tried to change it to: "+ pitch);
	}
	@Override
	public void setRate(int rate) {
		System.out.println("This is a fake text to speech, it can not produce sound.");
		System.out.println("Rate controls words per minute, you tried to change it to: "+ rate);
	}
	@Override
	public void deallocate() {
		System.out.println("Fake tts was deallocated.");
	}
}
