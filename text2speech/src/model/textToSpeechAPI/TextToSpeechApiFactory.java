package model.textToSpeechAPI;

/**
 * <h1> TTS Api Factory </h1> 
 * @author John Rizos
 */

public class TextToSpeechApiFactory {
	
	/**
	 * Creates Api Adapters for any implemented TTS service
	 * currently implemented: FreeTTS, FakeTTS
	 * @param apiType Name of the api needed, case ignored
	 */
	public TextToSpeechApi createSpeechApi(String apiType) {
		switch(apiType.toUpperCase()) {
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
