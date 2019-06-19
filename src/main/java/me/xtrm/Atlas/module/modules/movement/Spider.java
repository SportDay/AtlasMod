package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;

public class Spider extends Module {
	
	public Spider() {
		super("Spider", Category.Movement);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(mc.thePlayer.motionY < 0 && mc.thePlayer.isCollidedHorizontally)
			mc.thePlayer.jump();
	}

}
