package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.Modes;
import me.xtrm.Atlas.utils.MovementUtils;
import net.minecraft.util.EnumChatFormatting;

public class FastLadder extends Module {
	
	public FastLadder() {
		super("FastLadder", Category.Movement);
		
		addSetting(new Setting("Mode", this, "Normal", Modes.build("Normal", "Fast")));
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		String mode = getMode();
		setDisplayName(getName() + EnumChatFormatting.GRAY + " " + mode);
		
		switch(mode) {
			case "Normal":
				normal();
				break;
			case "Fast":
				fast();
				break;
		}
	}
	
	public void normal() {
		if(MovementUtils.isMoving() && mc.thePlayer.isOnLadder() && mc.thePlayer.isCollidedHorizontally) {
			mc.thePlayer.motionY *= 4;
		}
	}
	
	public void fast() {
		if(MovementUtils.isMoving() && mc.thePlayer.isOnLadder() && mc.thePlayer.isCollidedHorizontally) {
			mc.thePlayer.motionY = 1;
		}
	}


}
