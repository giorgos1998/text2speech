package model;

import java.util.ArrayList;

/**
 * <h1> Document Generator </h1> 
 * @author Georgios Papadatos
 */

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
