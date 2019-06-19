package me.xtrm.Atlas.command.impl;

import me.xtrm.Atlas.command.ACommand;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.util.EnumChatFormatting;

public class TP extends ACommand {

	@Override
	public String getCommand() {
		return "tp";
	}

	@Override
	public boolean runCommand(String s, String[] args) {		
		if(args.length == 3) {
			int x=-148615614,y=-148615614,z=-148615614;
			try {
				x = Integer.parseInt(args[0]);
				y = Integer.parseInt(args[1]);
				z = Integer.parseInt(args[2]);
			} catch (Exception e) {
				addConsoleText(EnumChatFormatting.RED + "Error, utilisez des nombres.");
				return true;
			}
			if(x == -148615614 || y == -148615614 || z == -148615614) {
				addConsoleText(EnumChatFormatting.RED + "Error, utilisez des nombres.");
				return true;
			}

			for(int i = 0; i < 25; i++) {
				Wrapper.mc.thePlayer.setPosition(x, y, z);
			}
			
			Wrapper.mc.displayGuiScreen(null);
		}else {
			addConsoleText(EnumChatFormatting.RED + "Error, usage: tp <x> <y> <z>");
			return true;
		}
		return true;
	}

	@Override
	public String getHelp() {
		return "Te téléportes en X Y Z";
	}

}
