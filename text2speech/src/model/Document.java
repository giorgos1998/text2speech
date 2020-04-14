package model;

import java.util.ArrayList;

import model.textToSpeechAPI.TextToSpeechApi;
import model.textToSpeechAPI.TextToSpeechApiFactory;

public class Document {
	
	private ArrayList<Line> contents;
	private DocumentGenerator docGenerator;
	private TextToSpeechApi speechAPI;
	private TextToSpeechApiFactory speechFactory;
	//encoding
	//encoding factory
	//maybe more
	
	public Document() {
		speechFactory = new TextToSpeechApiFactory();
		speechAPI = speechFactory.createSpeechApi("FREETTS");	//default value
		//initiate encoding + factory
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
		String textToPlay = this.docContentsToString(text);
		//TODO
	}
	
	public void playSelectedEncoded(String text) {
		//TODO
	}
	
	public void saveFile() {
		//TODO
	}
	
	public void openFile() {
		//TODO
	}
}
