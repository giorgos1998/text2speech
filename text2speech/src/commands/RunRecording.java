package commands;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

/**
 * <h1> Run Recording </h1> 
 * @author Georgios Papadatos
 */

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
		if (stack.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Replay stack is empty!", "", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			if (!manager.isRecording()) {
				stack.playCommands();
			}
			else {
				JOptionPane.showMessageDialog(null, "Stop recording to replay commands!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
