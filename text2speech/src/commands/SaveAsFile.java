package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> Save File As Command </h1> 
 * @author Vasiliki Kanakari
 */

public class SaveAsFile implements ActionListener{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public SaveAsFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		doc.saveFileAs(frame);
	}
	
}
