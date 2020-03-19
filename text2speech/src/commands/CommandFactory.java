package commands;

import java.awt.event.ActionListener;


public class CommandFactory {
	
	//will need more parameters
	public ActionListener makeCommand(String commandType) {
		switch(commandType) {
		//add more cases
		case "example-command":
			return new CommandExample();
		default:
			//should not reach here
			return null;
		}
	}
}
