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

/**
 * <h1> Document  </h1> 
 * @author Vasiliki Kanakari
 */


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
	private boolean flag; //This variable is true when saveFile()/saveFileAs() calls newFile()
	
	private String openFilePath = null;
	private String saveFilePath = null;
	
	public Document() {
		speechFactory = new TextToSpeechApiFactory();
		speechAPI = speechFactory.createSpeechApi("FREETTS"); //default value
		encoderFactory = new EncStrategyFactory();
		encoder = encoderFactory.createStrategy("ROT13");	  //default value
		docGenerator = new DocumentGenerator();
		//fileAuthor = null;
		//fileTitle = null;
		creationDate = null;
		lastSaveDate = null;
		flag = false;
	}
	
	/**
	 * Pop's up a window that requests the user to give the file a title and an author.
	 * You can press "Create" button to create the new file or "Cancel" button.
	 * Does not continue when "Create" button is pressed, until user gives both title and author.
	 * Sets the creation date and the last save date.
	 * 
	 * Initializes volume, speed, pitch, encoding strategy and speech library's values.
	 * 
	 * If this method has been called from saveFile() method, then flag = true
	 * and after giving the file title and author, the file has to be saved, 
	 * so it calls saveNewFile() method.
	 * 
	 * @param frame The application's main frame.
	 */
	public void newFile(FreeTTSWindow frame) {
		//initialize preferences window 
		frame.setVolumeValue(50);
		frame.setSpeedValue(21);
		frame.setPitchValue(11);
		frame.setEncodingStrategy("ROT13");
		frame.setSpeechLibrary("FREETTS");
				
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
						lastSaveDate = creationDate;
						newFileFrame.dispose();
						FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
						frame.setFileChooser(new JFileChooser());
						frame.getFileChooser().setFileFilter(txtFilter);
						frame.setTitle("NewFile*   -   FreeTTS Editor");
						if (flag == true) {
							saveNewFile(frame);
						}else {
							frame.getTextArea().setText(null);
						}
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
	
	/**
	 * Pop's up the OpenDialog window for the user to choose which file they
	 * want to open. After selecting a file, updates the text area so as the user can 
	 * see its contents and sets the frame's title to the current open file's path.
	 * 
	 * Initializes volume, speed, pitch and encoding strategy's values.
	 * 
	 * @param frame The application's main frame.
	 */
	public void openFile(FreeTTSWindow frame) { 
		//initialize preferences window
		frame.setVolumeValue(50);
		frame.setSpeedValue(21);
		frame.setPitchValue(11);
		frame.setEncodingStrategy("ROT13");
		frame.setSpeechLibrary("FREETTS");
		
		if (frame.getFileChooser().showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String fileName = frame.getFileChooser().getSelectedFile().getAbsolutePath();
			openFilePath = fileName;
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
	
	/**
	 * Updates the file's content and the last saved date.
	 * 
	 * If the file to be saved does not exist (has not been saved before)
	 * and if the user has not given the file title and author yet, calls newFile() 
	 * so as the user gives these values first and then save the file.
	 * 
	 * If the file to be saved does not exist (has not been saved before),
	 * but the user has already given the file title and author, the
	 * file can now be saved, so it calls saveNewFile() method.
	 * 
	 * @param frame The application's main frame.
	 */
	public void saveFile(FreeTTSWindow frame) {
		FileWriter fw = null;
		String filePath;
		// This file does not exist
		if (frame.getFileChooser().getSelectedFile() == null) {
			flag = true;		
			if (fileTitle == null || fileAuthor == null) {
				// Must create new file, to set values for fileTitle and fileAuthor
				// and then save the file 
				newFile(frame); //file is saved inside newFile(), since flag = true
			}else {
				// If user has given file title and author, the file can be saved
				saveNewFile(frame);
			}
		// This file already exists
		// Change existing file's content
		}else{	
			try {
				filePath = frame.getFileChooser().getSelectedFile().getPath();
				saveFilePath = filePath;
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
	
	/**
	 * This method is called when the recording.
	 * 
	 * @param frame
	 * @param filePath
	 */
	public void openFilePath(FreeTTSWindow frame, String filePath) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(filePath);
			frame.getTextArea().read(fileReader, null);
			fileReader.close();
			frame.setTitle(filePath + "   -   FreeTTS Editor");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is called when the recording.
	 * 
	 * @param frame The application's main frame.
	 * @param filePath The path to save the file.
	 */
	public void saveFilePath(FreeTTSWindow frame, String filePath) {
		FileWriter fw = null;
		try {
			if (!filePath.toLowerCase().endsWith(".txt")) {
				filePath = filePath + ".txt";
			}
			fw = new FileWriter(filePath);
			frame.getTextArea().write(fw);
			fw.close();
			frame.setTitle(filePath + "   -   FreeTTS Editor");	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is called when "Save" menu item is chosen and the the file 
	 * does not exist already or when "SaveAs" menu item is chosen.
	 * It pop's up the save dialog window, user gives the file a name,
	 * and saves it to a directory.
	 * Then, sets the frame's title to the current file's path and
	 * sets the creation date and the last save date of the file.
	 * 
	 * @param frame The application's main frame.
	 */
	private void saveNewFile(FreeTTSWindow frame) {
		FileWriter fw = null;
		String filePath;
		if (frame.getFileChooser().showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				filePath = frame.getFileChooser().getSelectedFile().getPath();
				saveFilePath = filePath;
				if (!filePath.endsWith(".txt")) {
					filePath = filePath + ".txt";
				}
				fw = new FileWriter(filePath);
				frame.getTextArea().write(fw);
				fw.close();
				creationDate = LocalDateTime.now();
				lastSaveDate = creationDate;
				frame.setTitle(filePath + "   -   FreeTTS Editor");
			}catch (IOException e) {
				e.printStackTrace();
			};
		}
		flag = false;
	}
	
	/**
	 * If the user has not given the file title and author yet, calls newFile() 
	 * so as the user gives these values first and then save the file.
	 * 
	 * If the user has already given the file title and author, the
	 * file can now be saved, so it calls saveNewFile() method.
	 * 
	 * @param frame The application's main frame.
	 */
	public void saveFileAs(FreeTTSWindow frame) {
		flag = true;
		if (fileTitle == null || fileAuthor == null) {
			// Must create new file, to set values for fileTitle and fileAuthor
			// and then save the file 
			newFile(frame); //file is saved inside newFile(), since flag = true
		}else {
			// If user has given file title and author, the file can be saved
			saveNewFile(frame);
		}
	}
	
	/**
	 * TODO
	 * 
	 * @param
	 * @return
	 */
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
	
	/**
	 * Keep the whole text into a string and then play it.
	 * @param text The whole text of the text area.
	 */
	public void playAll(String text) {
		String textToPlay = this.docContentsToString(text);
		speechAPI.play(textToPlay);
	}
	
	/**
	 * If some text is selected, play it.
	 * @param text The selected text of the text area.
	 */
	public void playSelected(String text) {
		if (text != null) { speechAPI.play(text); }
	}
	
	/**
	 * Keep the whole text into a string, split this string into words 
	 * kept in wordsToReverse[] array, reverse the array
	 * and then play the reversed text.
	 * @param text The whole text of the text area.
	 */
	public void playAllReverse(String text) {
		String textToPlay = this.docContentsToString(text);
		String[] wordsToReverse = textToPlay.split("\\s+");
		String reversedText = "";
		for (int i = wordsToReverse.length-1; i >= 0; i--) {
			reversedText += wordsToReverse[i] + " ";
		}
		speechAPI.play(reversedText);
	}
	
	/**
	 * If some text is selected, split this text into words 
	 * kept in wordsToReverse[] array, reverse the array
	 * and then play the reversed text.
	 * @param text The selected text of the text area.
	 */
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
	
	/**
	 * Keep the whole text into a string, encode and then play it.
	 * @param text The whole text of the text area.
	 */
	public void playAllEncoded(String text) {
		String textToEncode = this.docContentsToString(text);
		String textToPlay = encoder.encode(textToEncode);
		speechAPI.play(textToPlay);
	}
	
	/**
	 * If some text is selected, encode and play it.
	 * @param text The selected text of the text area.
	 */
	public void playSelectedEncoded(String text) {
		if (text != null) {
			String textToPlay = encoder.encode(text);
			speechAPI.play(textToPlay);
		}
	}
	
	/**
	 * TODO
	 * 
	 * @param volume
	 * @param speed
	 * @param pitch
	 * @param strategy
	 * @param library
	 */
	public void saveSettings(int volume, int speed, int pitch, String strategy, String library) {
		speechAPI.setVolume(volume);
		speechAPI.setRate(speed);
		speechAPI.setPich(pitch);
		encoder = encoderFactory.createStrategy(strategy);
		speechAPI = speechFactory.createSpeechApi(library);
	}
	
	public String getOpenFilePath() {
		return openFilePath;
	}
	
	public String getSaveFilePath() {
		return saveFilePath;
	}
		
	public String getAuthor() {
		return fileAuthor;
	}
	
	public String getTitle() {
		return fileTitle;
	}
	
	public LocalDateTime getLastSaveDate() {
		return lastSaveDate;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
