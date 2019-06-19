package me.xtrm.Atlas.module.modules.player;

import java.lang.reflect.Field;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;

public class AutoMiner extends Module {

	public AutoMiner() {
		super("AutoMiner", Category.Player);
	}
	

	@EventTarget
	public void onUpdate(EventUpdate e) {
		try {
			Field f = mc.gameSettings.keyBindAttack.getClass().getDeclaredField("pressed");
			f.setAccessible(true);
			f.set(mc.gameSettings.keyBindAttack, true);
		} catch (Exception ex) { 
			try {
				Field f = mc.gameSettings.keyBindAttack.getClass().getDeclaredField("field_74513_e");
				f.setAccessible(true);
				f.set(mc.gameSettings.keyBindAttack, true);
			} catch(Exception exx) {
				ex.printStackTrace();
				exx.printStackTrace();
			}
		}
	}
	
	@Override
	public void onDisable() {
		try {
			Field f = mc.gameSettings.keyBindAttack.getClass().getDeclaredField("pressed");
			f.setAccessible(true);
			f.set(mc.gameSettings.keyBindAttack, false);
		} catch (Exception ex) { 
			try {
				Field f = mc.gameSettings.keyBindAttack.getClass().getDeclaredField("field_74513_e");
				f.setAccessible(true);
				f.set(mc.gameSettings.keyBindAttack, false);
			} catch(Exception exx) {
				ex.printStackTrace();
				exx.printStackTrace();
			}
		}
		super.onDisable();
	}

}
