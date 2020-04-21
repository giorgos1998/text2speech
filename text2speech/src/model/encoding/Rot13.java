package model.encoding;

/**
 * <h1> Rot13 strategy </h1> 
 * @author John Rizos
 */

public class Rot13 extends LetterEncTemplate {
	/**
	 * I only have to set the translateMap so any letter
	 * will be translated to the correct letter
	 */
	public Rot13() {
		translateMap = "nopqrstuvwxyzabcdefghijklm";
	}
}
