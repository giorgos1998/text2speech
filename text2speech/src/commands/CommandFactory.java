package commands;

import java.awt.event.ActionListener;

import gui.FreeTTSWindow;


public class CommandFactory {
		
	//will need more parameters
	public ActionListener makeCommand(String commandType, FreeTTSWindow frame) {
		switch(commandType) {
			case "NewFileCommand":
				return new NewFile(frame);
			case "OpenFileCommand":
				return new OpenFile(frame);
			case "SaveFileCommand":
				return new SaveFile(frame);
			case "SaveAsFileCommand":
				return new SaveAsFile(frame);
			case "ExitFileCommand":
				return new Exit();
			case "TuneAudioCommand":
				return new TuneAudio(frame);
			case "HelpCommand":
				return new Help();
			//add more cases
			default:
				//should not reach here
				return null;
		}
	}
}
