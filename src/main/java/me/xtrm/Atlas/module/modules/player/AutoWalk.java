package me.xtrm.Atlas.module.modules.player;

import java.lang.reflect.Field;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;

public class AutoWalk extends Module {

	public AutoWalk() {
		super("AutoWalk", Category.Player);
	}	

	@EventTarget
	public void onUpdate(EventUpdate e) {
		try {
			Field f = mc.gameSettings.keyBindForward.getClass().getDeclaredField("pressed");
			f.setAccessible(true);
			f.set(mc.gameSettings.keyBindForward, true);
		} catch (Exception ex) { 
			try {
				Field f = mc.gameSettings.keyBindForward.getClass().getDeclaredField("field_74513_e");
				f.setAccessible(true);
				f.set(mc.gameSettings.keyBindForward, true);
			} catch(Exception exx) {
				ex.printStackTrace();
				exx.printStackTrace();
			}
		}
	}
	
	@Override
	public void onDisable() {
		try {
			Field f = mc.gameSettings.keyBindForward.getClass().getDeclaredField("pressed");
			f.setAccessible(true);
			f.set(mc.gameSettings.keyBindForward, false);
		} catch (Exception ex) { 
			try {
				Field f = mc.gameSettings.keyBindForward.getClass().getDeclaredField("field_74513_e");
				f.setAccessible(true);
				f.set(mc.gameSettings.keyBindForward, false);
			} catch(Exception exx) {
				ex.printStackTrace();
				exx.printStackTrace();
			}
		}
		super.onDisable();
	}

}
