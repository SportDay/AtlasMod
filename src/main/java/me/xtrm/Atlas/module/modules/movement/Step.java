package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.MovementUtils;

public class Step extends Module {
	
	public Step() {
		super("Step", Category.Movement);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		mc.thePlayer.stepHeight = 8f;
		if(mc.thePlayer.isCollidedHorizontally && mc.thePlayer.onGround && mc.theWorld.getBlock((int)mc.thePlayer.posX, (int)mc.thePlayer.posY + 2, (int)mc.thePlayer.posZ).getMaterial().isReplaceable()) {
			mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 1, mc.thePlayer.posZ);
			MovementUtils.setSpeed(0.2);
		}
	}
	
	@Override
	public void onDisable() {
		mc.thePlayer.stepHeight = 0.6f;
		super.onDisable();
	}

}
