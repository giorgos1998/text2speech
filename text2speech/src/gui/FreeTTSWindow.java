package gui;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.CommandManager;

/**
 * <h1> FreeTTS Main Window </h1>
 * 
 * The main application window
 * Creates the menus and the menu items and then creates
 * the action listeners for each one of them
 *  
 * @author Vasiliki Kanakari
 */

@SuppressWarnings("serial")
public class FreeTTSWindow extends JFrame{
	
	private JTextArea textArea = new JTextArea(10, 10);
	private JFileChooser fileChooser = new JFileChooser();
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem infoMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem playAllMenuItem;
	private JMenuItem playSelectedMenuItem;
	private JMenuItem playAllRevMenuItem;
	private JMenuItem playSelRevMenuItem;
	private JMenuItem playAllEncMenuItem;
	private JMenuItem playSelEncMenuItem;
	private JMenuItem startRecMenuItem;
	private JMenuItem stopRecMenuItem;
	private JMenuItem playRecMenuItem;
	private JMenuItem preferencesMenuItem;
	private JMenuItem guidelines;
	
	//default values
	private int volumeValue = 50;
	private int speedValue = 21;
	private int pitchValue = 11;
	private String encodingStrategy = "ROT13";
	private String speechLibrary = "FREETTS";
	
	private CommandManager manager;
	
	//Preferences menu
	private JFrame preferencesFrame;
	private JSlider volumeSlider;
	private JSlider speedSlider;
	private JSlider pitchSlider;
	private JComboBox<String> strategyBox;
	private JComboBox<String> libraryBox;
	
	//Info menu
	private JFrame infoFrame;

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
		
		infoMenuItem = new JMenuItem("Info");
		fileMenu.add(infoMenuItem);
		
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
		 *  ACTION RECORDER MENU 
		 */
		JMenu actionReaderMenu = new JMenu("Action Recorder");
		menuBar.add(actionReaderMenu);
		
		startRecMenuItem = new JMenuItem("Start Recording");
		actionReaderMenu.add(startRecMenuItem);
		
		stopRecMenuItem = new JMenuItem("Stop Recording");
		actionReaderMenu.add(stopRecMenuItem);
		
		playRecMenuItem = new JMenuItem("Play Recorded");
		actionReaderMenu.add(playRecMenuItem);
		
		/* 
		 *  HELP MENU 
		 */
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		guidelines = new JMenuItem("Guidelines");
		helpMenu.add(guidelines);
				
