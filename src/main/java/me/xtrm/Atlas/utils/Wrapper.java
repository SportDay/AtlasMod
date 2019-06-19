package me.xtrm.Atlas.utils;

import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Wrapper {
	
	public static Minecraft mc = Minecraft.getMinecraft();
	public static FontRenderer fr = Minecraft.getMinecraft().fontRenderer;

	public static Setting getSetting(String name, Module m) {
		return m.gsu(name);
	}
	
}
