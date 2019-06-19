package me.xtrm.Atlas.module.modules.gui;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;

public class ClickGUI extends Module {

	public ClickGUI() {
		super("ClickGUI", Keyboard.KEY_RSHIFT, Category.GUI);
	}
	
	@Override
	public void onToggle() {
//		toggle();
		
		if(Atlas.instance.clickgui == null)
			Atlas.instance.clickgui = new me.xtrm.Atlas.guis.click.ClickGUI();
		
		mc.displayGuiScreen(Atlas.instance.clickgui);
	}

}
