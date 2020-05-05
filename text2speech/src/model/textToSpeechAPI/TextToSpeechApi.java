package model.textToSpeechAPI;

/**
 * <h1> Interface for every Api Adapter </h1> 
 * @author John Rizos
 */

public interface TextToSpeechApi {
	
	/**
	 * Plays the text given in a String
	 * @param textToPlay String of text to play
	 */
	public void play(String textToPlay);
	/**
	 * Sets the volume of sound played
	 * Out of range parameters are set automatically to default
	 * @param volume int inbetween [1-100]
	 */
	public void setVolume(int volume);
	/**
	 * Sets the pitch of sound played
	 * Out of range parameters are set automatically to default
	 * @param pitch int inbetween [1-100]
	 */
	public void setPich(int pitch);
	/**
	 * Sets the rate of sound played
	 * Out of range parameters are set automatically to default
	 * @param rate int inbetween [1-100]
	 */
	public void setRate(int rate);
	/**
	 * To be called when the API is switched to another service or at end of program to
	 * close the API currently used.
	 * Deallocates any resources allocated per requirement of the service.
	 */
	public void deallocate();
}
