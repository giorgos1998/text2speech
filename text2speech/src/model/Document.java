package model;

import java.util.ArrayList;

public class Document {
	
	private ArrayList<Line> contents;
	private DocumentGenerator docGenerator;
	
	public Document() {
		
	}
	
	public void playAll(String text) {
		contents = docGenerator.createDocData(text);
		String textToPlay;
		//TODO
	}
}
