package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;

/**
 * <h1> Save File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class SaveFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public SaveFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		execute();
	}

	@Override
	public void execute() {
		doc.saveFile(frame);
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
