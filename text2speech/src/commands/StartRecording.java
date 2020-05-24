package commands;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "Already recording!", "", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
