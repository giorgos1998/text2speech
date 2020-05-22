package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;

/**
 * <h1> New File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class NewFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public NewFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		execute();
	}

	@Override
	public void execute() {
		doc.newFile(frame);
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
