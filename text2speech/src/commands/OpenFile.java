package commands;

import gui.FreeTTSWindow;

import java.io.FileReader;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;


public class OpenFile implements ActionListener{
	
	private FreeTTSWindow frame;
	
	public OpenFile(FreeTTSWindow frame) {
		this.frame = frame;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (frame.getFileChooser().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String fileName = frame.getFileChooser().getSelectedFile().getAbsolutePath();
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(fileName);
				frame.getTextArea().read(fileReader, null);
				fileReader.close();
				frame.setTitle(fileName + "   -   FreeTTS Editor");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
