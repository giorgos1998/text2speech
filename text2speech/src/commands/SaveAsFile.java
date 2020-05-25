package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * <h1> Save File As Command </h1> 
 * @author Vasiliki Kanakari
 * @author Georgios Papadatos
 */

public class SaveAsFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	private boolean cloneFlag = false;
	
	private String saveFilePath;
	
	public SaveAsFile(FreeTTSWindow frame, Document doc, CommandManager manager) {
		this.frame = frame;
		this.doc = doc;
		this.manager = manager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (manager.isRecording()) {
			execute();
			manager.addClone("SaveFileCommand");
		} else {
			execute();
		}
	}

	@Override
	public void execute() {
		if (cloneFlag == false) {										//if it's not a clone
			if (doc.getAuthor() == null || doc.getTitle() == null) {	//if file is not initialized
				JOptionPane.showMessageDialog(frame, "You must initialize a file before saving! Please copy your content and create a new file.", "", JOptionPane.INFORMATION_MESSAGE);
			}
			else {														//if file is initialized, it can be saved
				String textToSave = frame.getTextArea().getText();
				//Pop's up the SaveDialog window for the user to give the file a name, and saves it to a directory.
				if (frame.getFileChooser().showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
					saveFilePath = frame.getFileChooser().getSelectedFile().getPath();
					doc.saveFile(saveFilePath, textToSave, true);		//the file choice is considered a new file
					frame.setTitle(saveFilePath + "   -   FreeTTS Editor");
				}
			}
		}
		else {															//if it's a clone
			String textToSave = frame.getTextArea().getText();
			doc.saveFile(saveFilePath, textToSave, true);				//using "Save As" and not "Save" means save as a new file
			frame.setTitle(saveFilePath + "   -   FreeTTS Editor");
		}		
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}
