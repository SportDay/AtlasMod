package me.xtrm.Atlas.module.modules.movement;

import java.util.ArrayList;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.MovementUtils;
import net.minecraft.util.EnumChatFormatting;

public class BunnyHop extends Module {

	public BunnyHop() {
		super("BunnyHop", Category.Movement);
		
		ArrayList<String> mode = new ArrayList<String>();
		mode.add("Legit");
		mode.add("Fast");
		mode.add("BOINGBOING!");
		addSetting(new Setting("Mode", this, "Fast", mode));
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		String mode = getMode();
		setDisplayName(getName() + EnumChatFormatting.GRAY + " " + mode);
		
		switch(mode) {
			case "Legit":
				legit();
				break;
			case "Fast":
				fast();
				break;
			case "BOINGBOING!":
				fukcit();
				break;
		}
	}
	
	public void legit() {
		if(MovementUtils.isMoving()) {
			if(mc.thePlayer.onGround) {
				mc.thePlayer.jump();
			}
		}
	}
	
	public void fast() {
		if(MovementUtils.isMoving()) {
			if(mc.thePlayer.onGround) {
				MovementUtils.setSpeed(0.75);
				mc.thePlayer.motionY = 0.4;
			}else {
				MovementUtils.setSpeed(0.55);
				mc.thePlayer.motionY -= mc.thePlayer.isCollidedHorizontally ? 0 : 0.05;
			}
		}
	}
	
	public void fukcit() {
		if(MovementUtils.isMoving()) {
			if(mc.thePlayer.onGround) {
				MovementUtils.setSpeed(1);
				mc.thePlayer.jump();
				mc.thePlayer.motionY = 0.9;
			}else {
				MovementUtils.setSpeed(0.8);
				mc.thePlayer.motionY -= 0.05;
			}
		}
	}

}
