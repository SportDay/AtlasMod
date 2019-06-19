package me.xtrm.Atlas.guis.click.elements;

import me.xtrm.Atlas.guis.click.objects.BasicElement;
import me.xtrm.Atlas.guis.click.objects.ModuleButton;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.ColorUtils;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.Wrapper;

public class ComboElement extends BasicElement {
	
	private boolean extended;
	
	public ComboElement(Setting set, ModuleButton parent) {
		super(set, parent);
		extended = false;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {		
		if(this.extended) {
			totalheight = height + (set.getOptions().size() * height);
			GuiUtils.getInstance().drawRectDouble(x, y, x + width, y + totalheight, 0xA0000000);
			
			int count = 0;
			for(String s : set.getOptions()) {
				if(isHoveredCoords(x, y + height + (count * height), x + width, y + height + ((count + 1) * height), mouseX, mouseY) && !isHoveredCoords(x, y - height + (count * height), x + width, y + ((count + 1) * height), mouseX, mouseY)) {
					GuiUtils.getInstance().drawRectDouble(x, y + height + (count * height), x + width, y + height + ((count + 1) * height), 0x35000000);
					Wrapper.fr.drawStringWithShadow("<", (int)(x + width - 10), (int)(y + height + 2 + (count * height)), ColorUtils.getMainColor().getRGB());
				}
				
				Wrapper.fr.drawStringWithShadow(s, (int)x + 5, (int)(y + height + 2 + (count * height)), set.string().equalsIgnoreCase(s) ? ColorUtils.getMainColor().getRGB() : 0xFFFFFF);
				count++;
			}
			
			GuiUtils.getInstance().drawRectDouble(x, y + height, x + 2, y + totalheight, ColorUtils.getMainColor().getRGB());
		}else {
			totalheight = height;
			GuiUtils.getInstance().drawRectDouble(x, y, x + width, y + totalheight, 0xA0000000);
		}
		
		if(isHovered(mouseX, mouseY) && !isHoveredUp(mouseX, mouseY))
			GuiUtils.getInstance().drawRectDouble(x, y, x + width, y + height, 0x35000000);
		
		Wrapper.fr.drawStringWithShadow(set.getName(), (int)(x + width / 2 - Wrapper.fr.getStringWidth(set.getName()) / 2), (int)y + 2, 0xFFFFFF);
		Wrapper.fr.drawStringWithShadow(extended ? "-" : "+", (int)(x + width - 8), (int)y + 2, 0xFFFFFF);
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {		
		if(isHoveredCoords(x + 1, y + 1, x + width - 1, y + height - 1, mouseX, mouseY) && !isHoveredUp(mouseX, mouseY) && (mouseButton == 1 || mouseButton == 0)) {
			this.extended = !extended;
			return true;
		}
		int count = 0;
		for(String s : set.getOptions()) {
			if(isHoveredCoords(x, y + height + (count * height), x + width, y + height + ((count + 1) * height), mouseX, mouseY) && !isHoveredCoords(x, y - height + (count * height), x + width, y + ((count + 1) * height), mouseX, mouseY))
				set.setValString(s);
			
			count++;
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}
