package me.xtrm.Atlas.command;

import me.xtrm.Atlas.main.Atlas;

public abstract class ACommand {
	
	public abstract String getCommand();
	public abstract boolean runCommand(String s, String[] args);
	public abstract String getHelp();
	
	public void addConsoleText(String text) {
		Atlas.instance.console.addMessage(text);
	}
	
}
