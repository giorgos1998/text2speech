package commands;

import java.util.HashMap;

import gui.FreeTTSWindow;
import model.Document;

public class CommandManager {

	private HashMap<String, Command> commandCache;
	private ReplayStack stack;
	private boolean record;
	
	public CommandManager() {
		commandCache = new HashMap<String, Command>();
		stack = new ReplayStack();
		record = false;
	}
	
	public Command getCommand(String ID) {
		return commandCache.get(ID);
	}
	
	public void addClone(String ID) {
		Command clone = (Command) commandCache.get(ID).clone();
		clone.setCloneFlag(true);
		stack.addToStack(clone);
	}
	
	public void loadCache(FreeTTSWindow frame, Document doc) {
		//TODO add all commands
		commandCache.put("NewFileCommand", new NewFile(frame, doc));
		commandCache.put("OpenFileCommand", new OpenFile(frame, doc));
		commandCache.put("SaveFileCommand", new SaveFile(frame, doc));
		commandCache.put("SaveAsFileCommand", new SaveAsFile(frame, doc));
		commandCache.put("ExitCommand", new Exit());
		commandCache.put("TuneAudioCommand", new TuneAudio(frame, doc, this));
		commandCache.put("HelpCommand", new Help());
		commandCache.put("PlayAllCommand", new PlayAll(frame, doc, this));
		commandCache.put("PlaySelectedCommand", new PlaySelected(frame, doc, this));
		commandCache.put("PlayAllReverse", new PlayAllReverse(frame, doc, this));
		commandCache.put("PlaySelectedReverse", new PlaySelectedReverse(frame, doc, this));
		commandCache.put("PlayAllEncoded", new PlayAllEncoded(frame, doc,this));
		commandCache.put("PlaySelectedEncoded", new PlaySelectedEncoded(frame, doc, this));
		commandCache.put("StartRecording", new StartRecording(this));
		commandCache.put("StopRecording", new StopRecording(this));
		commandCache.put("RunRecording", new RunRecording(this, stack));
	}
	
	public void startRecording() {
		record = true;
	}
	
	public void stopRecording() {
		record = false;
	}
	
	public boolean isRecording() {
		return record;
	}
}
