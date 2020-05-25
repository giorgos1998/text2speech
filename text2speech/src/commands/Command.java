package commands;

import java.awt.event.ActionListener;

/**
 * <h1> Command </h1> 
 * @author Georgios Papadatos
 */

public abstract class Command implements ActionListener, Cloneable{
	
	public abstract void execute();
	
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
	public abstract void setCloneFlag(boolean value);
}
