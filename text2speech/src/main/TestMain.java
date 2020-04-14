package main;

import commands.Command;
import commands.CommandCache;

public class TestMain {

	public static void main(String[] args) {
		CommandCache cache = new CommandCache();
		cache.loadCache();
		Command clone1 = cache.getCommand("TESTCOMMAND", true);
		Command original = cache.getCommand("TESTCOMMAND", false);
		System.out.println("Clone 1:");
		clone1.execute();
		System.out.println();
		System.out.println("Original:");
		original.execute();
		original.setNum(20);
		System.out.println();
		System.out.println("Original after change:");
		original.execute();
		Command clone2 = cache.getCommand("TESTCOMMAND", true);
		System.out.println();
		System.out.println("Clone 2:");
		clone2.execute();
	}

}
