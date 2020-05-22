package commands;

import java.awt.event.ActionEvent;
import gui.FreeTTSWindow;
import model.Document;

/**
 * <h1> Tune Audio Command </h1> 
 * 
 * Creates the "Preferences" window that contains
 * the sliders that set the values of volume, speed, pitch 
 * and encoding strategy of the speech.
 * You can either press the "Apply" button to save any changes 
 * made or press the "Cancel" button to discard them.
 * 
 * @author Vasiliki Kanakari
 */


public class TuneAudio extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	private boolean cloneFlag = false;
	
	private int volumeValue;
	private int speedValue;
	private int pitchValue;
	private String encodingStrategy;
	private String speechLibrary;
	
	
	public TuneAudio(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("TuneAudioCommand");
		}
		else {
			execute();
		}
	}

	@Override
	public void execute() {
		if (cloneFlag == false) {
			volumeValue = frame.getVolumeValue();
			speedValue = frame.getSpeedValue();
			pitchValue = frame.getPitchValue();
			encodingStrategy = frame.getEncodingStrategy();
			speechLibrary = frame.getSpeechLibrary();
		}
		frame.setVolumeValue(volumeValue);
		frame.setSpeedValue(speedValue);
		frame.setPitchValue(pitchValue);
		frame.setEncodingStrategy(encodingStrategy);
		frame.setSpeechLibrary(speechLibrary);
		frame.closePreferencesWindow();
		
		//TODO Add model.Document.saveSettings()
		doc.saveSettings(volumeValue, speedValue, pitchValue, encodingStrategy, speechLibrary);
	}

	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}