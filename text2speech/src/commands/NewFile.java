package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> New File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class NewFile implements ActionListener{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public NewFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		doc.newFile(frame);
	}
	
}
