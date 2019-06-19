package me.xtrm.Atlas.guis.click.elements;

import me.xtrm.Atlas.guis.click.objects.BasicElement;
import me.xtrm.Atlas.guis.click.objects.ModuleButton;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.ColorUtils;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.Wrapper;

public class CheckboxElement extends BasicElement {
	
	public CheckboxElement(Setting set, ModuleButton parent) {
		super(set, parent);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		totalheight = height;
		
		GuiUtils.getInstance().drawRectDouble(x + 1, y + 1, x + 10, y + height - 1, set.bool() ? ColorUtils.getMainColor().darker().hashCode() : 0xFF010101);
		if(isHoveredCoords(x + 1, y + 1, x + 10, y + height - 1, mouseX, mouseY))
			GuiUtils.getInstance().drawRectDouble(x + 1, y + 1, x + 10, y + height - 1, 0x35010101);
		
		Wrapper.fr.drawStringWithShadow(set.getName(), (int)x + 15, (int)y + 2, 0xFFFFFF);
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(isHoveredCoords(x + 1, y + 1, x + 10, y + height - 1, mouseX, mouseY) && !isHoveredUp(mouseX, mouseY) && mouseButton == 0) {
			set.setValBoolean(!set.bool());
			return true;
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

}
