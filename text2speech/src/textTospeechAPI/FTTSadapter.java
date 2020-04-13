package textTospeechAPI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FTTSadapter implements TextToSpeechApi {
	
    private static Voice voice;
	
	public FTTSadapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		voice = VoiceManager.getInstance().getVoices()[0];
		voice.allocate();
		System.out.println(voice.getRate());
		
	}
	@Override
	public void play(String textToPlay) {
        voice.speak(textToPlay);
	}
	@Override
	public void setVolume(float volume) { 	//0.4-1 step .005
		voice.setVolume(volume);
	}
	@Override
	public void setPich(float pitch) {  	//1-500 step 1
		voice.setPitch(pitch);
	}
	@Override
	public void setRate(float rate) {   	//50-999 step 1
		voice.setRate(rate);
	}
	@Override
	public void deallocate() {
		voice.deallocate();
	}
}
