package model;

import java.util.ArrayList;
import model.encoding.EncStrategy;
import model.encoding.EncStrategyFactory;
import model.textToSpeechAPI.TextToSpeechApi;
import model.textToSpeechAPI.TextToSpeechApiFactory;

public class Document {
	
	private ArrayList<Line> contents;
	private DocumentGenerator docGenerator;
	private TextToSpeechApi speechAPI;
	private TextToSpeechApiFactory speechFactory;
	private EncStrategy encoder;
	private EncStrategyFactory encoderFactory;
	//maybe more
	
	public Document() {
		speechFactory = new TextToSpeechApiFactory();
		speechAPI = speechFactory.createSpeechApi("FREETTS");	//default value
		encoderFactory = new EncStrategyFactory();
		encoder = encoderFactory.createStrategy("ROT13");
	}
	
	private String docContentsToString(String text) {
		
		contents = docGenerator.createDocData(text);	//refresh contents
		String convertedText = "";
		
		if(contents.isEmpty()) {
			//error message
		}
		else if(contents.size() == 1) {
			convertedText = contents.get(0).joinWords();
		}
		else {
			convertedText = contents.get(0).joinWords();
			for(int i=1; i<contents.size(); i++) {
				convertedText += " " + contents.get(i).joinWords();
			}
		}
		return convertedText;
	}
	
	public void playAll(String text) {
		String textToPlay = this.docContentsToString(text);
		speechAPI.play(textToPlay);
	}
	
	public void playSelected(String text) {
		speechAPI.play(text);
	}
	
	public void playAllEncoded(String text) {
		String textToEncode = this.docContentsToString(text);
		String textToPlay = encoder.encode(textToEncode);
		speechAPI.play(textToPlay);
	}
	
	public void playSelectedEncoded(String text) {
		String textToPlay = encoder.encode(text);
		speechAPI.play(textToPlay);
	}
	
	public void playAllReverse(String text) {
		//TODO
	}
	
	public void playSelectedReverse(String text) {
		//TODO
	}
	
	public void saveFile() {
		//TODO
	}
	
	public void openFile() {
		//TODO
	}
	
	public void saveSettings(float volume, float speed, float pitch, String strategy) {
		speechAPI.setVolume(volume);
		speechAPI.setRate(speed);
		speechAPI.setPich(pitch);
		encoder = encoderFactory.createStrategy(strategy);
	}
}
