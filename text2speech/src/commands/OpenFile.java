package commands;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> Open File Command </h1> 
 * @author Vasiliki Kanakari
 */

public class OpenFile implements ActionListener{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public OpenFile(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		doc.openFile(frame);
	}
	
}
