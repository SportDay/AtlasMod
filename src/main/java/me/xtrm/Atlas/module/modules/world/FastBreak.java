package me.xtrm.Atlas.module.modules.world;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FastBreak extends Module {

	public FastBreak() {
		super("FastBreak", Category.World);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		mc.thePlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 840, 10));
	}
	
	@Override
	public void onDisable() {
		mc.thePlayer.removePotionEffectClient(Potion.digSpeed.id);
		super.onDisable();
	}

}
