package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
	private boolean successfulSave;
	
	public SaveFile(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (manager.isRecording()) {
			execute();
			if (successfulSave) {
				manager.addClone("SaveFileCommand");
			}
		} else {
			execute();
		}
	}

	@Override
	public void execute() {
		if (cloneFlag == false) {										//if it's not a clone
			if (doc.getAuthor() == null || doc.getTitle() == null) {	//if file is not initialized
				JOptionPane.showMessageDialog(frame, "You must initialize a file before saving! Please copy your content and create a new file.", "", JOptionPane.INFORMATION_MESSAGE);
				successfulSave = false;
			}
			else {														//if file is initialized, it can be saved
				File selectedFile = frame.getFileChooser().getSelectedFile();
				String textToSave = frame.getTextArea().getText();
				if (selectedFile == null) {								//file is initialized, but it's the first time getting saved
					//save new file
					if (frame.getFileChooser().showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
						saveFilePath = frame.getFileChooser().getSelectedFile().getPath();
						doc.saveFile(saveFilePath, textToSave, true);	//the file choice is considered a new file
						frame.setTitle(saveFilePath + "   -   FreeTTS Editor");
						successfulSave = true;
					}
					else {												//user chose cancel in save dialog
						successfulSave = false;
					}
				}
				else {													//file is initialized and has been saved before
					saveFilePath = selectedFile.getPath();
					doc.saveFile(saveFilePath, textToSave, false);
					frame.setTitle(saveFilePath + "   -   FreeTTS Editor");
					successfulSave = true;
				}
			}
		}
		else {															//if it's a clone		
			String textToSave = frame.getTextArea().getText();
			doc.saveFile(saveFilePath, textToSave, false);
			frame.setTitle(saveFilePath + "   -   FreeTTS Editor");
		}		
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}
