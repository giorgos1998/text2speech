package commands;

import java.util.Hashtable;

public class CommandCache {

	private Hashtable<String, Command> commandMap = new Hashtable<String, Command>();
	
	public Command getCommand(String commandType, boolean makeClone) {
		Command cachedCommand = commandMap.get(commandType);
		if(makeClone) {
			return (Command) cachedCommand.clone();
		}
		else {
			return cachedCommand;
		}
	}
	
	public void loadCache() {
		TestCloneCommand testCommand = new TestCloneCommand("some text");
		testCommand.setNum(10);
		commandMap.put("TESTCOMMAND", testCommand);
	}
}
