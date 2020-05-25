package commands;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * <h1> Help Command </h1> 
 * @author Vasiliki Kanakari
 */

public class Help extends Command{
			
	@Override
	public void actionPerformed(ActionEvent ev) {
		execute();
	}

	@Override
	public void execute() {
		String message = "Here we provide guidelines, concering application's main functionalities.\n\n";
		message += "  FILE\n" + "----------------------------------\n" +
				"New file" + String.format("%10s", ":  ") + "Create a new document by giving it a title and an author.\n" + 
				"Open file..   :  Open an existing document.\n" + 
				"Save" + String.format("%15s", ":  ") + "Saves the contents of the document to disk.\n" + 
				"Save as...    :  Saves the contents of the document to disk into a new document, by providing a particular file name.\n" + 
				"Info" + String.format("%17s", ":  ") + "Displays the document's properties: title, author, creation date and last save date.\n" + 
				"Exit" + String.format("%17s", ":  ") + "Exits the application.\n\n";
		message += "  SPEECH\n" + "----------------------------------\n" + 
				"FreeTTSEditor is able to transform the contents of a document into speech.\n" + 
				"You can also encode the contents of a document based on two different encoding strategies: Rot13 and AtBash,\nand then transform them to speech.\n\n" + 
				"Play all" + String.format("%33s", ":") + " Transforms the whole document to speech.\n" + 
				"Play selected" + String.format("%22s", ":") + " If any text is selected, it is transformed to speech.\n" + 
				"Play all in reverse" + String.format("%14s", ":") + " Transforms the whole document to speech in reverse.\n" + 
				"Play selected in reverse  : If any text is selected, it is transformed to speech in reverse.\n" + 
				"Play all encoded" + String.format("%17s", ":") + " Encodes the whole document to the selected strategy and then transforms it to speech.\n" + 
				"Play selected encoded" + String.format("%6s", ":") + " If any text is selected, it is encoded to the selected strategy and then transformed to speech.\n" + 
				"Preferences" + String.format("%24s", ":") + " Change the speech volume, rate and pitch as well as the encoding strategy (Rot13 and AtBash)\n" +  
				String.format("%47s", " ") + "and the speech library (FreeTTS and FakeTTS).\n\n";
		message += "  ACTION RECORDER\n" + "----------------------------------\n" + 
				"FreeTTSEditor is also able to record a sequence of actions/commands that have been performed \nand re-execute them multiple times.\n\n" + 
				"Start recording  : When pressed, it starts recording the sequence of actions until \"Stop recording\" is pressed.\n" + 
				"Stop recording  : Stops the recording.\n" + 
				"Play recorded    : Re-executes the sequence of actions/commands that have been recorded.";
		JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
