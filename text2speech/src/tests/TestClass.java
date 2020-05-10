package tests;

import static org.junit.Assert.*;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import main.Launcher;
import commands.CommandFactory;
import gui.FreeTTSWindow;

public class TestClass {

	@Test
	public void testMain() {
		//Launcher testLauncher = new Launcher();
		ActionListener testCommand;
		Launcher.main(null);
		FreeTTSWindow frame = Launcher.frame;
		CommandFactory testCommandFactory =  new CommandFactory();
		
		testCommand = testCommandFactory.makeCommand("NewFileCommand", frame );
		testCommand.actionPerformed(null);
		assertEquals("Text should be empty when a new file is made.", "", frame.getTextArea().getText());
		
		
		frame.getTextArea().setText("Hello World!");
		testCommand = testCommandFactory.makeCommand("SaveFileCommand", frame );
		testCommand.actionPerformed(null);
		String fileName = frame.getFileChooser().getSelectedFile().getAbsolutePath();
		try {
			FileReader fr = new FileReader(fileName);
			assertEquals("Text in file should be the same as text area.", 
					"Hello World!", null);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
