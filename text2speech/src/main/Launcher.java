package main;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.EventQueue;

import commands.CommandManager;


public class Launcher{
	
	public static FreeTTSWindow frame;
	
	public static void main(String args[]) {
		
		frame = new FreeTTSWindow();
		Document doc = new Document();
		CommandManager manager = new CommandManager();
		manager.loadCache(frame, doc);
		
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