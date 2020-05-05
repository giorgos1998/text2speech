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
		// TODO
		
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
		
	public void setEncodingStrategy(String strat) {
		encodingStrategy = strat;
	}
	
	public String getEncodingStrategy() {
		return encodingStrategy;
	}
	
}
