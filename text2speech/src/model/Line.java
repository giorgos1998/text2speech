package model;

/**
 * <h1> Line </h1> 
 * @author Georgios Papadatos
 */

public class Line {

	private String[] words;
	
	public Line(String[] words) {
		this.words = words;;
	}
	
	public String joinWords() {
		if(words.length == 1) {
			return words[0];
		}
		else {
			return String.join(" ",words);
		}
	}
}
