package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gui.FreeTTSWindow;
import model.Document;

public class PlaySelectedEncoded implements ActionListener{

	private FreeTTSWindow frame;
	private Document doc;
	
	public PlaySelectedEncoded(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = frame.getTextArea().getSelectedText();
		doc.playSelectedEncoded(text);
	}
}
