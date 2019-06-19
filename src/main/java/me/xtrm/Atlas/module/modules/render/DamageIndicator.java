package me.xtrm.Atlas.module.modules.render;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.render.EventRender2D;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.ScaledUtils;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;

public class DamageIndicator extends Module {

	public DamageIndicator() {
		super("DamageIndicator", Category.Render);
	}
	
	@EventTarget
	public void onRender2D(EventRender2D event) {
		ScaledResolution sr = ScaledUtils.gen();
		
		if(mc.pointedEntity != null && mc.pointedEntity instanceof EntityLivingBase) {
			
			EntityLivingBase e = (EntityLivingBase)mc.pointedEntity;
			String name = e.getCommandSenderName();
			double length = 2 * 50;
			float lifepercent = (e.getMaxHealth()*e.getHealth()/100.0f) / (e.getMaxHealth()*e.getMaxHealth()/100);
			
//			Background
			GuiUtils.getInstance().drawRect(sr.getScaledWidth() / 2 + 1, sr.getScaledHeight() / 2 + 1, (float) (sr.getScaledWidth() / 2 + 1 + length + 10 + 10 + Wrapper.fr.getStringWidth(name)), sr.getScaledHeight() / 2 + 10 + 10 + 6, 0xFF666666);
			
//			Name
			Wrapper.fr.drawStringWithShadow(name, sr.getScaledWidth() / 2 + 7, sr.getScaledHeight() / 2 + 9, -1);
			
//			Total Bar
			GuiUtils.getInstance().drawRect(sr.getScaledWidth() / 2 + 15 + Wrapper.fr.getStringWidth(name), sr.getScaledHeight() / 2 + 1 + 2 + 2, (float) (sr.getScaledWidth() / 2 + 15 + Wrapper.fr.getStringWidth(name) + length), sr.getScaledHeight() / 2 + 22, 0xFFFF3333);
			
//			Health Bar
			GuiUtils.getInstance().drawRect(sr.getScaledWidth() / 2 + 15 + Wrapper.fr.getStringWidth(name), sr.getScaledHeight() / 2 + 1 + 2 + 2, (float) (sr.getScaledWidth() / 2 + 15 + Wrapper.fr.getStringWidth(name) + (int)(lifepercent * length)), sr.getScaledHeight() / 2 + 22, 0xFF00FF00);
			
//			Life
			Wrapper.fr.drawStringWithShadow(e.getHealth() + "/" + e.getMaxHealth(), (int) (sr.getScaledWidth() / 2 + 1 + length + 10 + 10 + Wrapper.fr.getStringWidth(name)) - Wrapper.fr.getStringWidth(e.getHealth() + "/" + e.getMaxHealth()) - 2 - 10, sr.getScaledHeight() / 2 + 9, -1);
		}
	}

}