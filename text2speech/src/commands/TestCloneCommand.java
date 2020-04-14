package commands;

import java.awt.event.ActionEvent;

public class TestCloneCommand extends Command{
	
	private String text;
	private int num = 0;
	
	public TestCloneCommand(String text) {
		this.text = text;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void execute() {
		System.out.println("execute() running...");
		System.out.println("Text string: " + text);
		System.out.println("Test number: " + num);
	}

	@Override
	public void setNum(int num) {
		this.num = num;	
	}
}
