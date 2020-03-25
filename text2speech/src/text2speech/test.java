package text2speech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class test {
	VoiceManager freettsVM;
    private static Voice voice;

    private static void speak(String msg){
        voice = VoiceManager.getInstance().getVoices()[0];
        voice.allocate();
        voice.speak(msg);
        voice.deallocate();
    }

    public static void main(String[] agrs){
    	System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    	
        String[] text = new String[]{"The usual approach of science of constructing a mathematical model "
        		+ "	cannot answer the questions of why there should be a universe for the"
        		+ "	model to describe. Why does the universe go to all the bother of existing?",
        		"hello world"};
        int i;
        
        for(i=0;i<text.length;i++){
            System.out.println(text[i]);
            speak(text[i]);
        }
        
    }
}
