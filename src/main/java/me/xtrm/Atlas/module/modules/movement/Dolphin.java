package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;

public class Dolphin extends Module {

	public Dolphin() {
		super("Dolphin", Category.Movement);
	}
	
	boolean hbiw = false;
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(mc.thePlayer.isInWater()) {
			hbiw = true;
			mc.thePlayer.motionY = 0.3;
		}
		if(hbiw) {
			if(mc.thePlayer.onGround) {
				hbiw = false;
			}
			mc.thePlayer.motionY += 0.0675;
		}
	}

}
