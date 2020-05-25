package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * <h1> New File Command </h1> 
 * @author Vasiliki Kanakari
 * @author Georgios Papadatos
 */

public class NewFile extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	private CommandManager manager;
	private boolean cloneFlag = false;
	
	private String author;
	private String title;
	private String returnText;
	
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
		}
		else {
			execute();
		}
	}

	@Override
	public void execute() {
		if (cloneFlag == false) {								//if it isn't a clone
			String localAuthor = frame.getAuthorTextField();	//just in case user didn't input correct info
			String localTitle = frame.getTitleTextField();
			returnText = doc.newFile(localAuthor, localTitle);
			if (returnText.equals("success")) {					//everything ok to initialize new file
				frame.closeNewFileWindow();
				frame.getTextArea().setText("");
				FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
				frame.setFileChooser(new JFileChooser());
				frame.getFileChooser().setFileFilter(txtFilter);
				frame.setTitle("NewFile*   -   FreeTTS Editor");
				author = localAuthor;							//save working info
				title = localTitle;
			}
			else {												//some info missing to initialize new file
				JOptionPane.showMessageDialog(null, returnText, "", JOptionPane.INFORMATION_MESSAGE);
			}
		}else {													//if it's a clone
			doc.newFileAuto(title, author);
			frame.getTextArea().setText("");
			FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
			frame.setFileChooser(new JFileChooser());
			frame.getFileChooser().setFileFilter(txtFilter);
			frame.setTitle("NewFile*   -   FreeTTS Editor");
		}
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		cloneFlag = value;
	}
}
