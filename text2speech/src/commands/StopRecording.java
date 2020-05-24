package commands;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class StopRecording extends Command{
	
	private CommandManager manager;
	
	public StopRecording(CommandManager manager) {
		this.manager = manager;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		execute();
	}

	@Override
	public void execute() {
		if (manager.isRecording()) {
			manager.stopRecording();
		}
		else {
			JOptionPane.showMessageDialog(null, "Recording has not started yet!", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
