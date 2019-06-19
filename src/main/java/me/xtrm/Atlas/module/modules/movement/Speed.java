package me.xtrm.Atlas.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.Modes;
import me.xtrm.Atlas.utils.MovementUtils;
import net.minecraft.util.EnumChatFormatting;

public class Speed extends Module {
	
	public Speed() {
		super("Speed", Keyboard.KEY_M, Category.Movement);
		
		addSetting(new Setting("Speed", this, 1.25D, 0D, 5D, false));
		addSetting(new Setting("Mode", this, "Normal", Modes.build("Normal", "YPort", "Test")));
	}

	@EventTarget
	public void onUpdate(EventUpdate e) {
		String mode = getMode();
		setDisplayName(getName() + EnumChatFormatting.GRAY + " " + mode);
		
		switch(mode) {
			case "Normal":
				normal();
				break;
			case "YPort":
				yport();
				break;
			case "Test":
				test();
				break;
		}
	}
	
	public void normal() {
		double speed = gsu("Speed").doubl() / 2.5;
		MovementUtils.setSpeed(MovementUtils.getSpeed());
		if(MovementUtils.isMoving()) {
			MovementUtils.setSpeed(speed);
		}
	}
	
	public void yport() {
		double speed = gsu("Speed").doubl() / 2.5;
		if(MovementUtils.isMoving()) {
			if(mc.thePlayer.onGround) {
				mc.thePlayer.motionY = 0.25;
				MovementUtils.setSpeed(speed);
			}else {
				mc.thePlayer.motionY = -1;
			}	
		}
	}
	
	public void test() {
		
	}
	
}