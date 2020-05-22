package commands;

import java.awt.event.ActionEvent;

/**
 * <h1> Exit Command </h1> 
 * @author Vasiliki Kanakari
 */

public class Exit extends Command{

	@Override
	public void actionPerformed(ActionEvent ev) {
		execute();
	}

	@Override
	public void execute() {
		System.exit(0);
	}

	@Override
	public void setCloneFlag(boolean value) {
		//do nothing, cannot be cloned
	}
}
