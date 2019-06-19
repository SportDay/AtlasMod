package me.xtrm.Atlas.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Fullbright extends Module {
	
	public Fullbright() {
		super("Fullbright", Keyboard.KEY_G, Category.Render);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 860, 10));
	}
	
	@Override
	public void onDisable() {
		mc.thePlayer.removePotionEffectClient(Potion.nightVision.id);
		super.onDisable();
	}

}
