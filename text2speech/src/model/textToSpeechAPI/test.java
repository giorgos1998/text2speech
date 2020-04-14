package model.textToSpeechAPI;

public class test {

    public static void main(String[] agrs){
    	
    	
        String[] text = new String[]{"The usual approach of science of constructing a mathematical model "
        		+ "	cannot answer the questions of why there should be a universe for the"
        		+ "	model to describe. Why does the universe go to all the bother of existing?",
        		"hello world"};
        
        TextToSpeechApiFactory fac = new TextToSpeechApiFactory();
        
        TextToSpeechApi api = fac.createSpeechApi("freetts");
        
        api.setVolume(0.9f);
        System.out.println(text[1]);
        api.play(text[1]);

        System.out.println("done");
        api.deallocate();
    }
}
