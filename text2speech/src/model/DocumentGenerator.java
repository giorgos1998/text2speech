package model;

import java.util.ArrayList;

public class DocumentGenerator {
	
	public ArrayList<Line> createDocData(String input){
		
		ArrayList<Line> docData = new ArrayList<Line>();
		
		String[] lines = input.split("\\r?\\n");
		
		for(int i=0; i<lines.length; i++) {
			docData.add(new Line(lines[i].trim().split("\\s+")));
		}
		
		return docData;
	}
}
