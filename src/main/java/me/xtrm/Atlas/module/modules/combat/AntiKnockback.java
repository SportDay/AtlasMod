package me.xtrm.Atlas.module.modules.combat;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.MovementUtils;

public class AntiKnockback extends Module {

	public AntiKnockback() {
		super("AntiKnockback", Category.Combat);
	}
	
	private double oldmy = 0;
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if(mc.thePlayer.hurtTime == 9){
			mc.thePlayer.motionY = oldmy;
			MovementUtils.setSpeed(MovementUtils.getSpeed());
    	}else {
    		oldmy = mc.thePlayer.motionY;
    	}
	}

}
