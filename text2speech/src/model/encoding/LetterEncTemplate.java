package model.encoding;

/**
 * <h1>  Letter Encoding Template </h1> 
 * Template for encoding letters in a string with a 1-1 function.
 * Ignores characters other than letters.
 * Any extension of this Template simply requires to set the translation map String.
 * For other characters, different kinds of translation (ex not 1-1) we need a 
 * different template, or a normal implementation of Encoding Strategy.
 * @author John Rizos
 */

public abstract class LetterEncTemplate implements EncStrategy {
	
	/**
	 * map and translateMap are Strings that contain all the characters of the alphabet 
	 * that gets encoded by this template once in a logical order.
	 * That order for translateMap is for each index i the character that map[i] gets 
	 * translated to: map[i] -> translateMap[i] 
	 * They are both Strings (essentially char[]) because its easier to initialize them that way.
	 */
	
	protected String translateMap; 
	private String map = "abcdefghijklmnopqrstuvwxyz";
	
	@Override
	public String encode(String textToEncode) {

		int idx;
		char[] translated = new char[textToEncode.length()];
		
		for (int i = 0; i < textToEncode.length(); i++){
			if(Character.isLetter(textToEncode.charAt(i))) {
				if(Character.isUpperCase(textToEncode.charAt(i))) {
					idx = map.indexOf(Character.toLowerCase(textToEncode.charAt(i)));
					translated[i] = Character.toUpperCase(translateMap.charAt(idx));
				}
				else {
					idx = map.indexOf(textToEncode.charAt(i));
					translated[i] = translateMap.charAt(idx);
				}
			}
			else {
				translated[i] = textToEncode.charAt(i);
			}
		}
		return new String(translated);
	}
	
}
