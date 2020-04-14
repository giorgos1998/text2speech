package model.encoding;

/**
 * <h1> Rot13 strategy </h1> 
 * @author John Rizos
 */

public class Rot13 extends LetterEncTemplate {
	public Rot13() {
		translateMap = "nopqrstuvwxyzabcdefghijklm";
	}
}
