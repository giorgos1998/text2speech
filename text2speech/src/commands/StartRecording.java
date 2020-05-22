package commands;

import java.awt.event.ActionEvent;

public class StartRecording extends Command{
	
	private CommandManager manager;
	
	public StartRecording(CommandManager manager) {
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

	@Override
	public void execute() {
		if (!manager.isRecording()) {
			manager.startRecording();
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
