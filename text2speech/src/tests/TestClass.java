package tests;

/**
 * <h1> Test Class </h1> 
 * @author John Rizos
 * 
 * Tests US1 to US13
 * 
 * US2 does not need to be tested since we did not implement an EditDocument command
 * all the text is held in the frame textArea until needed when it's taken by the document
 * 
 * US6-8-10 there is no point in testing the playSelected commands since the textArea
 * is sure to return the selected text correctly and then it's played in the TTS like the playAll
 * commands
 * 
 * I used file output from FakeTTS in order to test playing text and settings because checking
 * those would require tweaking the document to either somehow gain access to the FakeTTS object
 * or the doc's private fields.
 * 
 * For all the windows that open you can ignore them, !!! except for the two select file dialogues
 * those block the test so you need to click "cancel" to continue.
 */


import static org.junit.Assert.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import main.Launcher;
import model.Document;
import commands.Command;
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
			assertEquals("Text in text area should be the same as in TTS output file.", 
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
			assertEquals("Text in TTS output file should be the reverse of text area.", 
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
			assertEquals("Text in TTS output file should be the encoded of text area.", 
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
		doc = Launcher.doc;
		testCommandFactory = Launcher.manager;
		
		/**Test case 7
		 * test changing Api settings with doc.saveSettings (US11)
		 * Since the Tune adio command basically calls that document method
		 * with the preferences that have been set by the user, we can directly call
		 * it skipping the ui.
		 */
		
		frame.getTextArea().setText("Hello World!");		//precondition text area
		testFile = new File("FakeTTSApiOut.txt");			//precondition output file

		if(testFile.exists()){								//clear output file (1st time)
			testFile.delete();
			try {
				testFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		doc.saveSettings(100,42,69,"ROT13","FAKETTS");		
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Settings in TTS output file should be the same as set.", 
					fileScanner.nextLine(), "100 69 42");   //arg rate comes before pitch in the doc.saveSettings method
			fileScanner.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		/**Test case 8
		 * test changing encoding setting (US12)
		 */
		testFile.delete();									//clear output file
		try {
			testFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		doc.saveSettings(50,21,11,"ATBASH","FAKETTS");	
		testCommand = testCommandFactory.getCommand("PlayAllEncoded");
		testCommand.actionPerformed(null);
		try {
			Scanner fileScanner = new Scanner(testFile);
			assertEquals("Text in TTS output file should be the encoded (ATBASH)  of text area.", 
					fileScanner.nextLine(), "Svool Dliow!");
			fileScanner.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReplay() {
		Launcher.main(null);
		frame = Launcher.frame;
		doc = Launcher.doc;
		testCommandFactory = Launcher.manager;
		
		/**Test case 9
		 * test replaying commands (US13)
		 * I will record some commands and then check the stack to
		 * see if the commands were recorded. Testing from the command
		 * results is impossible since I can't assert anything inbetween 
		 * the commands being replayed -I can only assert the results of
		 * the last one.
		 */
		testCommandFactory.startRecording();
		testCommand = testCommandFactory.getCommand("OpenFileCommand");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("SaveFileCommand");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("SaveAsFileCommand");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("PlayAllCommand");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("PlaySelectedCommand");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("PlayAllReverse");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("PlaySelectedReverse");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("PlayAllEncoded");
		testCommand.actionPerformed(null);
		testCommand = testCommandFactory.getCommand("PlaySelectedEncoded");
		testCommand.actionPerformed(null);
		//testCommand = testCommandFactory.getCommand("TuneAudioCommand");	//this crashes in testing because  
		//testCommand.actionPerformed(null);								//the preferences window is not open
		testCommandFactory.stopRecording();
		
		ArrayList<Command> recording = testCommandFactory.getStack();
		String [] expectedRecording = new String[] {"commands.OpenFile",
													"commands.SaveFile",
													"commands.SaveAsFile",
													"commands.PlayAll",
													"commands.PlaySelected",
													"commands.PlayAllReverse",
													"commands.PlaySelectedReverse",
													"commands.PlayAllEncoded",
													"commands.PlaySelectedEncoded"};

		for(int i = 0;i<recording.size();i++) {
			assertEquals("The class name should be the recorded class name.",
					recording.get(i).getClass().getName(),expectedRecording[i]);
		}
	}
}