		/*
		 * 	LINE TESTING STUFF
		 * 
		JButton countLines = new JButton("Count lines");
		countLines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				int lines = textArea.getLineCount();
				textArea.append("\n\nNo. of lines: " + lines);
			}
		});
		menuBar.add(countLines);
		*/
		
	}
	
	/** 
	 * Creates an action listener for each and every menu item
	 */		
	public void createListeners(CommandManager manager) {
		this.manager = manager;
		newMenuItem.addActionListener(manager.getCommand("NewFileCommand"));
		openMenuItem.addActionListener(manager.getCommand("OpenFileCommand"));
		saveMenuItem.addActionListener(manager.getCommand("SaveFileCommand"));
		saveAsMenuItem.addActionListener(manager.getCommand("SaveAsFileCommand"));
		exitMenuItem.addActionListener(manager.getCommand("ExitCommand"));
		playAllMenuItem.addActionListener(manager.getCommand("PlayAllCommand"));
		playSelectedMenuItem.addActionListener(manager.getCommand("PlaySelectedCommand"));
		playAllRevMenuItem.addActionListener(manager.getCommand("PlayAllReverse"));
		playSelRevMenuItem.addActionListener(manager.getCommand("PlaySelectedReverse"));
		playAllEncMenuItem.addActionListener(manager.getCommand("PlayAllEncoded"));
		playSelEncMenuItem.addActionListener(manager.getCommand("PlaySelectedEncoded"));
		startRecMenuItem.addActionListener(manager.getCommand("StartRecording"));
		stopRecMenuItem.addActionListener(manager.getCommand("StopRecording"));
		playRecMenuItem.addActionListener(manager.getCommand("RunRecording"));
		preferencesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				openPreferencesWindow();
			}
		});
		guidelines.addActionListener(manager.getCommand("HelpCommand"));
		infoMenuItem.addActionListener(manager.getCommand("DisplayInfo"));
	}
	
	/**
	 * Creates preferences window
	 */
	private void openPreferencesWindow() {
		preferencesFrame = new JFrame();
		preferencesFrame.setTitle("Preferences");
		preferencesFrame.setBounds(400, 200, 482, 435);
		preferencesFrame.setResizable(false);
		preferencesFrame.setVisible(true);
						
		JLabel volumeLabel = new JLabel("Volume");		
		JLabel speedLabel = new JLabel("Speed");	
		JLabel pitchLabel = new JLabel("Pitch");
		JLabel encodingStrategyLabel = new JLabel("Encoding Strategy");
		JLabel speechLibrarylabel = new JLabel("Speech Library");
		
		volumeSlider = new JSlider();		
		volumeSlider.setValue(volumeValue);
		volumeSlider.setBorder(new LineBorder(SystemColor.activeCaption));	
		
		speedSlider = new JSlider();
		speedSlider.setValue(speedValue);
		speedSlider.setBorder(new LineBorder(SystemColor.activeCaption));		
		
		pitchSlider = new JSlider();
		pitchSlider.setValue(pitchValue);
		pitchSlider.setBorder(new LineBorder(SystemColor.activeCaption));			
		
		strategyBox = new JComboBox<String>();
		strategyBox.addItem("ROT13");
		strategyBox.addItem("ATBASH");
		strategyBox.setSelectedItem(encodingStrategy);
		
		libraryBox = new JComboBox<String>();
		libraryBox.addItem("FREETTS");
		libraryBox.addItem("FAKETTS");
		libraryBox.setSelectedItem(speechLibrary);
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(manager.getCommand("TuneAudioCommand"));
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				preferencesFrame.dispose();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(preferencesFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap(307, Short.MAX_VALUE)
				.addComponent(applyButton).addGap(18).addComponent(cancelButton).addGap(33))
				.addGroup(groupLayout.createSequentialGroup().addGap(50).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(speechLibrarylabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(libraryBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(encodingStrategyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGap(18).addComponent(strategyBox, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(pitchLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(pitchSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(speedLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
				.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(ComponentPlacement.RELATED))).addContainerGap(150, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(42).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(33).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(speedSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(speedLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(31).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pitchSlider, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
				.addComponent(pitchLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(29).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(strategyBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(encodingStrategyLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(libraryBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(speechLibrarylabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
				.addGap(29).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(cancelButton)
				.addComponent(applyButton)).addGap(29))
		);
		preferencesFrame.getContentPane().setLayout(groupLayout);
	}
	
	/**
	 * A frame that contains all the information of the file: title, author, creation date and last save date
	 * 
	 * @param author
	 * @param title
	 * @param creationDate
	 * @param lastSaveDate
	 */
	public void openInfoWindow(String author, String title, LocalDateTime creationDate, LocalDateTime lastSaveDate) {
		infoFrame = new JFrame();
		infoFrame.setTitle("File Info");
		infoFrame.setBounds(300, 250, 350, 300);
		infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		infoFrame.setResizable(false);
		infoFrame.setVisible(true);;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		JLabel titleLabel = new JLabel("Title: " + title);
		JLabel authorLabel = new JLabel("Author: " + author);	
		JLabel creationDateLabel = new JLabel("Creation Date: " + creationDate.format(formatter));
		JLabel lastSaveDateLabel = new JLabel("Last Save Date: " + lastSaveDate.format(formatter));
		
		JButton cancelButton = new JButton("Close");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				infoFrame.dispose();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(infoFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(46).addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
				.addComponent(creationDateLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(authorLabel, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE).addComponent(lastSaveDateLabel)
				.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap(316, Short.MAX_VALUE)).addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
				.addContainerGap(300, Short.MAX_VALUE).addComponent(cancelButton).addGap(51)));
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(49).addComponent(titleLabel).addGap(27)
				.addComponent(authorLabel).addGap(31).addComponent(creationDateLabel).addGap(29).addComponent(lastSaveDateLabel)
				.addGap(18).addComponent(cancelButton).addContainerGap(36, Short.MAX_VALUE)));
		infoFrame.getContentPane().setLayout(groupLayout);
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
	 * @return int The current volume value the user chose.
	 */
	public int getVolumeValue() {
		return volumeSlider.getValue();
	}
	
	/**
	 * Sets the speed value of the speech.
	 * @param spd The value to be set to speed.
	 */
	public void setSpeedValue(int spd) {
		speedValue = spd;
	}
	
	/**
	 * @return int The current speed value the user chose.
	 */
	public int getSpeedValue() {
		return speedSlider.getValue();
	}
	
	/**
	 * Sets the pitch value of the speech.
	 * @param pit The value to be set to pitch.
	 */
	public void setPitchValue(int pit) {
		pitchValue = pit;
	}
	
	/**
	 * @return int The current pitch value the user chose.
	 */
	public int getPitchValue() {
		return pitchSlider.getValue();
	}
		
	/**
	 * Sets the encoding strategy of the speech.
	 * @param strat The value to be set to encoding strategy.
	 */
	public void setEncodingStrategy(String strat) {
		encodingStrategy = strat;
	}
	
	/**
	 * @return String The current encoding strategy the user chose.
	 */
	public String getEncodingStrategy() {
		return String.valueOf(strategyBox.getSelectedItem());
	}
	
	/**
	 * Sets the speech library of the speech.
	 * @param strat The value to be set to speech library.
	 */
	public void setSpeechLibrary(String lib) {
		speechLibrary = lib;
	}
	
	/**
	 * @return String The current speech library the user chose.
	 */
	public String getSpeechLibrary() {
		return String.valueOf(libraryBox.getSelectedItem());
	}
	
	public void closePreferencesWindow() {
		preferencesFrame.dispose();
	}
	
}
