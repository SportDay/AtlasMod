package me.xtrm.Atlas.command.impl;

import me.xtrm.Atlas.command.ACommand;
import me.xtrm.Atlas.main.Atlas;
import net.minecraft.util.EnumChatFormatting;

public class Help extends ACommand {

	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public boolean runCommand(String s, String[] args) {
		for(ACommand cmd : Atlas.instance.commandManager.commands) {
			if(!cmd.getHelp().equalsIgnoreCase("{{no-display}}")) {
				addConsoleText(EnumChatFormatting.GRAY + cmd.getCommand() + EnumChatFormatting.DARK_GRAY + " - " + EnumChatFormatting.GRAY + cmd.getHelp());
			}
		}
		return true;
	}

	@Override
	public String getHelp() {
		return "Donne l'aide des commandes";
	}

}
