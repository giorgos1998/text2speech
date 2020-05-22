package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;

/**
 * <h1> Save File As Command </h1> 
 * @author Vasiliki Kanakari
 */

public class SaveAsFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public SaveAsFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

	@Override
	public void execute() {
		doc.saveFileAs(frame);
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
