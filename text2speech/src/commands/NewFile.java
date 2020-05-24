package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

/**
 * <h1> New File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class NewFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	private boolean cloneFlag = false;
	private String author;
	private String title;
	private LocalDateTime creationDate;
	private LocalDateTime lastSaveDate;
	private String openFilePath;
	
	public NewFile(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("NewFileCommand");
		} else {
			execute();
		}
	}

	@Override
	public void execute() {
		if (cloneFlag == false) {
			doc.newFile(frame);
			author = doc.getAuthor();
			title = doc.getTitle();
			creationDate = doc.getCreationDate();
			lastSaveDate = doc.getLastSaveDate();	
			openFilePath = doc.getSaveFilePath();
		}else {					
			doc.openFilePath(frame, openFilePath);
		}		
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}
