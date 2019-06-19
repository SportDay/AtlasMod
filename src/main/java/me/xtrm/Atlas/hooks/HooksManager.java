package me.xtrm.Atlas.hooks;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.xtrm.Atlas.guis.CIngameMenu;
import me.xtrm.Atlas.guis.CMainMenu;
import me.xtrm.Atlas.guis.GuiConsole;
import me.xtrm.Atlas.main.Atlas;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.gui.ForgeGuiFactory.ForgeConfigGui;

public class HooksManager {
	
	@SubscribeEvent
	public void onGui(GuiOpenEvent event) {		
		try {			
			if(!(event.gui instanceof GuiChat) && !(event.gui instanceof GuiIngameMenu) && !(event.gui instanceof GuiChest) && !(event.gui instanceof GuiInventory) && !(event.gui instanceof GuiContainerCreative) && !(event.gui instanceof GuiCommandBlock) && !(event.gui instanceof GuiConsole) && !(event.gui instanceof GuiEnchantment) && !(event.gui instanceof GuiRepair) && !(event.gui instanceof GuiInventory) && !(event.gui instanceof GuiCrafting) && !(event.gui instanceof GuiFurnace) && !(event.gui instanceof GuiBeacon) && !(event.gui instanceof GuiDispenser) && !(event.gui instanceof GuiHopper) && !(event.gui instanceof GuiBrewingStand) && !(event.gui instanceof GuiEditSign) && !(event.gui instanceof ForgeConfigGui)) {
				Atlas.instance.rpcManager.updateRPC("In menus", "Clicking buttons");
			}
			
			if((event != null && event.gui != null && event.gui.getClass() != null && event.gui.getClass().getName() != null) && (event.gui instanceof GuiMainMenu || event.gui.getClass().getName().equalsIgnoreCase("fr.paladium.palamod.client.gui.PGuiMainMenu"))) {
//				event.gui = new AionLoadingScreen(new CMainMenu());
				event.gui = new CMainMenu();
			}
			if((event != null && event.gui != null && event.gui.getClass() != null && event.gui.getClass().getName() != null) && (event.gui instanceof GuiIngameMenu || event.gui.getClass().getName().equalsIgnoreCase("fr.paladium.palamod.client.gui.PIngameMenu"))) {
				event.gui = new CIngameMenu();
			}
		} catch (Exception e) { }
	}

}