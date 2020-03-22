package commands;

import gui.FreeTTSWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class NewFile implements ActionListener{
	
	private FreeTTSWindow frame;
	
	public NewFile(FreeTTSWindow frame) {
		this.frame = frame;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
		frame.setFileChooser(new JFileChooser());
		frame.getFileChooser().setFileFilter(txtFilter);
		try {
			/*
			 * ADD TITLE AND AUTHOR ====================================
			 */
			frame.setTitle("NewFile*	-	FreeTTS Editor");
			frame.getTextArea().setText(null);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
