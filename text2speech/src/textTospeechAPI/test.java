package textTospeechAPI;

//import com.sun.speech.freetts.Voice;
//import com.sun.speech.freetts.VoiceManager;

public class test {
	/*   private static Voice voice;

    private static void speak(String msg){
        voice = VoiceManager.getInstance().getVoices()[0];
        voice.allocate();
        voice.speak(msg);
        voice.deallocate();
    }*/

    public static void main(String[] agrs){
    	
    	
        String[] text = new String[]{"The usual approach of science of constructing a mathematical model "
        		+ "	cannot answer the questions of why there should be a universe for the"
        		+ "	model to describe. Why does the universe go to all the bother of existing?",
        		"hello world"};
        
        TextToSpeechApiFactory fac = new TextToSpeechApiFactory();
        
        TextToSpeechApi api = fac.createSpeechApi("freetts");
        
        
        /*int i;
        
        for(i=0;i<text.length;i++){
            System.out.println(text[i]);
            api.play(text[i]);
        }*/
        
        api.setVolume(0.9f);
        System.out.println(text[1]);
        api.play(text[1]);

        System.out.println("done");
        api.deallocate();
    }
}
