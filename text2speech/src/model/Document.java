package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

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
	private String openFilePath = null;
	private String saveFilePath = null;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	public Document() {
		speechFactory = new TextToSpeechApiFactory();
		speechAPI = speechFactory.createSpeechApi("FREETTS"); //default value
		encoderFactory = new EncStrategyFactory();
		encoder = encoderFactory.createStrategy("ROT13");	  //default value
		docGenerator = new DocumentGenerator();
		fileAuthor = null;
		fileTitle = null;
		creationDate = null;
		lastSaveDate = null;
	}
	
	/**
	 * Does not continue when "Create" button is pressed, until user gives both title and author.
	 * Sets the creation date and the last save date.
	 * 
	 * @param author The author the user gave to the document.
	 * @param title  The title the user gave to the document.
	 */
	public String newFile(String author, String title) {			
		String message;

		// Make sure user gave title and author
		if (author.length() == 0 && title.length() == 0) {
			message = "You must give the file a Title and an Author!";
		}else if (title.length() == 0) {
			message = "You must give the file a Title!";
		}else if (author.length() == 0) {
			message = "You must give the file an Author!";
		// If user gave both title and author return "success" to command
		}else {
			fileAuthor = author;
			fileTitle = title;
			creationDate = LocalDateTime.now();
			lastSaveDate = null;
			message = "success";
		}
		return message;
	}
	
	/**
	 * Get's file's properties: author, title, creation date, last save date
	 * Reads and returns file's contents.
	 * Keeps the file path for replayability.
	 * 
	 * @param fileName The path of the file to be opened.
	 * @return String Return the contents of the file or "error" in case of error.
	 */
	public String openFile(String fileName) { 
		
			File file = new File(fileName);
			openFilePath = fileName;
			try {
				Scanner sc = new Scanner(file);
				fileTitle = sc.nextLine();
				fileAuthor = sc.nextLine();
				creationDate = LocalDateTime.parse(sc.nextLine(), formatter);
				lastSaveDate = LocalDateTime.parse(sc.nextLine(), formatter);
				String text = "";
				while (sc.hasNextLine()) {
					text += sc.nextLine();
				}
				sc.close();
				return text;
			}catch(IOException e) {
				e.printStackTrace();
				return "error";
			}
		}
	
	/**
	 * Updates the file's contents and the last saved date.
	 * Keeps the file path for replayability.
	 * 
	 * @param filePath The path of the file to be saved.
	 * @param textToSave The text of the file to be saved.
	 * @param newFile
	 */
	public void saveFile(String filePath, String textToSave, boolean newFile) {
		try {
			if (!filePath.toLowerCase().endsWith(".txt")) {
				filePath = filePath + ".txt";
			}
			saveFilePath = filePath;
			FileWriter fw = new FileWriter(filePath);
			lastSaveDate = LocalDateTime.now();
			if (newFile) {
				creationDate = lastSaveDate;
			}
			fw.write(fileTitle + "\n");
			fw.write(fileAuthor + "\n");
			fw.write(creationDate.format(formatter) + "\n");
			fw.write(lastSaveDate.format(formatter) + "\n");
			fw.write(textToSave);
			fw.close();	
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is called when recording.
	 * @param filePath The path of the file to be opened.
	 * @return "error" if something goes wrong.
	 */
	public String openFilePath(String filePath) {
		try {
			if (!filePath.toLowerCase().endsWith(".txt")) {
				filePath = filePath + ".txt";
			}
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			fileTitle = sc.nextLine();
			fileAuthor = sc.nextLine();
			creationDate = LocalDateTime.parse(sc.nextLine(), formatter);
			lastSaveDate = LocalDateTime.parse(sc.nextLine(), formatter);
			String text = "";
			while (sc.hasNextLine()) {
				text += sc.nextLine();
			}
			sc.close();
			return text;
		}catch(IOException e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * This method is called when recording.
	 * @param title The title of the new file.
	 * @param author The author of the new file.
	 */
	public void newFileAuto(String title, String author) {
		fileTitle = title;
		fileAuthor = author;
		creationDate = LocalDateTime.now();
		lastSaveDate = null;
	}
	
	/**
	 * 
	 * @param text The text to be converted.
	 * @return String The converted to a string contents.
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
	 * Set's volume, speed, pitch, strategy, library to the file.
	 * 
	 * @param volume
	 * @param speed
	 * @param pitch
	 * @param strategy
	 * @param library
	 */
	public void saveSettings(int volume, int speed, int pitch, String strategy, String library) {
		encoder = encoderFactory.createStrategy(strategy);
		speechAPI = speechFactory.createSpeechApi(library);
		speechAPI.setVolume(volume);
		speechAPI.setRate(speed);
		speechAPI.setPich(pitch);
	}
	
	/**
	 * @return String The path of the file to be opened.
	 */
	public String getOpenFilePath() {
		return openFilePath;
	}
	
	/**
	 * @return String The path of the file to be saved.
	 */
	public String getSaveFilePath() {
		return saveFilePath;
	}
	
	/**
	 * @return String The file's author.
	 */
	public String getAuthor() {
		return fileAuthor;
	}
	
	/**
	 * @return String The file's title.
	 */
	public String getTitle() {
		return fileTitle;
	}
	
	/**
	 * @return String The date the file was created.
	 */
	public LocalDateTime getLastSaveDate() {
		return lastSaveDate;
	}
	
	/**
	 * @return String The date the file was last saved.
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
}
