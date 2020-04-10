package textTospeechAPI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FTTSadapter implements TextToSpeechApi {
	
    private static Voice voice;
	
	public FTTSadapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	}
	
	public void play(String textToPlay) {
		voice = VoiceManager.getInstance().getVoices()[0];
        voice.allocate();
        voice.speak(textToPlay);
        voice.deallocate();
	}
	public void setVolume(int volume) {
		//to do
	}
	public void setPich(double pitch) {
		//to do
	}
	public void setSpeed(double speed) {
		//to do
	}
}
