package me.xtrm.Atlas.command.impl;

import me.xtrm.Atlas.command.ACommand;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import net.minecraft.util.EnumChatFormatting;

public class Toggle extends ACommand {

	@Override
	public String getCommand() {
		return "toggle";
	}

	@Override
	public boolean runCommand(String s, String[] args) {
		if(args.length == 1) {
			boolean found = false;
			for(Module m : Atlas.instance.moduleManager.mods) {
				if(m.getName().equalsIgnoreCase(args[0])) {
					found = true;
					m.toggle();
					addConsoleText(EnumChatFormatting.BLUE + m.getName() + " " + (m.isEnabled() ? (EnumChatFormatting.GREEN + "activé") : (EnumChatFormatting.RED + "désactivé")));
				}
			}
			if(!found)
				addConsoleText(EnumChatFormatting.RED + "Module introuvable.");
			
			return true;
		}else {
			addConsoleText(EnumChatFormatting.RED + "Indiquez un module. ex: \"toggle KillAura\"");
			return true;
		}
	}

	@Override
	public String getHelp() {
		return "Active/Desactive le module spécifié";
	}

}
