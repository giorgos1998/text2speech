package gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.CommandFactory;


public class FreeTTSWindow extends JFrame{
	
	private JTextArea textArea = new JTextArea(10, 10);
	private JFileChooser fileChooser = new JFileChooser();
	private JMenuItem newMenuItem;			// New File
	private JMenuItem openMenuItem;			// Open file...
	private JMenuItem saveMenuItem;			// Save file
	private JMenuItem saveAsMenuItem;		// Save As file...
	private JMenuItem exitMenuItem;			// Exit
	// ADD MORE...
	private JMenuItem preferencesMenuItem;	// Preferences
	private JMenuItem guidelines;			//Guidelines
	private int volumeValue = 50;
	private int speedValue = 50;
	private int pitchValue = 50;
	
	
	public FreeTTSWindow() {
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        
        FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
      	fileChooser.setFileFilter(txtFilter);
                
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new LineBorder(SystemColor.controlHighlight, 20));		
        
        setTitle("FreeTTS Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 435);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/*
		 *  FILE MENU
		 */
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		newMenuItem = new JMenuItem("New file");
		fileMenu.add(newMenuItem);
		
		openMenuItem = new JMenuItem("Open file...");
		fileMenu.add(openMenuItem);
		
		saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		
		saveAsMenuItem = new JMenuItem("Save As...");
		fileMenu.add(saveAsMenuItem);
		
		fileMenu.addSeparator();
		
		exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		/*
		 *  SPEECH MENU 
		 */
		JMenu speechMenu = new JMenu("Speech");
		menuBar.add(speechMenu);
		
		JMenuItem playAllMenuItem = new JMenuItem("Play All");
		speechMenu.add(playAllMenuItem);
		
		JMenuItem playSelectedMenuItem = new JMenuItem("Play Selected");
		speechMenu.add(playSelectedMenuItem);
		
		JMenuItem playAllRevMenuItem = new JMenuItem("Play All in Reverse");
		speechMenu.add(playAllRevMenuItem);
		
		JMenuItem playSelRevMenuItem = new JMenuItem("Play Selected in Reverse");
		speechMenu.add(playSelRevMenuItem);
		
		JMenuItem playAllEncMenuItem = new JMenuItem("Play All Encoded");
		speechMenu.add(playAllEncMenuItem);
		
		JMenuItem playSelEncMenuItem = new JMenuItem("Play Selected Encoded");
		speechMenu.add(playSelEncMenuItem);
		
		speechMenu.addSeparator();
		
		preferencesMenuItem = new JMenuItem("Preferences");
		speechMenu.add(preferencesMenuItem);
		
		/* 
		 *  ACTION READER MENU 
		 */
		JMenu actionReaderMenu = new JMenu("Action Recorder");
		menuBar.add(actionReaderMenu);
		
		JMenuItem startRecMenuItem = new JMenuItem("Start Recording");
		actionReaderMenu.add(startRecMenuItem);
		
		JMenuItem stopRecMenuItem = new JMenuItem("Stop Recording");
		actionReaderMenu.add(stopRecMenuItem);
		
		JMenuItem playRecMenuItem = new JMenuItem("Play Recorded");
		actionReaderMenu.add(playRecMenuItem);
		
		/* 
		 *  HELP MENU 
		 */
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		guidelines = new JMenuItem("Guidelines");
		helpMenu.add(guidelines);
		// TO DO
		
		createListeners();	
	}
		
	public void createListeners() {
		CommandFactory factory = new CommandFactory();
		newMenuItem.addActionListener(factory.makeCommand("NewFileCommand", this));
		openMenuItem.addActionListener(factory.makeCommand("OpenFileCommand", this));
		saveMenuItem.addActionListener(factory.makeCommand("SaveFileCommand", this));
		saveAsMenuItem.addActionListener(factory.makeCommand("SaveAsFileCommand", this));
		exitMenuItem.addActionListener(factory.makeCommand("ExitFileCommand", this));
		// ADD MORE LISTENERS...
		preferencesMenuItem.addActionListener(factory.makeCommand("TuneAudioCommand", this));
		guidelines.addActionListener(factory.makeCommand("HelpCommand", this));
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
	public void setFileChooser(JFileChooser fc) {
		fileChooser = fc;
	}
	
	public void setVolumeValue(int vol) {
		volumeValue = vol;
	}
	
	public int getVolumeValue() {
		return volumeValue;
	}
	
	public void setSpeedValue(int spd) {
		speedValue = spd;
	}
	
	public int getSpeedValue() {
		return speedValue;
	}
	
	public void setPitchValue(int pit) {
		pitchValue = pit;
	}
	
	public int getPitchValue() {
		return pitchValue;
	}
	
}
