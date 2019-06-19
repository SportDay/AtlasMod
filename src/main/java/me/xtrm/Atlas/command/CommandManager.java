package me.xtrm.Atlas.command;

import java.util.ArrayList;

import me.xtrm.Atlas.command.impl.Bind;
import me.xtrm.Atlas.command.impl.Help;
import me.xtrm.Atlas.command.impl.TP;
import me.xtrm.Atlas.command.impl.Toggle;
import me.xtrm.Atlas.command.impl.ToggleAlias;
import me.xtrm.Atlas.command.impl.VClip;
import me.xtrm.Atlas.main.Atlas;
import net.minecraft.util.EnumChatFormatting;

public class CommandManager {
	
	public ArrayList<ACommand> commands;
	
	public CommandManager(){
		commands = new ArrayList<ACommand>();
	}
	
	public void init() {
		commands.add(new Bind());
		commands.add(new Help());
//		commands.add(new Setting());
		commands.add(new Toggle());
		commands.add(new ToggleAlias());
		commands.add(new TP());
		commands.add(new VClip());
	}
	
	public void runCommands(String s) {
		Atlas.instance.console.addMessage(EnumChatFormatting.GRAY + "> " + s);

		boolean commandResolved = false;
		String readString = s.trim();
		boolean hasArgs = readString.trim().contains(" ");
		String commandName = hasArgs ? readString.split(" ")[0] : readString.trim();
		String[] args = hasArgs ? readString.substring(commandName.length()).trim().split(" ") : new String[0];
		for(ACommand command : commands){
			if(command.getCommand().trim().equalsIgnoreCase(commandName.trim())) {
				commandResolved = true;
				if(!command.runCommand(readString, args)) {
					Atlas.instance.console.addMessage(EnumChatFormatting.RED + "Une erreur s'est produite durant l'execution de la commande.");
				}
				break;
			}
		}
		if(!commandResolved){
//			ChatUtils.sendMessage("§cInvalid command. Type §6\"help\" §cfor Help");
			Atlas.instance.console.addMessage(EnumChatFormatting.RED + "Commande invalide. Tapez " + EnumChatFormatting.GOLD + "\"help\" " + EnumChatFormatting.RED + "pour avoir de l'aide");
		}
	}

}