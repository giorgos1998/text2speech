package textTospeechAPI;

public class TextToSpeechApiFactory {
	
	public TextToSpeechApi createSpeechApi(String serviceName) {
		
		if(serviceName.equalsIgnoreCase("FREETTS")) {
			return new FTTSadapter();
		}
		else if(serviceName.equalsIgnoreCase("FAKETTS")) {
			return new FakeTTSadapter();
		}
		else return null;
	}
}
