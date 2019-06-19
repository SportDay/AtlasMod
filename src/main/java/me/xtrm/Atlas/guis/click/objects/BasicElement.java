package me.xtrm.Atlas.guis.click.objects;

import me.xtrm.Atlas.guis.click.ClickGUI;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.GuiUtils;

public class BasicElement {
	
	public ClickGUI clickgui;
	public ModuleButton parent;
	
	public boolean visible;
	
	public Setting set;
	public double offset;
	public double x;
	public double y;
	public double width;
	public double height;
	
	public double totalheight;
	
	public String setstrg;

	public BasicElement(Setting set, ModuleButton parent) {
		this.set = set;
		this.parent = parent;
	}
	
	public void setup() {		
		clickgui = parent.parent.parent;
		if(set != null)
			setstrg = set.getName();
		visible = false;
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		GuiUtils.getInstance().drawRect((float)x, (float)y, (float)x + (float)width, (float)y + (float)totalheight, 0xA0000000);
		
		if(isHovered(mouseX, mouseY) && !isHoveredUp(mouseX, mouseY))
			GuiUtils.getInstance().drawRect((float)x, (float)y, (float)x +(float) width, (float)y +(float) height, 0x35000000);
	}
	
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		return false;
	}

	public void mouseReleased() {
		
	}
	
	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
	
	public boolean isHoveredCoords(double x, double y, double x2, double y2, int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
	}
	
	public boolean isHoveredUp(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y - height && mouseY <= y;
	}
}
