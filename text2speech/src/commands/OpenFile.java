package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

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
		}
		else {
			execute();
		}
	}

	@Override
	public void execute() {		
		if (cloneFlag == true) {
			String textToPrint = doc.openFilePath(openFilePath);
			frame.getTextArea().setText(textToPrint);
			frame.setTitle(openFilePath + "   -   FreeTTS Editor");
		}
		else {
			if (frame.getFileChooser().showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
				String fileName = frame.getFileChooser().getSelectedFile().getAbsolutePath();
				String textToPrint = doc.openFile(fileName);
				frame.getTextArea().setText(textToPrint);
				frame.setTitle(fileName + "   -   FreeTTS Editor");
				openFilePath = doc.getOpenFilePath();
			}
		}
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}
