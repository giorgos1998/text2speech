package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> Save File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class SaveFile implements ActionListener{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public SaveFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		doc.saveFile(frame);
	}
	
}
