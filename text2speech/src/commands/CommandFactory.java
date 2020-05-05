package commands;

import java.awt.event.ActionListener;

import gui.FreeTTSWindow;
import model.Document;


public class CommandFactory {
		
	//will need more parameters
	public ActionListener makeCommand(String commandType, FreeTTSWindow frame) {
		Document doc = new Document();
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
			//add more cases
			default:
				//should not reach here
				return null;
		}
	}
}
