package me.xtrm.Atlas.tabgui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.other.EventKeyPressed;
import me.xtrm.Atlas.eventbus.events.render.EventRender2D;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.module.Module.Category;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class TabGui extends GuiScreen {
	
	public int selectedCat = 0;
	public int selectedMod = 0;
	public int catTargetY = 0;
	public int modTargetY = 0;
	public int modSelectorX = 0, modSelectorY = -666;
	public int x, y, width, height;
	public int catSelectorX = 0, catSelectorY = -666;
	
	public Category selectedCategory;
	
	public boolean collapsed = false;
	
	public ArrayList<Module> modules = new ArrayList<Module>();
	
	@EventTarget
	public void onRender(EventRender2D event) {
		if(!Atlas.instance.moduleManager.getModule("HUD").gsu("TabGui").bool())
			return;
		
		x = 3;
		y = 18;
		width = 78;
		height = 12;
		catSelectorX = x + 1;
		
		int modX = x + width + 2;
		
		int catCount = 0;
		for(Category c : Category.values()){
			if(!c.equals(Category.GUI)){
				catCount++;
			}
		}
		
		if(selectedCat > catCount - 1){
			selectedCat = 0;
		}
		
		if(selectedCat < 0){
			selectedCat = catCount - 1;
		}
		
		selectedCategory = Category.values()[selectedCat];
		
		catTargetY = y + (selectedCat * height);
		
		if(catSelectorY == -666)
			catSelectorY = catTargetY;
		
		if(catSelectorY < catTargetY){
			catSelectorY++;
		}
		if(catSelectorY > catTargetY){
			catSelectorY--;
		}
		if(catSelectorY < catTargetY){
			catSelectorY++;
		}
		if(catSelectorY > catTargetY){
			catSelectorY--;
		}
		
		int count1 = 0;
		for(Category c : Category.values()){
			if(!c.equals(Category.GUI)){
				drawRect(x, y + (count1 * height), x + width, y + (count1 + 1) * height, 0x55000000);
				count1++;
			}
		}
		
//		CategorySelector
		
		GuiUtils.getInstance().drawBorderedRect(catSelectorX - 1, catSelectorY, width + 3, catSelectorY + height, 0xEE000000, 0xFF4286f4);
		
		int count2 = 0;
		for(Category c : Category.values()){
			if(!c.equals(Category.GUI)){
				String name = c.name().substring(0, 1).toUpperCase() + c.name().substring(1).toLowerCase();
				Wrapper.fr.drawStringWithShadow(name, x + (Category.values()[selectedCat].name().equalsIgnoreCase(c.name()) ? 6 : 3), y + (count2 * height) + 2, 0xFFFFFF);
				count2++;
			}
		}
		
		GuiUtils.getInstance().drawBorderedRect(x, y, width + 3, y + (count1) * height, 0xEE000000, 0x00000000);
		
		if(collapsed){
			Collections.sort(Atlas.instance.moduleManager.getMods(), new Comparator<Module>() {
		    	public int compare(Module m1, Module m2) {
			        if (Wrapper.fr.getStringWidth(m1.getName()) > Wrapper.fr.getStringWidth(m2.getName())) {
			        	return -1;
			        }
			        if (Wrapper.fr.getStringWidth(m1.getName()) < Wrapper.fr.getStringWidth(m2.getName())) {
			        	return 1;
			        }
			        return 0;
		    	}
		    });
			
			int modCount = 0;
			int maxWidth = 0;
			
			modules.clear();
			for(Module m : Atlas.instance.moduleManager.getMods()){
				if(m.getCategory() == selectedCategory){
					modules.add(m);
				}
			}
			
			for(Module m : modules){
				if(m.isCategory(selectedCategory)){
					if(Wrapper.fr.getStringWidth(m.getName()) > maxWidth){
						maxWidth = Wrapper.fr.getStringWidth(m.getName());
					}
					modCount++;
				}
			}
			
			maxWidth += 10;
			
			if(selectedMod > modCount - 1){
				selectedMod = 0;
			}
			
			if(selectedMod < 0){
				selectedMod = modCount - 1;
			}
			
			int selectedCatY = selectedCat * height;
			modTargetY = y + selectedCatY + (selectedMod * height);
			
			if(modSelectorY < modTargetY){
				modSelectorY++;
			}
			if(modSelectorY > modTargetY){
				modSelectorY--;
			}
			if(modSelectorY < modTargetY){
				modSelectorY++;
			}
			if(modSelectorY > modTargetY){
				modSelectorY--;
			}
			
			int count3 = 0;
			for(Module m : modules){
				if(m.isCategory(selectedCategory)){
					drawRect(modX, y + selectedCatY + (count3 * height), modX + maxWidth + 1, y + selectedCatY + (count3 + 1) * height, 0x55000000);
					count3++;
				}
			}
			
//			ModSelector
			GuiUtils.getInstance().drawBorderedRect(modX + modSelectorX, modSelectorY, modX + maxWidth + 1, modSelectorY + height, 0xEE000000, 0xFF4286f4);
			
			int count4 = 0;
			for(Module m : modules){
				if(m.isCategory(selectedCategory)){
					Wrapper.fr.drawStringWithShadow((m.isEnabled() ? EnumChatFormatting.WHITE : EnumChatFormatting.GRAY) + m.getName(), modX + (modules.get(selectedMod).getName().equalsIgnoreCase(m.getName()) ? 6 : 3), y + selectedCatY + (count4 * height) + 2, 0xFFFFFF);
					count4++;
				}
			}
			if(tog){
				tog = false;
				modules.get(selectedMod).toggle();
			}
		}
	}
	
	boolean tog = false;
	boolean c = false;
	boolean c2 = false;

	@EventTarget
	public void onKey(EventKeyPressed e) {
		if(!Atlas.instance.moduleManager.getModule("HUD").gsu("TabGui").bool())
			return;
		
		int key = e.key;
		if(!collapsed){
			if(key == Keyboard.KEY_UP){
				selectedCat--;
			}
			if(key == Keyboard.KEY_DOWN){
				selectedCat++;
			}
			if(key == Keyboard.KEY_RETURN || key == Keyboard.KEY_RIGHT){
				collapsed = true;
				modSelectorY = y + (selectedCat * height) + (selectedMod * height);
			}
		}else{
			if(key == Keyboard.KEY_UP){
				selectedMod--;
			}
			if(key == Keyboard.KEY_DOWN){
				selectedMod++;
			}
			if(key == Keyboard.KEY_RETURN || key == Keyboard.KEY_RIGHT){
				tog = true;
			}
			if(key == Keyboard.KEY_LEFT){
				collapsed = false;
			}
		}
	}

}
