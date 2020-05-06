package gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.CommandFactory;

/**
 * <h1> FreeTTS Main Window </h1>
 * 
 * The main application window
 * Creates the menus and the menu items and then creates
 * the action listeners for each one of them
 *  
 * @author Vasiliki Kanakari
 */

public class FreeTTSWindow extends JFrame{
	
	private JTextArea textArea = new JTextArea(10, 10);
	private JFileChooser fileChooser = new JFileChooser();
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem playAllMenuItem;
	private JMenuItem playSelectedMenuItem;
	private JMenuItem playAllRevMenuItem;
	private JMenuItem playSelRevMenuItem;
	private JMenuItem playAllEncMenuItem;
	private JMenuItem playSelEncMenuItem;
	// ADD MORE...
	private JMenuItem preferencesMenuItem;
	private JMenuItem guidelines;
	private int volumeValue = 50;
	private int speedValue = 21;
	private int pitchValue = 11;
	private String encodingStrategy = "";
	
	/**
	 * Creates the main frame and sets its title,
	 * the menus and their menu items,
	 * a scroll bar and a text area
	 */
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
		
		playAllMenuItem = new JMenuItem("Play All");
		speechMenu.add(playAllMenuItem);
		
		playSelectedMenuItem = new JMenuItem("Play Selected");
		speechMenu.add(playSelectedMenuItem);
		
		playAllRevMenuItem = new JMenuItem("Play All in Reverse");
		speechMenu.add(playAllRevMenuItem);
		
		playSelRevMenuItem = new JMenuItem("Play Selected in Reverse");
		speechMenu.add(playSelRevMenuItem);
		
		playAllEncMenuItem = new JMenuItem("Play All Encoded");
		speechMenu.add(playAllEncMenuItem);
		
		playSelEncMenuItem = new JMenuItem("Play Selected Encoded");
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
		
		createListeners();
		
		/*
		 * 	LINE TESTING STUFF
		 */
		JButton countLines = new JButton("Count lines");
		countLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int lines = textArea.getLineCount();
				textArea.append("\n\nNo. of lines: " + lines);
			}
		});
		menuBar.add(countLines);
	}
	
	/** 
	 * Creates an action listener for each and every menu item
	 */		
	public void createListeners() {
		CommandFactory factory = new CommandFactory();
		newMenuItem.addActionListener(factory.makeCommand("NewFileCommand", this));
		openMenuItem.addActionListener(factory.makeCommand("OpenFileCommand", this));
		saveMenuItem.addActionListener(factory.makeCommand("SaveFileCommand", this));
		saveAsMenuItem.addActionListener(factory.makeCommand("SaveAsFileCommand", this));
		exitMenuItem.addActionListener(factory.makeCommand("ExitCommand", this));
		playAllMenuItem.addActionListener(factory.makeCommand("PlayAllCommand", this));
		playSelectedMenuItem.addActionListener(factory.makeCommand("PlaySelectedCommand", this));
		playAllRevMenuItem.addActionListener(factory.makeCommand("PlayAllReverse", this));
		playSelRevMenuItem.addActionListener(factory.makeCommand("PlaySelectedReverse", this));
		playAllEncMenuItem.addActionListener(factory.makeCommand("PlayAllEncoded", this));
		playSelEncMenuItem.addActionListener(factory.makeCommand("PlaySelectedEncoded", this));
		//TODO ADD MORE LISTENERS...
		preferencesMenuItem.addActionListener(factory.makeCommand("TuneAudioCommand", this));
		guidelines.addActionListener(factory.makeCommand("HelpCommand", this));
	}
	
	/**
	 * @return JTextArea The text area of the application.
	 */
	public JTextArea getTextArea() {
		return textArea;
	}
	
	/**
	 * @return JFileChooser The file Chooser.
	 */
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
	/**
	 * Sets the file chooser value.
	 * @param fc The value to be set to file chooser.
	 */
	public void setFileChooser(JFileChooser fc) {
		fileChooser = fc;
	}
	
	/**
	 * Sets the volume value of the speech.
	 * @param vol The value to be set to volume.
	 */
	public void setVolumeValue(int vol) {
		volumeValue = vol;
	}
	
	/**
	 * @return int The current volume value the application uses.
	 */
	public int getVolumeValue() {
		return volumeValue;
	}
	
	/**
	 * Sets the speed value of the speech.
	 * @param spd The value to be set to speed.
	 */
	public void setSpeedValue(int spd) {
		speedValue = spd;
	}
	
	/**
	 * @return int The current speed value the application uses.
	 */
	public int getSpeedValue() {
		return speedValue;
	}
	
	/**
	 * Sets the pitch value of the speech.
	 * @param pit The value to be set to pitch.
	 */
	public void setPitchValue(int pit) {
		pitchValue = pit;
	}
	
	/**
	 * @return int The current pitch value the application uses.
	 */
	public int getPitchValue() {
		return pitchValue;
	}
		
	/**
	 * Sets the encoding strategy of the speech.
	 * @param strat The value to be set to encoding strategy.
	 */
	public void setEncodingStrategy(String strat) {
		encodingStrategy = strat;
	}
	
	/**
	 * @return String The current encoding strategy the application uses.
	 */
	public String getEncodingStrategy() {
		return encodingStrategy;
	}
	
}
