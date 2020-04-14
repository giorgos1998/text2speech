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
	 * Out of range parameters are set automatically to default (1)
	 * @param volume A float in range [0.4-1] anything under 0.4 is inaudible
	 */
	public void setVolume(float volume);
	/**
	 * Sets the pitch of sound played
	 * Out of range parameters are set automatically to default (100)
	 * @param volume A float in range [1-500] Hz
	 */
	public void setPich(float pitch);
	/**
	 * Sets the rate of sound played
	 * Out of range parameters are set automatically to default (150)
	 * @param volume A float in range [50-999] wpm anything under 50 is incoherent
	 */
	public void setRate(float rate);
	/**
	 * To be called when the API is switched to another service or at end of program to
	 * close the API currently used.
	 * Deallocates any resources allocated per requirement of the service.
	 */
	public void deallocate();
}
