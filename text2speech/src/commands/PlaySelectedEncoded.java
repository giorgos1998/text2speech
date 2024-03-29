package commands;

import java.awt.event.ActionEvent;
import gui.FreeTTSWindow;
import model.Document;

/**
 * <h1> Play Selected Encoded Command </h1> 
 * @author Georgios Papadatos
 */

public class PlaySelectedEncoded extends Command{

	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	
	public PlaySelectedEncoded(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("PlaySelectedEncoded");
		} else {
			execute();
		}
	}

	@Override
	public void execute() {
		String text = frame.getTextArea().getSelectedText();
		doc.playSelectedEncoded(text);
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, this command does not need to know if it is a clone or not	
	}
}
