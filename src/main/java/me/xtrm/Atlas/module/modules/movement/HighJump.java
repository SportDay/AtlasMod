package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.move.EventJump;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.ChatUtils;

public class HighJump extends Module {
	
	public HighJump() {
		super("HighJump", Category.Movement);
		
		addSetting(new Setting("Motion", this, 2D, 0D, 5D, false));
	}
	
	@EventTarget
	public void onUpdate(EventJump e) {
		double motion = gsu("Motion").doubl();
		
		if(mc.thePlayer.onGround) {
			mc.thePlayer.motionY = motion;
		}
	}
	
	@Override
	public void onEnable() {
		if(mc.isSingleplayer())
			ChatUtils.sendMessage("Warning, this module is buggy on Singleplayer");
		super.onEnable();
	}

}
