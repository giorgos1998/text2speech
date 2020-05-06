package commands;

import java.awt.event.ActionListener;

import gui.FreeTTSWindow;
import model.Document;

/**
 * <h1> Command Factory </h1> 
 * @author Vasiliki Kanakari
 */

public class CommandFactory {
	
	private Document doc = new Document();
	
	/**
	 * Creates the commands.
	 * @param commandType The type of command requested
	 * @param frame The application's main frame
	 * @return ActionListener Returns either the action listener for the requested 
	 * command or null in case of error.
	 */
	public ActionListener makeCommand(String commandType, FreeTTSWindow frame) {
		switch(commandType) {
			case "NewFileCommand":
				return new NewFile(frame, doc);
			case "OpenFileCommand":
				return new OpenFile(frame, doc);
			case "SaveFileCommand":
				return new SaveFile(frame, doc);
			case "SaveAsFileCommand":
				return new SaveAsFile(frame, doc);
			case "ExitCommand":
				return new Exit();
			case "TuneAudioCommand":
				return new TuneAudio(frame, doc);
			case "HelpCommand":
				return new Help();
			case "PlayAllCommand":
				return new PlayAll(frame, doc);
			case "PlaySelectedCommand":
				return new PlaySelected(frame, doc);
			case "PlayAllReverse":
				return new PlayAllReverse(frame, doc);
			case "PlaySelectedReverse":
				return new PlaySelectedReverse(frame, doc);
			case "PlayAllEncoded":
				return new PlayAllEncoded(frame, doc);
			case "PlaySelectedEncoded":
				return new PlaySelectedEncoded(frame, doc);
			//TODO add more cases
			default:
				//should not reach here
				return null;
		}
	}
}
