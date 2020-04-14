package model.encoding;

import java.util.HashMap;

/**
 * <h1> Encoding Template </h1> 
 * @author John Rizos
 */

public abstract class EncTemplate implements EncStrategy {
	
	public HashMap<String,String> map;
	
	//public abstract String encode(String textToEncode);
	
	public String mapFunc(String letter) {
		
		return map.get(letter);
	}
}
