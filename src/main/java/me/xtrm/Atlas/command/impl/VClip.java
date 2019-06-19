package me.xtrm.Atlas.command.impl;

import me.xtrm.Atlas.command.ACommand;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.util.EnumChatFormatting;

public class VClip extends ACommand {

	@Override
	public String getCommand() {
		return "vclip";
	}

	@Override
	public boolean runCommand(String s, String[] args) {
		if(args.length == 0) {
			addConsoleText(EnumChatFormatting.RED + "Donnez une valeur entre -10 et 10");
			return true;
		}else if(args.length == 1) {
			try {
				double d = Double.parseDouble(args[0]);
				
				if(d < -10 || d > 10) {
					addConsoleText(EnumChatFormatting.RED + "Erreur, mettez un nombre entre -10 et 10");
					return true;
				}
				
				Wrapper.mc.thePlayer.setPosition(Wrapper.mc.thePlayer.posX, Wrapper.mc.thePlayer.posY + d, Wrapper.mc.thePlayer.posZ);
				d *= 100;
				d = Math.round(d);
				d /= 100;				
				addConsoleText(EnumChatFormatting.GRAY + "Téléporté " + EnumChatFormatting.BLUE + d);
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				addConsoleText(EnumChatFormatting.RED + "Erreur, mettez un nombre correct");
				return false;
			}
		}else {
			addConsoleText(EnumChatFormatting.RED + "Donnez une valeur entre -10 et 10");
			return true;
		}
	}

	@Override
	public String getHelp() {
		return "Te téléportes sur l'axe Y";
	}

}
