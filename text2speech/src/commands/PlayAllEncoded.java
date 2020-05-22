package commands;

import java.awt.event.ActionEvent;
import gui.FreeTTSWindow;
import model.Document;

public class PlayAllEncoded extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	
	public PlayAllEncoded(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("PlayAllEncoded");
		}
		else {
			execute();
		}
	}

	@Override
	public void execute() {
		String text = frame.getTextArea().getText();
		doc.playAllEncoded(text);
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, this command does not need to know if it is a clone or not	
	}
}
