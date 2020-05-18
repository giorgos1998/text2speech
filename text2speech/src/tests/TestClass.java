package tests;

import static org.junit.Assert.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import main.Launcher;
import model.Document;
import commands.CommandFactory;
import gui.FreeTTSWindow;

public class TestClass {

	@Test
	public void testMain() {
		ActionListener testCommand;
		File testFile = new File("testSaveFile.txt");
		CommandFactory testCommandFactory =  new CommandFactory();
		Document doc;
		Launcher.main(null);
		FreeTTSWindow frame = Launcher.frame;
		
		//test making new file (US1)
		testCommand = testCommandFactory.makeCommand("NewFileCommand", frame );
		testCommand.actionPerformed(null);
		assertEquals("Text should be empty when a new file is made.", "", frame.getTextArea().getText());
		
		//test saving in new file (US3)
		frame.getTextArea().setText("Hello World!");
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
			assertEquals("Text in text area should be the same as in file.", 
					"Hello World!", frame.getTextArea().getText());
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//test "playing sound" with PlayAll command (US5-6)
		/* UFINISHED, needs reference to doc
		frame.getTextArea().setText("Hello World!");
		testFile = new File("FakeTTSApiOut.txt");
		doc.saveSettings(50,21,11,"ROT13","FAKETTS");
		testCommand = testCommandFactory.makeCommand("PlayAllCommand", frame );
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in text area should be the same as in file.", 
					fileScanner.nextLine(), "Hello World!");
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}*/
		
		//test "playing sound" in reverse with PlayAllReverse command (US7-8)
		/* UFINISHED, needs reference to doc
		frame.getTextArea().setText("Hello World!");
		testFile = new File("FakeTTSApiOut.txt");
		//doc.saveSettings(50,21,11,"ROT13","FAKETTS");
		testCommand = testCommandFactory.makeCommand("PlayAllReverse", frame );
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in file should be the reverse of text area.", 
					fileScanner.nextLine(), "World! Hello");
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}*/
		
		//test "playing sound" encoded with PlayAllEncoded command (US9-10)
		/* UFINISHED, needs reference to doc
		frame.getTextArea().setText("Hello World!");
		testFile = new File("FakeTTSApiOut.txt");
		//doc.saveSettings(50,21,11,"ROT13","FAKETTS");
		testCommand = testCommandFactory.makeCommand("PlayAllEncoded", frame );
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in file should be the encoded of text area.", 
					fileScanner.nextLine(), "Uryyb Jbeyq!");
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}*/
		
		//test settings (US11-12)
		frame.setVolumeValue(100);
		assertEquals("Volume in frame should be the same as set.",frame.getVolumeValue(),100);
		frame.setSpeedValue(100);
		assertEquals("Volume in frame should be the same as set.",frame.getSpeedValue(),100);
		frame.setPitchValue(100);
		assertEquals("Volume in frame should be the same as set.",frame.getPitchValue(),100);
		frame.setEncodingStrategy("ATBASH");
		assertEquals("Volume in frame should be the same as set.",frame.getEncodingStrategy(),"ATBASH");
		frame.setSpeechLibrary("FREETTS");
		assertEquals("Volume in frame should be the same as set.",frame.getSpeechLibrary(),"FREETTS");
		
		
	}
	
}
