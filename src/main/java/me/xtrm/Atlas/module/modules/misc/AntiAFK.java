package me.xtrm.Atlas.module.modules.misc;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;

public class AntiAFK extends Module {
	
	public AntiAFK() {
		super("AntiAFK", Category.Miscellaneous);
	}
	
	private int tick = 0;
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		tick++;
		if(tick > 200) {
			tick = 0;
			mc.thePlayer.rotationYaw -= 180;
			mc.thePlayer.moveForward = 1;
			if(mc.thePlayer.onGround)
				mc.thePlayer.jump();
		}
	}

}
