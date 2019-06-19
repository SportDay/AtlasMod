package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;

public class Jesus extends Module {
	
	public Jesus() {
		super("Jesus", Category.Movement);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(mc.thePlayer.isInWater()) {
			mc.thePlayer.motionY = 0.05;
			mc.thePlayer.motionX *= 1.2;
			mc.thePlayer.motionZ *= 1.2;
			if(mc.thePlayer.isCollidedHorizontally) 
				mc.thePlayer.onGround = true;
		}
	}

}
