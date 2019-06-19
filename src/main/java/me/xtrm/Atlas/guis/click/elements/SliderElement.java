package me.xtrm.Atlas.guis.click.elements;

import me.xtrm.Atlas.guis.click.objects.BasicElement;
import me.xtrm.Atlas.guis.click.objects.ModuleButton;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.ColorUtils;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.Wrapper;

public class SliderElement extends BasicElement {

	public SliderElement(Setting set, ModuleButton parent) {
		super(set, parent);
	}
	
	public boolean dragging;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		totalheight = height;
		
		double pv = (this.set.doubl() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
		
//		ChatUtils.sendMessage(set.getUnlocalizedName() + " pv = " + pv);
		
		GuiUtils.getInstance().drawRectDouble(x, y + 1, x + width, y + height - 1, 0xAA000000);
		GuiUtils.getInstance().drawRectDouble(x, y + 1, x + width * pv, y + height - 1, ColorUtils.getMainColor().darker().hashCode());
		
		if(dragging) {
			double val = set.getMin() + ((mouseX - this.x) / this.width) * (set.getMax() - set.getMin());
			
			if(val > set.getMax())
				val = set.getMax();
			if(val < set.getMin())
				val = set.getMin();
			
			this.set.setValDouble(val);
		}
		
		double lol = Math.round(set.doubl() * 100);
		Wrapper.fr.drawStringWithShadow(set.getName() + ": " + (lol / 100), (int)x + 3, (int)y + 2, 0xFFFFFF);
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {		
		if(isHoveredCoords(x + 1, y + 1, x + width - 1, y + height - 1, mouseX, mouseY) && !isHoveredUp(mouseX, mouseY) && mouseButton == 0) {
			dragging = true;
		}
		
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	public void mouseReleased() {
		dragging = false;
	}

}
