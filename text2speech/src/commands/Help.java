package commands;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * <h1> Help Command </h1> 
 * @author Vasiliki Kanakari
 */

public class Help extends Command{
			
	@Override
	public void actionPerformed(ActionEvent ev) {
		execute();
	}

	@Override
	public void execute() {
		//TODO modify the message
		String message = "Here we will provide guidelines,\nconcering application's main functionalities.\n\n";
		JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
