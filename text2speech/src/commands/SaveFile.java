package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

/**
 * <h1> Save File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class SaveFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	private boolean cloneFlag = false;
	private String saveFilePath;
	private LocalDateTime lastSaveDate;
	
	public SaveFile(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("SaveFileCommand");
		} else {
			execute();
		}
	}

	@Override
	public void execute() {
		if (cloneFlag == false) {
			doc.saveFile(frame);
			saveFilePath = doc.getSaveFilePath();
			lastSaveDate = doc.getLastSaveDate();
		} else {			
			doc.saveFilePath(frame, saveFilePath);
		}		
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}
