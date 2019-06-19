package me.xtrm.Atlas.command.impl;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.command.ACommand;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import net.minecraft.util.EnumChatFormatting;

public class Bind extends ACommand {

	@Override
	public String getCommand() {
		return "bind";
	}

	@Override
	public boolean runCommand(String s, String[] args) {
		try {
			if(args.length == 0) {
				addConsoleText(EnumChatFormatting.RED + "Erreur, arguments invalides. Essayez:");
				addConsoleText(EnumChatFormatting.RED + " bind <module> <touche>");
				addConsoleText(EnumChatFormatting.RED + " bind <module> none");
				addConsoleText(EnumChatFormatting.RED + " bind clear");
			}else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("clear")) {
					for(Module m : Atlas.instance.moduleManager.mods) {
						m.setKey(0);
					}
				}else {
					addConsoleText(EnumChatFormatting.RED + "Erreur, arguments invalides. Essayez:");
					addConsoleText(EnumChatFormatting.RED + " bind <module> <touche>");
					addConsoleText(EnumChatFormatting.RED + " bind <module> none");
					addConsoleText(EnumChatFormatting.RED + " bind clear");
				}
			}else if(args.length == 2) {
				Module mod = null;
				for(Module m : Atlas.instance.moduleManager.mods) {
					if(m.getName().equalsIgnoreCase(args[0])) {
						mod = m;
					}
				}
				
				if(mod == null) {
					addConsoleText(EnumChatFormatting.RED + "Erreur, module introuvable.");
				}else {
					if(args[1].equalsIgnoreCase("none")) {
						mod.setKey(0);
						addConsoleText(EnumChatFormatting.GRAY + "Bind de " + mod.getName() + " reset");
					}else {
						try {
							String thearg = args[1].toUpperCase();
							int key = Keyboard.getKeyIndex(thearg);
							int keyx = Keyboard.getKeyIndex(Keyboard.getKeyName(key));
							mod.setKey(keyx);
							addConsoleText(EnumChatFormatting.GRAY + "Bind de " + mod.getName() + " défini à " + EnumChatFormatting.BLUE + thearg);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}else {
				addConsoleText(EnumChatFormatting.RED + "Erreur, arguments invalides. Essayez:");
				addConsoleText(EnumChatFormatting.RED + " bind <module> <touche>");
				addConsoleText(EnumChatFormatting.RED + " bind <module> none");
				addConsoleText(EnumChatFormatting.RED + " bind clear");
			}
		} catch (Exception e) {
			addConsoleText(EnumChatFormatting.RED + "Error, report to xTrM_");
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public String getHelp() {
		return "Définis la touche pour un module";
	}

}
