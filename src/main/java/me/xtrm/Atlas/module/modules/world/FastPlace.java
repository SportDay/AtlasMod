package me.xtrm.Atlas.module.modules.world;

import java.lang.reflect.Field;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import net.minecraft.client.Minecraft;

public class FastPlace extends Module {

	public FastPlace() {
		super("FastPlace", Category.World);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		try {
			Field f = Minecraft.getMinecraft().getClass().getDeclaredField("rightClickDelayTimer");
			f.setAccessible(true);
			f.set(Minecraft.getMinecraft(), 0);
		} catch(Exception ex) {
			try {
				Field f = Minecraft.getMinecraft().getClass().getDeclaredField("field_71467_ac");
				f.setAccessible(true);
				f.set(Minecraft.getMinecraft(), 0);
			} catch(Exception exx) {
				ex.printStackTrace();
				exx.printStackTrace();
			}
		}
	}

}