package model;

public class DataStructureGenerator {

	public DataStructureGenerator() {
		
	}
	
	public String[] breakLines(String input){
		return input.split("\\r?\\n");
	}
	
	public String[] breakWords(String input) {
		return input.trim().split("\\s+");
	}
}
