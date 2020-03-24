package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


public class Help implements ActionListener{
			
	@Override
	public void actionPerformed(ActionEvent ev) {
		String message = "Here we will provide guidelines,\nconcering application's main functionalities.\n\n";
		JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
