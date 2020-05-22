package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;

public class PlayAll extends Command{

	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	
	public PlayAll(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("PlayAllCommand");
		}
		else {
			execute();
		}
	}

	@Override
	public void execute() {
		String text = frame.getTextArea().getText();
		doc.playAll(text);
	}

	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, this command does not need to know if it is a clone or not	
	}
}
