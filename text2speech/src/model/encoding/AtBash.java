package model.encoding;

/**
 * <h1> AtBash strategy </h1> 
 * @author John Rizos
 */

public class AtBash extends LetterEncTemplate {
	/**
	 * I only have to set the translateMap so any letter
	 * will be translated to the correct letter
	 */
	public AtBash() {
		translateMap = "zyxwvutsrqponmlkjihgfedcba";
	}
}
