package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.MovementUtils;

public class Glide extends Module {

	public Glide() {
		super("Glide", Category.Movement);
		
		addSetting(new Setting("Speed", this, 0.25D, 0D, 2D, false));
		addSetting(new Setting("Motion", this, -0.1D, -1D, 0D, false));
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		double speed = gsu("Speed").doubl();
		speed /= 10;
		double motion = gsu("Motion").doubl();
		
		if(mc.thePlayer.motionY < 0) {
			mc.thePlayer.motionY = motion;
			if(MovementUtils.isMoving()) {
				MovementUtils.setSpeed(MovementUtils.getSpeed() + speed);
			}
		}
	}

}
