package main;

import gui.FreeTTSWindow;
import java.awt.EventQueue;


public class Launcher{
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FreeTTSWindow frame = new FreeTTSWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}