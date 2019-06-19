package me.xtrm.Atlas.guis.click.elements.cbuttons;

import me.xtrm.Atlas.guis.click.objects.BasicElement;
import me.xtrm.Atlas.guis.click.objects.ModuleButton;
import me.xtrm.Atlas.module.modules.player.spammer.GuiChangeMsg;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.Wrapper;

public class SpammerConfigElement extends BasicElement {

	public SpammerConfigElement(ModuleButton parent) {
		super(null, parent);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		totalheight = height;
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		GuiUtils.getInstance().drawRectDouble(x + 2, y + 1, x + width - 2, y + height - 1, 0xAA000000);
		Wrapper.fr.drawStringWithShadow("Config", (int)(x + (width / 2)) - (Wrapper.fr.getStringWidth("Config") / 2), (int)y + 2, 0xFFFFFF);
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		
		if(this.isHovered(mouseX, mouseY) && !this.isHoveredUp(mouseX, mouseY)) {
			if(mouseButton == 0) {
				Wrapper.mc.displayGuiScreen(new GuiChangeMsg());
			}
		}
		
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

}
