package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Exit implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent ev) {
		System.exit(0);
	}
	
}
