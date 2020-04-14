package textTospeechAPI;

/**
 * <h1> TTS Api Factory </h1> 
 * @author John Rizos
 */

public class TextToSpeechApiFactory {
	
	/**
	 * Creates Api Adapters for any implemented TTS service
	 * currently implemented: FreeTTS, FakeTTS
	 * @param serviceName Name of the service needed, case ignored
	 */
	public TextToSpeechApi createSpeechApi(String serviceName) {
		switch(serviceName.toUpperCase()) {
			case("FREETTS"):
				return new FTTSadapter();
			case("FAKETTS"):
				return new FakeTTSadapter();
			default:
				System.out.println("Error: Worng TTS service name given to ApiFactory.");
				return null;
		}
	}
}
