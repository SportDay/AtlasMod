package me.xtrm.Atlas.module.modules.world;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.render.EventRender3D;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.Location;
import me.xtrm.Atlas.utils.RainbowUtils;
import me.xtrm.Atlas.utils.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

public class Nuker extends Module {

	public Nuker() {
		super("Nuker", Category.World);
		
		addSetting(new Setting("Label1", this, "En survie, utilisez"));
		addSetting(new Setting("Label2", this, "un outil adapté"));
		addSetting(new Setting("Radius", this, 3D, 1D, 5D, true));
	}
	
	private Location loc;
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		int radius = (int)gsu("Radius").doubl();
		
		for(int x = -radius; x<radius; x++) {
			for(int y = -radius; y<radius; y++) {
				for(int z = -radius; z<radius; z++) {
					loc = new Location(mc.thePlayer).offset(x, y, z);
					Block b = mc.theWorld.getBlock((int)loc.getX(), (int)loc.getY(), (int)loc.getZ());
					if(b != null && !b.getMaterial().isLiquid() && Block.getIdFromBlock(b) != 0) {
						mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, (int)loc.getX(), (int)loc.getY(), (int)loc.getZ(), 1));
						if(!mc.thePlayer.capabilities.isCreativeMode) {
							mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(2, (int)loc.getX(), (int)loc.getY(), (int)loc.getZ(), 1));
						}
					}
				}
			}
		}
	}
	
	@EventTarget
	public void onRender3D(EventRender3D event) {
		RenderUtils.init3D();
		{
			RenderUtils.drawOutlinedBlock(event.partialTicks, loc.getX(), loc.getY(), loc.getZ(), RainbowUtils.effect(0, 0.7F, 1).getRGB());
		}
		RenderUtils.reset3D();
	}

}
