package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * <h1> Help Command </h1> 
 * @author Vasiliki Kanakari
 */

public class Help implements ActionListener{
			
	@Override
	public void actionPerformed(ActionEvent ev) {
		//TODO modify the message
		String message = "Here we will provide guidelines,\nconcering application's main functionalities.\n\n";
		JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
