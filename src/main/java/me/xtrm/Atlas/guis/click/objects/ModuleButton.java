package me.xtrm.Atlas.guis.click.objects;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.guis.click.elements.CheckboxElement;
import me.xtrm.Atlas.guis.click.elements.ComboElement;
import me.xtrm.Atlas.guis.click.elements.SliderElement;
import me.xtrm.Atlas.guis.click.elements.SpacerElement;
import me.xtrm.Atlas.guis.click.elements.cbuttons.*;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.ChatUtils;
import me.xtrm.Atlas.utils.ColorUtils;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.Wrapper;

public class ModuleButton {

	public CategoryPanel parent;
	
	public ArrayList<BasicElement> elements = new ArrayList<BasicElement>();
	
	public Module mod;
	public double x;
	public double y;
	public double width;
	public double height;
	
	public boolean visible;
	public boolean extended;
	public boolean binding;
	
	public ModuleButton(Module mod, int x, int y, int width, int height, CategoryPanel parent) {
		this.mod = mod;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.parent = parent;
		this.visible = false;
//		this.extendedHeight = height;
	}
	
	public void setup() {		
		if(parent.parent.setmgr.getSettingsByMod(mod) == null)
			return;
		
		ArrayList<Setting> settings = parent.parent.setmgr.getSettingsByMod(mod);
		
		// Add settings elements
		for(Setting s : settings) {
			if(s.isCheck()) {
				elements.add(new CheckboxElement(s, this));
			}else if(s.isSlider()) {
				elements.add(new SliderElement(s, this));
			}else if(s.isCombo()) {
				elements.add(new ComboElement(s, this));
			}else {
				elements.add(new SpacerElement(s, this));
			}
		}
		
		if(mod.getName().equalsIgnoreCase("XRay")) {
			elements.add(new XRayConfigElement(this));
		}
		if(mod.getName().equalsIgnoreCase("Spammer")) {
			elements.add(new SpammerConfigElement(this));
		}
		
		for(BasicElement e : elements) {
			e.setup();
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		if(!this.visible) return;
		
		if(isHovered(mouseX, mouseY) && !isHoveredUp(mouseX, mouseY)) {
			GuiUtils.getInstance().drawRectDouble(x + 1, y, x + width - 1, y + height, 0xA0000000);
		}
		
		if(mod.isEnabled()) {
			GuiUtils.getInstance().drawRectDouble(x + 1, y, x + width - 1, y + height, 0xCC0C0C0C);
		}
		
		Wrapper.fr.drawStringWithShadow(mod.getName(), (int)x + 4, (int)y + 2, mod.isEnabled() ? ColorUtils.getMainColor().getRGB() : 0xFFFFFF);
		if(!elements.isEmpty())
			Wrapper.fr.drawStringWithShadow(this.extended ? "-" : "+", (int)(x + width - 9), (int)y + 2, 0xFFFFFF);
		
		if(this.extended) {
			if(elements != null && !elements.isEmpty() && elements.size() > 0) {
				int lastY = 0;
				for(BasicElement be : elements) {
					be.x = x + width;
					be.height = height;
					be.width = width;
					be.y = y + lastY;
					
					be.drawScreen(mouseX, mouseY, partialTicks);
					lastY += be.totalheight;
				}
			}
		}
	}
	
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(!this.visible) return false;
		
		if(isHovered(mouseX, mouseY) && !isHoveredUp(mouseX, mouseY) && mouseButton == 0) {
			mod.toggle();
			return true;
		}else if(isHovered(mouseX, mouseY) && !isHoveredUp(mouseX, mouseY) && mouseButton == 1) {
			if(!extended) {
				if(!this.elements.isEmpty()) {
					for(CategoryPanel cp : parent.parent.panels) {
						for(ModuleButton mb : cp.elements) {
							mb.extended = false;
						}
					}
				}
			}
			this.extended = !extended;
			return true;
		}else if(isHovered(mouseX, mouseY) && !isHoveredUp(mouseX, mouseY) && mouseButton == 2) {
			if(!binding) {
				if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
					ChatUtils.sendMessage("Module " + this.mod.getName() + " Unbind");
					this.mod.setKey(0);
				}else {
					binding = true;
					parent.parent.binding = true;
					parent.parent.currentBinding = this;
				}
			}
		}

		if(extended) {
			for (BasicElement et : elements) {
				if (et.mouseClicked(mouseX, mouseY, mouseButton)) {
					return true;
				}
			}
		}
		return false;
	}

	public void mouseReleased() {
		if(!this.visible) return;
		
		for (BasicElement et : elements) {
			et.mouseReleased();
		}
	}

	public void keyTyped(char typedChar, int keyCode) {
		if(binding) {
			binding = false;
			parent.parent.binding = false;
			parent.parent.currentBinding = null;
			if(keyCode != Keyboard.KEY_ESCAPE) {
				mod.setKey(keyCode);
				ChatUtils.sendMessage("&c" + mod.getName() + "&7's key set to &c" + Keyboard.getKeyName(keyCode));
			}
		}
	}
	
	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
	
	public boolean isHoveredUp(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y - height && mouseY <= y;
	}

}
