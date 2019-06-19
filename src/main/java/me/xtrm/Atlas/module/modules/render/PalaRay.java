package me.xtrm.Atlas.module.modules.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.render.EventRender3D;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.module.modules.render.palaray.OreFinderThread;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.ChatUtils;
import me.xtrm.Atlas.utils.Location;
import me.xtrm.Atlas.utils.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.util.EnumChatFormatting;

public class PalaRay extends Module {
	
	private ScheduledExecutorService exec;
	private OreFinderThread thread;
	
	private HashMap<Location, Integer> ores;
	
	public PalaRay() {
		super("OreFinder", Category.Render);
		
		addSetting(new Setting("Paladium", this, true));
		addSetting(new Setting("Findium", this, false));
		addSetting(new Setting("Titane", this, true));
		addSetting(new Setting("Amethyste", this, true));
		addSetting(new Setting("Diamond", this, true));
		
		addSetting(new Setting("Radius", this, 25, 10, 100, true));
		addSetting(new Setting("UseThreadCalc", this, true));
		
		ores = new HashMap<Location, Integer>();
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(thread != null)
			this.setDisplayName(getName() + " " + EnumChatFormatting.GRAY + thread.count);
		
		if(mc.thePlayer.hurtTime == 5) {
			thread.reset();
		}
	}

	private boolean utc = true;
	
	@SuppressWarnings("unchecked")
	@EventTarget
	public void onRender3D(EventRender3D e) {
		boolean utcx = gsu("UseThreadCalc").bool();
		
		if(utc != utcx) {
			utc = utcx;
			toggle();
			ChatUtils.sendMessage("Réactivez le module");
			return;
		}
		
		if(utc) {
			float fx = e.partialTicks;
			
			if(thread == null)
				return;
			
			if(thread.started && thread.finished) {
				ores = (HashMap<Location, Integer>) thread.getOres().clone();
			}
			ArrayList<Location> locs = new ArrayList<Location>();
			locs.addAll(ores.keySet());

			RenderUtils.init3D();
			for(Location l : locs) {
				int color = ores.get(l);
				RenderUtils.drawOutlinedBlock(fx, l.getX(), l.getY(), l.getZ(), color);
			}
			RenderUtils.reset3D();
			
		}else {
			int radius = (int)gsu("Radius").doubl();
			for(int x = -radius; x<radius; x++) {
				for(int y = -radius; y<radius; y++) {
					for(int z = -radius; z<radius; z++) {
						Location loc = new Location(mc.thePlayer).offset(x, y, z);
						HashMap<Location, Integer> xres = new HashMap<Location, Integer>();
						Block block = mc.theWorld.getBlock((int)loc.getX(), (int)loc.getY(), (int)loc.getZ());
						
						if(gsu("Paladium").bool() && block.getUnlocalizedName().equalsIgnoreCase("tile.paladium_ore")) { // tile.paladium_ore
							xres.put(loc, 0xFFF68F43);
						}
						if(gsu("Diamond").bool() && block.getUnlocalizedName().equalsIgnoreCase("tile.oreDiamond")) {
							xres.put(loc, 0xFF11DBCD);
						}
						if(gsu("Amethyste").bool() && block.getUnlocalizedName().equalsIgnoreCase("tile.amethyst_ore")) {
							xres.put(loc, 0xFF7e04f7);
						}
						if(gsu("Titane").bool() && block.getUnlocalizedName().equalsIgnoreCase("tile.oreIron")) {
							xres.put(loc, 0xFF828180);
						}
						if(gsu("Findium").bool() && block.getUnlocalizedName().equalsIgnoreCase("tile.findium_ore")) {
							xres.put(loc, 0xFFf4c242);
						}
						
						RenderUtils.init3D();
						for(Location l : xres.keySet()) {
							int color = xres.get(l);
							RenderUtils.drawOutlinedBlock(e.partialTicks, l.getX(), l.getY(), l.getZ(), color);
						}
						RenderUtils.reset3D();
					}
				}
			}
		}
	}
	
	@Override
	public void onEnable() {
		if(gsu("UseThreadCalc").bool()) {
			exec = Executors.newSingleThreadScheduledExecutor();
			exec.scheduleAtFixedRate(thread = new OreFinderThread(), 0, 100, TimeUnit.MILLISECONDS);
		}
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		if(exec != null) {
			exec.shutdown();
			exec = null;
		}		
		
		super.onDisable();
	}

}
