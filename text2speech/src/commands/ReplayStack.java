package commands;

import java.util.ArrayList;

/**
 * <h1> Replay Stack </h1> 
 * @author John Rizos
 */


public class ReplayStack {
	
	private ArrayList<Command> stack;	//FIFO stack for commands
	
	public ReplayStack() {
		stack = new ArrayList<Command>();
	}
	
	/**
	 * adds a command to the tail of the stack
	 * @param clone Command to be replayed
	 */
	public void addToStack(Command clone) {
		stack.add(clone);
	}
	
	/**
	 * plays all commands from head to tail,
	 * then empties the stack for next command recording
	 */
	public void playCommands() {
		for(Command cmd:stack) {
			try {
				cmd.execute();
			}catch(Exception e) {
				continue;
			}
		}
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public ArrayList<Command> getStack() {		//for testing
		return stack;
	}
	
}
