package text2speech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class whatever {

	VoiceManager freettsVM;
    private static Voice voice;

    private static void speak(String msg){
        voice = VoiceManager.getInstance().getVoice("kevin16");
        voice.allocate();
        voice.speak(msg);
        voice.deallocate();
    }

    public static void main(String[] agrs){
    	System.out.println("Hello!");
        String[] text = new String[]{"hello world","hello world 2"};
        int i;
        for(i=0;i<text.length;i++){
            System.out.println(text[i]);
            speak(text[i]);
        }
    }

}
