package tests;

/**
 * <h1> Test Class </h1> 
 * @author John Rizos
 */


import static org.junit.Assert.*;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import main.Launcher;
import model.Document;
import commands.CommandManager;
import gui.FreeTTSWindow;

public class TestClass {
	
	public Document doc;
	public File testFile = new File("testSaveFile.txt");
	public FreeTTSWindow frame;
	public ActionListener testCommand;
	public CommandManager testCommandFactory;
	
	
	@Test
	public void testFileMakingSavingOpening() {
		
		if(testFile.exists()){								//clear output file
			testFile.delete();
			try {
				testFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Launcher.main(null);								//run and set up
		frame = Launcher.frame;
		testCommandFactory = Launcher.manager;
		
		frame.getFileChooser().setSelectedFile(testFile);	//precondition output file
		
		/**Test case 1
		 * test making new file (US1)
		 * Unfortunately due to how newFile command is made user has to input title
		 * and author for the file before emptying the text area, so jUnit gets the
		 * text area before the command is able to change it to nothing. The test fails
		 * if you set the text area to something else before running this.
		 */
		testCommand = testCommandFactory.getCommand("NewFileCommand");
		testCommand.actionPerformed(null);
		assertEquals("Text should be empty when a new file is made.", "", frame.getTextArea().getText());
		
		
		frame.getTextArea().setText("Hello World!");		//precondition text area
		
		/**Test case 2
		 * test saving in new file (US3)
		 */
		testCommand = testCommandFactory.getCommand("SaveFileCommand");
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
		
		/**Test case 3
		* test opening an existing file (US4)
		*you have to press cancel in file select dialogue or
		*select the correct file (...\text2speech\text2speech\testSaveFile.txt)
		*due to how the openFile command is made
		*/
		testCommand = testCommandFactory.getCommand("OpenFileCommand");
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
	}
	
	@Test
	public void testPlayingSound() {	
		Launcher.main(null);								//run and set up
		frame = Launcher.frame;
		testCommandFactory = Launcher.manager;
		doc = Launcher.doc;
		
		doc.saveSettings(50,21,11,"ROT13","FAKETTS");		//precondition settings
		frame.getTextArea().setText("Hello World!");		//precondition text area
		testFile = new File("FakeTTSApiOut.txt");			//precondition output file
		
		/**Test case 4
		 * test "playing sound" with PlayAll command (US5-6)
		 */
		if(testFile.exists()){								//clear output file (1st time)
			testFile.delete();
			try {
				testFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		testCommand = testCommandFactory.getCommand("PlayAllCommand");
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in text area should be the same as in file.", 
					fileScanner.nextLine(), "Hello World!");
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		/**Test case 5
		 * test "playing sound" in reverse with PlayAllReverse command (US7-8)
		 */
		testFile.delete();									//clear output file
		try {
			testFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		testCommand = testCommandFactory.getCommand("PlayAllReverse");
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in file should be the reverse of text area.", 
					fileScanner.nextLine(), "World! Hello ");
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/**Test case 6
		 * test "playing sound" encoded with PlayAllEncoded command (US9-10)
		 */
		testFile.delete();									//clear output file
		try {
			testFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		testCommand = testCommandFactory.getCommand("PlayAllEncoded");
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in file should be the encoded of text area.", 
					fileScanner.nextLine(), "Uryyb Jbeyq!");
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSettings() {
		Launcher.main(null);
		frame = Launcher.frame;
		
	}
	
	@Test
	public void testReplayCommands() {	/*
		Launcher.main(null);
		frame = Launcher.frame;
		
		ActionListener[] commands = new ActionListener[5];
		commands[0] = testCommandFactory.getCommand("NewFileCommand");
		frame.getTextArea().setText("Hello world replay!");
		frame.getFileChooser().setSelectedFile(testFile);
		commands[1] = testCommandFactory.getCommand("SaveFileCommand");
		commands[2] = testCommandFactory.getCommand("OpenFileCommand");
		commands[3] = testCommandFactory.getCommand("PlayAllCommand");
		commands[3] = testCommandFactory.getCommand("PlayAllCommand");
		frame.setVolumeValue(100);
	*/}
}
