package me.xtrm.Atlas.guis.click;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.xtrm.Atlas.guis.click.elements.SliderElement;
import me.xtrm.Atlas.guis.click.objects.BasicElement;
import me.xtrm.Atlas.guis.click.objects.CategoryPanel;
import me.xtrm.Atlas.guis.click.objects.ModuleButton;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module.Category;
import me.xtrm.Atlas.settings.SettingsManager;
import me.xtrm.Atlas.utils.ScaledUtils;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;

public class ClickGUI extends GuiScreen implements GuiYesNoCallback  {
	
	public ArrayList<CategoryPanel> panels;
	public SettingsManager setmgr;
	public boolean binding = false;
	public ModuleButton currentBinding = null;
	
	public ClickGUI() {
		setmgr = Atlas.instance.settingsManager;
		panels = new ArrayList<CategoryPanel>();
		
		double pwidth = 85;
		double pheight = 15;
		double px = 5;
		double py = 10;
		double pxplus = pwidth + 5;
		for(Category c : Category.values()) {
			if(c != Category.GUI) {
				panels.add(new CategoryPanel(c, px, py, pwidth, pheight, this));
				px += pxplus;
			}
		}
		
		for(CategoryPanel p : panels) {
			p.setup();
			p.extended = true;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {		
		ScaledResolution sr = ScaledUtils.gen();
		
		drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0xAA000000);
		
		for(CategoryPanel p : panels) {
			p.drawScreen(mouseX, mouseY, partialTicks);
		}
		
		if(binding && currentBinding != null) {
			drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0xCB000000);
			GL11.glPushMatrix();
			GL11.glScalef(2, 2, 2);
			drawCenteredString(Wrapper.fr, "Binding...", sr.getScaledWidth() / 4, sr.getScaledHeight() / 4 - 20, -1);
			GL11.glPopMatrix();
			if(currentBinding.mod.getKey() != -1 && currentBinding.mod.getKey() != 0) {
				drawCenteredString(Wrapper.fr, "Current bind is " + Keyboard.getKeyName(currentBinding.mod.getKey()) + ".", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 7, -1);
			}else {
				drawCenteredString(Wrapper.fr, "Current bind is NONE.", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 - 7, -1);
			}
			drawCenteredString(Wrapper.fr, "Press [ESCAPE] to get out of here.", sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 + 10, -1);
		}
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		for(CategoryPanel p : panels) {
			if(!binding)
				p.mouseClicked(mouseX, mouseY, mouseButton);
			else if(mouseButton == 2) {
				for(ModuleButton mb : p.elements) {
					mb.mouseClicked(mouseX, mouseY, mouseButton);
				}
			}
		}
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
		
		if(p_146286_3_ != -1) {
			for(CategoryPanel p : panels) {
				if(!binding)
					p.mouseReleased();
			}
		}
		
		super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
	}
	
//	@Override
//	protected void mouseReleased(int mouseX, int mouseY, int state) {
//		for(CategoryPanel p : panels) {
//			if(!binding)
//				p.mouseReleased(mouseX, mouseY, state);
//		}
//		super.mouseReleased(mouseX, mouseY, state);
//	}

	@Override
	public void onGuiClosed() {		
		for(CategoryPanel p : this.panels) {
			for(ModuleButton mb : p.elements) {
				for(BasicElement be : mb.elements) {
					if(be instanceof SliderElement) {
						SliderElement se = (SliderElement)be;
						se.dragging = false;
					}
				}
			}
		}
	}
	
	@Override
	public void keyTyped(char typedChar, int keyCode) {
		for(CategoryPanel p : panels) {
			for(ModuleButton mb : p.elements) {
				mb.keyTyped(typedChar, keyCode);
			}
		}
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public boolean isHovering(int x, int y, int x2, int y2, int mouseX, int mouseY) {
		return mouseX >= x && mouseY >= y && mouseX <= x2 && mouseY <= y2;
	}
	
}
