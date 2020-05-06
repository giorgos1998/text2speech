package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <h1> Exit Command </h1> 
 * @author Vasiliki Kanakari
 */

public class Exit implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent ev) {
		System.exit(0);
	}
	
}
