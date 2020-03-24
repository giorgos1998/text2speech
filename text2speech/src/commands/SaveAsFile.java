package commands;

import gui.FreeTTSWindow;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;


public class SaveAsFile implements ActionListener{
	
	private FreeTTSWindow frame;
	private LocalDateTime lastSaveDate;
	
	public SaveAsFile(FreeTTSWindow frame) {
		this.frame = frame;
		lastSaveDate = null;
	}
	
	public LocalDateTime getLastSaveDate() {
		return lastSaveDate;
	}
		
	public void actionPerformed(ActionEvent ev) {
		if (frame.getFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				String filePath = frame.getFileChooser().getSelectedFile().getPath();
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
	}
	
}
