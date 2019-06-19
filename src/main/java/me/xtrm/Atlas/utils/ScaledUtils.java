package me.xtrm.Atlas.utils;

import net.minecraft.client.gui.ScaledResolution;

public class ScaledUtils {
	
	public static ScaledResolution gen() {
		return new ScaledResolution(Wrapper.mc, Wrapper.mc.displayWidth, Wrapper.mc.displayHeight);
	}

}
