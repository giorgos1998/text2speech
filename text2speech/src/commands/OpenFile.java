package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;

/**
 * <h1> Open File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class OpenFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public OpenFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		execute();
	}

	@Override
	public void execute() {
		doc.openFile(frame);
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
