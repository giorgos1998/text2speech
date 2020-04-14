package model.encoding;

/**
 * <h1> Encoding Strategy Factory </h1> 
 * @author John Rizos
 */

public class EncStrategyFactory {
	
	/**
	 * Creates Strategies for any implemented Encodings
	 * currently implemented: Rot13, AtBash
	 * @param strategyType Name of the encoding strategy needed, case ignored
	 */
	public EncStrategy createStrategy(String strategyType) {
		switch(strategyType.toUpperCase()) {
			case("ROT13"):
				return new Rot13();
			case("ATBASH"):
				return new AtBash();
			default:
				System.out.println("Error: Worng strategy name given to EncStrategyFactory.");
				return null;
		}
	}
}
