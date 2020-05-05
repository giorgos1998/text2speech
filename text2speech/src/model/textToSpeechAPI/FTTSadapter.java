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
	/**
	 * @param volume gets converted to float in range [0.4-1] anything under 0.4 is almost inaudible
	 * default 100 (converted to 1) 
	 */
	@Override
	public void setVolume(int volume) {
		//NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin
		float inRangeVol = (((volume-1)*0.6f)/99)+0.4f;
		voice.setVolume(inRangeVol);
	}
	/**
	 * @param pitch gets converted to float in range [1-500] Hz 
	 * default 21 (converted to 100) 
	 */
	@Override
	public void setPich(int pitch) {
		//NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin
		float inRangePitch = (((pitch-1)*499)/99)+1;
		voice.setPitch(inRangePitch);
	}
	/**
	 * @param volume gets converted to float in range [50-999] wpm anything under 50 is incoherent
	 * default 11 (converted to 150) 
	 */
	@Override
	public void setRate(int rate) {
		//NewValue = (((OldValue - OldMin) * NewRange) / OldRange) + NewMin
		float inRangeRate = (((rate-1)*949)/99)+50;
		voice.setRate(inRangeRate);
	}
	@Override
	public void deallocate() {
		voice.deallocate();
	}
}
