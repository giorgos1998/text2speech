package main;

import gui.FreeTTSWindow;
import java.awt.EventQueue;


public class Launcher{
	
	public static FreeTTSWindow frame;
	
	public static void main(String args[]) {
		
		frame = new FreeTTSWindow();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}