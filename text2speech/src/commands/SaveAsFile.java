package commands;

import gui.FreeTTSWindow;

import java.io.FileWriter;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;


public class SaveAsFile implements ActionListener{
	
	private FreeTTSWindow frame;
	
	public SaveAsFile(FreeTTSWindow frame) {
		this.frame = frame;
	}
		
	public void actionPerformed(ActionEvent ev) {
		if (frame.getFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				String filePath = frame.getFileChooser().getSelectedFile().getPath();
				if (!filePath.toLowerCase().endsWith(".txt")) {
					filePath = filePath + ".txt";
				}
				fw = new FileWriter(filePath);
				frame.getTextArea().write(fw);
				fw.close();
				frame.setTitle(filePath + "   -   FreeTTS Editor");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
