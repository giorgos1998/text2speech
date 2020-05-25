package main;

import gui.FreeTTSWindow;
import model.Document;

import java.awt.EventQueue;

import commands.CommandManager;

/**
 * <h1> Launcher </h1> 
 * The application's launcher.
 * @author Georgios Papadatos
 */

public class Launcher{
	
	public static FreeTTSWindow frame;     //for testing
	public static CommandManager manager;  //for testing
	public static Document doc;  		   //for testing
	
	public static void main(String args[]) {
		
		frame = new FreeTTSWindow();
		doc = new Document();
		manager = new CommandManager();
		manager.loadCache(frame, doc);
		frame.createListeners(manager);;
		
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