package me.xtrm.Atlas.guis.click.elements;

import me.xtrm.Atlas.guis.click.objects.BasicElement;
import me.xtrm.Atlas.guis.click.objects.ModuleButton;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.Wrapper;

public class SpacerElement extends BasicElement {

	public SpacerElement(Setting set, ModuleButton parent) {
		super(set, parent);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		totalheight = height;
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		Wrapper.fr.drawStringWithShadow(set.getLabel(), (int)x + 2, (int)y + 2, -1);
	}
	
}
