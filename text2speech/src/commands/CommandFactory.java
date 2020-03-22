package commands;

import java.awt.event.ActionListener;

import gui.FreeTTSWindow;


public class CommandFactory {
		
	//will need more parameters
	public ActionListener makeCommand(String commandType, FreeTTSWindow frame) {
			switch(commandType) {
				//add more cases
				case "NewFileCommand":
					return new NewFile(frame);
				case "OpenFileCommand":
					return new OpenFile(frame);
				case "SaveFileCommand":
					return new SaveFile(frame);
				case "SaveAsFileCommand":
					return new SaveAsFile(frame);
				default:
					//should not reach here
					return null;
		}
	}
}
