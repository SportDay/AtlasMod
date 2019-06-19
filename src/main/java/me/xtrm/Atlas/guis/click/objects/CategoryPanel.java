package me.xtrm.Atlas.guis.click.objects;

import java.util.ArrayList;

import me.xtrm.Atlas.guis.click.ClickGUI;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.module.Module.Category;
import me.xtrm.Atlas.utils.ColorUtils;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.Wrapper;

public class CategoryPanel {
	
	public Category cat;
	public double x, x2, y, y2, width, height;
	public double extendedHeight;
	public boolean dragging;
	public boolean extended;
	public ArrayList<ModuleButton> elements = new ArrayList<ModuleButton>();
	public ClickGUI parent;
	
	public CategoryPanel(Category cat, double x, double y, double width, double height, ClickGUI parent) {
		this.cat = cat;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.extendedHeight = 0;
		this.parent = parent;
		this.extended = false;
	}
	
	public void setup() {
		int offset = 0;
		for(Module m : Atlas.instance.moduleManager.getModsInCategory(cat)) {
			elements.add(new ModuleButton(m, (int)x, (int)(y + height + offset), (int)width, 12, this));
			offset += 14;
		}
		
		for(ModuleButton mb : elements) {
			mb.setup();
		}
	}

	private int animation = 0;
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		if (this.dragging) {
			x = x2 + mouseX;
			y = y2 + mouseY;
		}
		
		String title = Character.toUpperCase(cat.name().toLowerCase().charAt(0)) + cat.name().toLowerCase().substring(1);
		
		if(!this.extended) {
			for(int i = 0; i < 4; i++) {
				if(extendedHeight > 0)
					extendedHeight--;
			}
		}else {
			int allheight = 0;
			for(ModuleButton mb : elements) {
				allheight += mb.height;
			}
			for(int i = 0; i < 4; i++) {
				if(extendedHeight < allheight)
					extendedHeight++;
				if(extendedHeight > allheight)
					extendedHeight--;
			}
		}
		
		int maincolor = ColorUtils.getMainColor().getRGB();
		int secondColor = ColorUtils.getMainColor().getRGB();
		
		GuiUtils.getInstance().drawRectDouble(x, y + height - 1, x + width, y + height, secondColor);
		GuiUtils.getInstance().drawRectDouble(x + 1, y + height - 1, x + width - 1, y + height + extendedHeight, 0xAA000000);
		GuiUtils.getInstance().drawRectDouble(x, y, x + width, y + height, maincolor);
		GuiUtils.getInstance().drawRectDouble(x, y + height + extendedHeight, x + width, y + height + extendedHeight + 2, maincolor);
		Wrapper.fr.drawStringWithShadow(title, (int)x + 4, (int)y + 3, 0xFFFFFF);
		
//		Wrapper.fr.drawStringWithShadow(this.extended ? "-" : "+", x + width - 5, y + 3, 0xFFFFFF);
		
		GuiUtils.getInstance().drawRectDouble(x + width - 9, y + 3 + (animation / 2), x + width - 6, y + height - 3 - (animation / 2), 0xFFFFFFFF);
		GuiUtils.getInstance().drawRectDouble(x + width - 12, y + height / 2 - 1, x + width - 3, y + height / 2 + 2, 0xFFFFFFFF);
		
		if(isHoveredCoords((int)x + (int)width - 13, (int)y + 2, (int)x + (int)width - 2, (int)y + (int)height - 2, mouseX, mouseY)) {
			GuiUtils.getInstance().drawRectDouble((int)x + (int)width - 13, (int)y + 2, (int)x + (int)width - 2, (int)y + (int)height - 2, 0x660A0A0A);
		}
		
		if(this.extended) {
			if(animation < 6)
				animation++;
		}else {
			if(animation > 0)
				animation--;
		}
		
		double lastOffset = 0;
		int count = 0;
		for(ModuleButton mb : elements) {
			if(extendedHeight / lastOffset >= count + 1) {
				mb.visible = true;
				
				mb.x = x;
				mb.y = y + height + (count * (lastOffset = mb.height));
				mb.drawScreen(mouseX, mouseY, partialTicks);
			}else {
				mb.visible = false;
			}
			count++;
		}
	}
	
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(mouseButton == 0 && isHoveredCoords((int)x + (int)width - 13, (int)y + 2, (int)x + (int)width - 2, (int)y + (int)height - 2, mouseX, mouseY)) {
			extended = !extended;
			return true;
	  	} else if (mouseButton == 0 && isHovered(mouseX, mouseY)) {
			x2 = this.x - mouseX;
			y2 = this.y - mouseY;
			dragging = true;
			return true;			
		} else if (mouseButton == 1 && isHovered(mouseX, mouseY)) {
			extended = !extended;
			return true;
		} else if (extended) {
			for (ModuleButton et : elements) {
				if (et.mouseClicked(mouseX, mouseY, mouseButton)) {
					return true;
				}
			}
		}
		return false;
	}

	public void mouseReleased() {
		for (ModuleButton mb : elements) {
			mb.mouseReleased();
		}
		dragging = false;
	}

	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
	
	//Shit way of removing double selection
	public boolean isHoveredCoords(int x, int y, int x2, int y2, int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
	}
	
}
