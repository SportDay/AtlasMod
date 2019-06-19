package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.MovementUtils;

public class Sprint extends Module {
	
	public Sprint() {
		super("Sprint", Category.Movement);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if (!mc.thePlayer.isCollidedHorizontally && MovementUtils.isMoving()) {
			mc.thePlayer.setSprinting(true);
		}
	}

}
