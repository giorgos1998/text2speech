package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.FreeTTSWindow;
import model.encoding.EncStrategy;
import model.encoding.EncStrategyFactory;
import model.textToSpeechAPI.TextToSpeechApi;
import model.textToSpeechAPI.TextToSpeechApiFactory;

public class Document {
	
	private ArrayList<Line> contents;
	private DocumentGenerator docGenerator;
	private TextToSpeechApi speechAPI;
	private TextToSpeechApiFactory speechFactory;
	private EncStrategy encoder;
	private EncStrategyFactory encoderFactory;
	private String fileAuthor;
	private String fileTitle;
	private LocalDateTime creationDate;
	private LocalDateTime lastSaveDate;
	//maybe more
	
	public Document() {
		speechFactory = new TextToSpeechApiFactory();
		speechAPI = speechFactory.createSpeechApi("FREETTS");	//default value
		encoderFactory = new EncStrategyFactory();
		encoder = encoderFactory.createStrategy("ROT13");
		docGenerator = new DocumentGenerator();
		//fileAuthor = null;
		//fileTitle = null;
		//creationDate = null;
		//lastSaveDate = null;
	}
	
	public void newFile(FreeTTSWindow frame) {
		//initialize preferences window 
		frame.setVolumeValue(50);
		frame.setSpeedValue(21);
		frame.setPitchValue(11);
		frame.setEncodingStrategy("");
				
		// Pop's up a window that requests the user to give file's title and author
		try {
			JFrame newFileFrame = new JFrame();	
			newFileFrame.setTitle("New File");
			newFileFrame.setBounds(400, 200, 482, 245);
			newFileFrame.setResizable(false);
			newFileFrame.setVisible(true);
			
			JLabel titleLabel = new JLabel("Title");
			JLabel authorLabel = new JLabel("Author");
			
			JTextField titleTextField = new JTextField();
			titleTextField.setColumns(10);
			
			JTextField authorTextField = new JTextField();
			authorTextField.setColumns(10);
					
			JButton createButton = new JButton("Create");
			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					String message;
					fileAuthor = authorTextField.getText().trim();
					fileTitle = titleTextField.getText().trim();
					
					// Make sure user gave title and author
					if (fileAuthor.length() == 0 && fileTitle.length() == 0) {
						message = "You must give the file a Title and an Author!";
					}else if (fileTitle.length() == 0) {
						message = "You must give the file a Title!";
					}else if (fileAuthor.length() == 0) {
						message = "You must give the file an Author!";
					// If user gave both title and author close newFileWindow
					}else {
						creationDate = LocalDateTime.now();
						newFileFrame.dispose();
						FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
						frame.setFileChooser(new JFileChooser());
						frame.getFileChooser().setFileFilter(txtFilter);
						frame.setTitle("NewFile*   -   FreeTTS Editor");
						frame.getTextArea().setText(null);
						return;
					}
					JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					newFileFrame.dispose();
				}
			});
			
			GroupLayout groupLayout = new GroupLayout(newFileFrame.getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap().addComponent(createButton).addGap(31).addComponent(cancelButton)
						.addGap(28)).addGroup(groupLayout.createSequentialGroup().addGap(75)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(authorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
						.addGap(27).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorTextField, 217, 217, 217)))).addContainerGap(114, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addGap(41).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(26).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(authorTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(26).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(createButton).addComponent(cancelButton)).addContainerGap())
			);
			newFileFrame.getContentPane().setLayout(groupLayout);			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void openFile(FreeTTSWindow frame) { 
		//initialize preferences window
		frame.setVolumeValue(50);
		frame.setSpeedValue(21);
		frame.setPitchValue(11);
		frame.setEncodingStrategy("");
		
		if (frame.getFileChooser().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String fileName = frame.getFileChooser().getSelectedFile().getAbsolutePath();
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(fileName);
				frame.getTextArea().read(fileReader, null);
				fileReader.close();
				frame.setTitle(fileName + "   -   FreeTTS Editor");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveFile(FreeTTSWindow frame) {
		FileWriter fw = null;
		String filePath;
		// This file does not have a name yet
		// Ask for a name
		if (frame.getFileChooser().getSelectedFile() == null) {
			if (frame.getFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				try {
					filePath = frame.getFileChooser().getSelectedFile().getPath();
					if (!filePath.endsWith(".txt")) {
						filePath = filePath + ".txt";
					}
					fw = new FileWriter(filePath);
					frame.getTextArea().write(fw);
					fw.close();
					lastSaveDate = LocalDateTime.now();
					frame.setTitle(filePath + "   -   FreeTTS Editor");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		// This file already exists
		// Change existing file's content
		}else{	
			try {
				filePath = frame.getFileChooser().getSelectedFile().getPath();
				if (!filePath.toLowerCase().endsWith(".txt")) {
					filePath = filePath + ".txt";
				}
				fw = new FileWriter(filePath);
				frame.getTextArea().write(fw);
				fw.close();
				frame.setTitle(filePath + "   -   FreeTTS Editor");	
				lastSaveDate = LocalDateTime.now();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveFileAs(FreeTTSWindow frame) {
		if (frame.getFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				String filePath = frame.getFileChooser().getSelectedFile().getPath();
				if (!filePath.endsWith(".txt")) {
					filePath = filePath + ".txt";
				}
				fw = new FileWriter(filePath);
				frame.getTextArea().write(fw);
				fw.close();
				lastSaveDate = LocalDateTime.now();
				frame.setTitle(filePath + "   -   FreeTTS Editor");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String docContentsToString(String text) {
		
		contents = docGenerator.createDocData(text);	//refresh contents
		String convertedText = "";
		
		if(contents.isEmpty()) {
			//error message
		}
		else if(contents.size() == 1) {
			convertedText = contents.get(0).joinWords();
		}
		else {
			convertedText = contents.get(0).joinWords();
			for(int i=1; i<contents.size(); i++) {
				convertedText += " " + contents.get(i).joinWords();
			}
		}
		return convertedText;
	}
	
	public void playAll(String text) {
		String textToPlay = this.docContentsToString(text);
		speechAPI.play(textToPlay);
	}
	
	public void playSelected(String text) {
		if (text != null) { speechAPI.play(text); }
	}
	
	public void playAllReverse(String text) {
		String textToPlay = this.docContentsToString(text);
		String[] wordsToReverse = textToPlay.split("\\s+");
		String reversedText = "";
		for (int i = wordsToReverse.length-1; i >= 0; i--) {
			reversedText += wordsToReverse[i] + " ";
		}
		speechAPI.play(reversedText);
	}
	
	public void playSelectedReverse(String text) {
		if (text != null) {
			String[] wordsToReverse = text.split("\\s+");
			String reversedText = "";
			for (int i = wordsToReverse.length-1; i >= 0; i--) {
				reversedText += wordsToReverse[i] + " ";
			}
			speechAPI.play(reversedText);
		}
	}
	
	public void playAllEncoded(String text) {
		String textToEncode = this.docContentsToString(text);
		String textToPlay = encoder.encode(textToEncode);
		speechAPI.play(textToPlay);
	}
	
	public void playSelectedEncoded(String text) {
		if (text != null) {
			String textToPlay = encoder.encode(text);
			speechAPI.play(textToPlay);
		}
	}
		
	public void saveSettings(float volume, float speed, float pitch, String strategy) {
		speechAPI.setVolume(volume);
		speechAPI.setRate(speed);
		speechAPI.setPich(pitch);
		encoder = encoderFactory.createStrategy(strategy);
	}
}
