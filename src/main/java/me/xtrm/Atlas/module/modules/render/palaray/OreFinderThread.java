package me.xtrm.Atlas.module.modules.render.palaray;

import java.util.HashMap;

import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.ChatUtils;
import me.xtrm.Atlas.utils.Location;
import net.minecraft.client.Minecraft;

public class OreFinderThread implements Runnable {

	private HashMap<Location, Integer> ores;
	
	public int count;
	
	public boolean finished, started;
	
	public OreFinderThread() {
		ores = new HashMap<Location, Integer>();
		count = 0;
		finished = started = false;
	}
	
	@Override
	public void run() {
		if(!started) {
			started = true;
			scanOres();
		}
	}
	
	public void scanOres() {
		Minecraft mc = Minecraft.getMinecraft();
		
		Module oreFinder = Atlas.instance.moduleManager.getModule("OreFinder");
		int radius = (int)oreFinder.gsu("Radius").doubl();
		
		HashMap<Location, Integer> xres = new HashMap<Location, Integer>();
		
		count = 0;
		for(int x = 0; x < (radius * 2); x++) {
			for(int y = 0; y < (radius * 2); y++) {
				for(int z = 0; z < (radius * 2); z++) {
					int px = (int) mc.thePlayer.posX;
					int py = (int) mc.thePlayer.posY;
					int pz = (int) mc.thePlayer.posZ;
					
					Location loc = new Location(((px - radius) + x), ((py - radius) + y), ((pz - radius) + z));
					if(oreFinder.gsu("Paladium").bool() && mc.theWorld.getBlock(((px - radius) + x), ((py - radius) + y), ((pz - radius) + z)).getUnlocalizedName().equalsIgnoreCase("tile.paladium_ore")) { // tile.paladium_ore
						xres.put(loc, 0xFFF68F43);
						count++;
					}
					if(oreFinder.gsu("Diamond").bool() && mc.theWorld.getBlock(((px - radius) + x), ((py - radius) + y), ((pz - radius) + z)).getUnlocalizedName().equalsIgnoreCase("tile.oreDiamond")) {
						xres.put(loc, 0xFF11DBCD);
						count++;
					}
					if(oreFinder.gsu("Amethyste").bool() && mc.theWorld.getBlock(((px - radius) + x), ((py - radius) + y), ((pz - radius) + z)).getUnlocalizedName().equalsIgnoreCase("tile.amethyst_ore")) {
						xres.put(loc, 0xFF7e04f7);
						count++;
					}
					if(oreFinder.gsu("Titane").bool() && mc.theWorld.getBlock(((px - radius) + x), ((py - radius) + y), ((pz - radius) + z)).getUnlocalizedName().equalsIgnoreCase("tile.oreIron")) {
						xres.put(loc, 0xFF828180);
						count++;
					}
					if(oreFinder.gsu("Findium").bool() && mc.theWorld.getBlock(((px - radius) + x), ((py - radius) + y), ((pz - radius) + z)).getUnlocalizedName().equalsIgnoreCase("tile.findium_ore")) {
						xres.put(loc, 0xFFf4c242);
						count++;
					}
				}	
			}	
		}
		ores.clear();
		for(Location l : xres.keySet()) {
			ores.put(l, xres.get(l));
		}
//		ores = xres;
		finished = true;
	}
	
	public HashMap<Location, Integer> getOres(){
		if(!finished || !started)
			return null;
		return ores;
	}
	
	public void reset() {
		ChatUtils.sendMessage("Resetted");
		finished = started = false;
	}

}
