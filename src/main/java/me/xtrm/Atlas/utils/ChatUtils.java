package me.xtrm.Atlas.utils;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtils {
	
	public static void sendMessage(String msg) {
		String s = EnumChatFormatting.GRAY + " > " + EnumChatFormatting.WHITE + msg;
		
		s = s.replaceAll("&", "§");
		
		Wrapper.mc.thePlayer.addChatMessage(new ChatComponentText(s));
	}

}
