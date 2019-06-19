package me.xtrm.Atlas.main;

import cpw.mods.fml.common.FMLCommonHandler;
import me.xtrm.Atlas.eventbus.EventTransmitter;
import me.xtrm.Atlas.hooks.HooksManager;
import me.xtrm.Atlas.module.modules.render.xray.XRayTickHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxyZ extends CommonProxyZ {
	
	@Override
	public void registerRenders() {
		EventTransmitter eventTransmitter = new EventTransmitter();
		HooksManager hm = new HooksManager();
	    XRayTickHandler xray = new XRayTickHandler();
	    
		MinecraftForge.EVENT_BUS.register(eventTransmitter);
	    FMLCommonHandler.instance().bus().register(eventTransmitter);
	    
	    MinecraftForge.EVENT_BUS.register(hm);
	    FMLCommonHandler.instance().bus().register(hm);
	    
	    MinecraftForge.EVENT_BUS.register(xray);
	    FMLCommonHandler.instance().bus().register(xray);
	    
		super.registerRenders();
	}

}
