package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.FreeTTSWindow;
import model.Document;

/**
 * <h1> Play Selected Reversed Command </h1> 
 * @author Vasiliki Kanakari
 */

public class PlaySelectedReverse implements ActionListener{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public PlaySelectedReverse(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = frame.getTextArea().getSelectedText();
		doc.playSelectedReverse(text);
	}
}
