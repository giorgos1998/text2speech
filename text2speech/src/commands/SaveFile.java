package commands;

import gui.FreeTTSWindow;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;


public class SaveFile implements ActionListener{
	
	private FreeTTSWindow frame;
	private LocalDateTime lastSaveDate;
	
	public SaveFile(FreeTTSWindow frame) {
		this.frame = frame;
		lastSaveDate = null;
	}
	
	public LocalDateTime getLastSaveDate() {
		return lastSaveDate;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		FileWriter fw = null;
		String filePath;
		// This file does not have a name yet
		// Ask for a name
		if (frame.getFileChooser().getSelectedFile() == null) {
			if (frame.getFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					filePath = frame.getFileChooser().getSelectedFile().getPath();
					if (!filePath.endsWith(".txt")) {
						filePath = filePath + ".txt";
					}
					fw = new FileWriter(filePath);
					frame.getTextArea().write(fw);
					fw.close();
					lastSaveDate = LocalDateTime.now();
					frame.setTitle(filePath + "   -   FreeTTS Editor");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		// This file already exists
		// Change existing file's content
		}else{	
			try {
				filePath = frame.getFileChooser().getSelectedFile().getPath();
				if (!filePath.toLowerCase().endsWith(".txt")) {
					filePath = filePath + ".txt";
				}
				fw = new FileWriter(filePath);
				frame.getTextArea().write(fw);
				fw.close();
				frame.setTitle(filePath + "   -   FreeTTS Editor");	
				lastSaveDate = LocalDateTime.now();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
