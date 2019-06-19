package me.xtrm.Atlas.module.modules.render;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.render.EventRender2D;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.tabgui.TabGui;
import me.xtrm.Atlas.utils.RainbowUtils;
import me.xtrm.Atlas.utils.ScaledUtils;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;

public class HUD extends Module {
	
	public HUD() {
		super("HUD", Keyboard.KEY_H, Category.Render, true);
		
		addSetting(new Setting("TabGui", this, false));
	}
	
	private TabGui tabgui;
	
	private float hue = 0.0f;
	
	@EventTarget
	public void onRender2D(EventRender2D evnt) {
		ScaledResolution sr = ScaledUtils.gen();
		
		float hue1 = this.hue;
		this.hue += 0.005F;
		if(this.hue >= 255.0F) {
			this.hue = 0.0F;
		}
		
		GL11.glPushMatrix();
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		drawStringRBW(Atlas.instance.name, 3, 2, 0.9F);
		GL11.glScalef(1F, 1F, 1F);
		GL11.glPopMatrix();
		fr.drawStringWithShadow(EnumChatFormatting.GRAY + "v" + Atlas.instance.ver, (int)(Wrapper.fr.getStringWidth(Atlas.instance.name) * 1.5F) + 8, 2, -1);
		
		Collections.sort(Atlas.instance.moduleManager.getMods(), new Comparator<Module>() {
	    	public int compare(Module m1, Module m2) {
		        if (Wrapper.fr.getStringWidth(m1.getDisplayName()) > Wrapper.fr.getStringWidth(m2.getDisplayName())) {
		        	return -1;
		        }
		        if (Wrapper.fr.getStringWidth(m1.getDisplayName()) < Wrapper.fr.getStringWidth(m2.getDisplayName())) {
		        	return 1;
		        }
		        return 0;
	    	} 
	    });
		
		int count = 2;
		int addiable = fr.FONT_HEIGHT + 1;
		for(Module m : Atlas.instance.moduleManager.getMods()) {
			if(m.getAnimation() != -1 && !m.isCategory(Category.GUI)) {
				fr.drawStringWithShadow(m.getDisplayName(), 
										sr.getScaledWidth() - m.getAnimation(), 
										count, 
										Color.HSBtoRGB(hue1, 0.5F, 1F)
				);
				count += Math.min(addiable, m.getAnimation());
				
				hue1 += 0.05f;
	      		if(hue1 >= 255.0F) {
	      			hue1 = 0.0F;
	      		}
				
	      		// Anim
				for(int i = 0; i < 1; i++) { if(m.isToggled()) { if(m.getAnimation() < fr.getStringWidth(m.getDisplayName()) + 2) { m.setAnimation(m.getAnimation() + 2); } if(m.getAnimation() > fr.getStringWidth(m.getDisplayName()) + 2) { m.setAnimation(fr.getStringWidth(m.getDisplayName()) + 1); } }else { if(m.getAnimation() > -1) { m.setAnimation(m.getAnimation() - 1); } } }
			}
		}
	}
	
	public void drawStringRBW(String s, int x, int y, float brightness) {
		int xx = x;
		for(int i = 0; i < s.length(); i++) {
			String sdd = s.charAt(i) + "";
			Wrapper.fr.drawStringWithShadow(sdd, xx, y, RainbowUtils.effect(i * 3500000L, brightness, 100).getRGB());
			xx += Wrapper.fr.getStringWidth(sdd);
		}
	}
	
	@Override
	public void onEnable() {
		Display.setTitle(Atlas.instance.name + " v" + Atlas.instance.ver + " | by " + Atlas.instance.author);
		
		if(tabgui == null) 
			tabgui = new TabGui();
		
		Atlas.instance.eventManager.register(tabgui);		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		Display.setTitle("Paladium - " + mc.getSession().getUsername() + "");
		
		if(tabgui == null) 
			tabgui = new TabGui();
		
		Atlas.instance.eventManager.unregister(tabgui);
		super.onDisable();
	}

}
