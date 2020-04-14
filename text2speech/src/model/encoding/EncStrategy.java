package model.encoding;

/**
 * <h1> Encoding Strategy Interface </h1> 
 * @author John Rizos
 */

public interface EncStrategy {
	
	/**
	 * Encode the string and return the encoded
	 * @param textToEncode String to encode
	 */
	public String encode(String textToEncode);
}
