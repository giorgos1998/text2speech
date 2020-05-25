package commands;

import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import gui.FreeTTSWindow;
import model.Document;

public class DisplayInfo extends Command{
	
	private FreeTTSWindow frame;
	private Document doc;
	
	public DisplayInfo(FreeTTSWindow frame, Document doc) {
		this.frame = frame;
		this.doc = doc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

	@Override
	public void execute() {
		//TODO get info and send them at the frame
		String author = doc.getAuthor();
		String title = doc.getTitle();
		LocalDateTime creationDate = doc.getCreationDate();
		LocalDateTime lastSaveDate = doc.getLastSaveDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		if (author == null || title == null || creationDate == null) {
			JOptionPane.showMessageDialog(null, "Cannot view file info if you have not created a file!", "", JOptionPane.INFORMATION_MESSAGE);
		}
		else if(lastSaveDate == null){
			frame.openInfoWindow(author, title, creationDate.format(formatter), "Not saved yet");
		}
		else {
			frame.openInfoWindow(author, title, creationDate.format(formatter), lastSaveDate.format(formatter));
		}
	}

	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}

}
