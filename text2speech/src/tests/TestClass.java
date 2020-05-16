package tests;

import static org.junit.Assert.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import main.Launcher;
import commands.CommandFactory;
import gui.FreeTTSWindow;

public class TestClass {

	@Test
	public void testMain() {
		ActionListener testCommand;
		Launcher.main(null);
		FreeTTSWindow frame = Launcher.frame;
		CommandFactory testCommandFactory =  new CommandFactory();
		
		
		//test making new file (US1)
		testCommand = testCommandFactory.makeCommand("NewFileCommand", frame );
		testCommand.actionPerformed(null);
		assertEquals("Text should be empty when a new file is made.", "", frame.getTextArea().getText());
		
		//test saving in new file (US3)
		frame.getTextArea().setText("Hello World!");
		File testFile = new File("testSaveFile.txt");
		frame.getFileChooser().setSelectedFile(testFile);
		testCommand = testCommandFactory.makeCommand("SaveFileCommand", frame );
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in file should be the same as text area.", 
					"Hello World!", fileScanner.nextLine());
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//test opening an existing file (US4)
		//you have to select the correct file -.-
		//(git repo-project folder)...\text2speech\text2speech\testSaveFile.txt
		testCommand = testCommandFactory.makeCommand("OpenFileCommand", frame );
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in text area should be the same as in file .", 
					"Hello World!", frame.getTextArea().getText());
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//test "playing sound" with play all command
		/* UNFINISHED
		frame.getTextArea().setText("Hello World!");
		testCommand = testCommandFactory.makeCommand("TuneAudioCommand", frame );
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.makeCommand("PlayAllCommand", frame );
		testCommand.actionPerformed(null);
		testFile = new File("FakeTTSApiOut.txt");
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in text area should be the same as in file .", 
					fileScanner.nextLine(), frame.getTextArea().getText());
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}*/
		
		
	}

}
