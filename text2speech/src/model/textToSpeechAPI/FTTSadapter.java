package model.textToSpeechAPI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * <h1> FreeTTS Api Adapter </h1> 
 * @author John Rizos
 */

public class FTTSadapter implements TextToSpeechApi {
	
    private static Voice voice;
	
    /**
	 * Creates Api Adapter for FreeTTS service
	 * initializes voice from FreeTTS and sets a needed system property
	 */
	public FTTSadapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		voice = VoiceManager.getInstance().getVoices()[0];
		voice.allocate();
		
	}
	@Override
	public void play(String textToPlay) {
        voice.speak(textToPlay);
	}
	@Override
	public void setVolume(float volume) {
		voice.setVolume(volume);
	}
	@Override
	public void setPich(float pitch) {
		voice.setPitch(pitch);
	}
	@Override
	public void setRate(float rate) {
		voice.setRate(rate);
	}
	@Override
	public void deallocate() {
		voice.deallocate();
	}
}
