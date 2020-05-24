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
	private CommandManager manager;
	private boolean cloneFlag = false;
	private String openFilePath;
	
	public OpenFile(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("OpenFileCommand");
		} else {
			execute();
		}
	}

	@Override
	public void execute() {		
		if (cloneFlag == true) {
			doc.openFilePath(frame, openFilePath);
		}else {			
			doc.openFile(frame);
			openFilePath = doc.getOpenFilePath();
		}
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}
