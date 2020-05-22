package commands;

import java.awt.event.ActionEvent;

public class RunRecording extends Command{
	
	private CommandManager manager;
	private ReplayStack stack;
	
	public RunRecording(CommandManager manager, ReplayStack stack) {
		this.manager = manager;
		this.stack = stack;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

	@Override
	public void execute() {
		if (!manager.isRecording()) {
			stack.playCommands();
		}
		else {
			//TODO display error message
		}
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
