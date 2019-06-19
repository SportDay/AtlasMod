package me.xtrm.Atlas.rpc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.gui.GuiScreen;

public class RPCManager {
	
	private Thread thread;
	private DiscordRPC lib;
	private DiscordEventHandlers handlers;
	private DiscordRichPresence presence;
	
	private long startTimestamp;
	
	public RPCManager() {
		Atlas.instance.eventManager.register(this);
		
		startTimestamp = System.currentTimeMillis();
	}
	
	public void init() {	
		lib = DiscordRPC.INSTANCE;
        String applicationId = "472506778651852800";
        String steamId = "";
        handlers = new DiscordEventHandlers();
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        
        presence = new DiscordRichPresence();
        updateRPC("In menus", "Clicking buttons");
        
        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                	Atlas.instance.rpcManager.lib.Discord_Shutdown();
                }
            }
        }, "RPC-Callback-Handler");
        thread.start();
	}
	
	private int last;
	private GuiScreen lastGui;
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(Wrapper.mc.currentScreen != lastGui) {
			lastGui = Wrapper.mc.currentScreen;
			if(lastGui == null) {
				last = -999;
			}
		}
		
		int total = 0;
		int i = 0;
		for(Module m : Atlas.instance.moduleManager.getMods()) {
			total++;
			if(m.isEnabled()) {
				i++;
			}
		}

		if(last != i) {
			last = i;
			
			boolean b = Wrapper.mc.isSingleplayer();
			
			updateRPC("InGame - " + (b ? "Solo" : "Paladium"), i + "/" + total + " modules enabled.");
		}
	}
	
	public void updateRPC(String details, String status) {
		presence.largeImageKey = "atlaslogo";
        presence.largeImageText = "Atlas V" + Atlas.instance.ver;
        presence.smallImageKey = "logo-xtrm";
        presence.smallImageText = "by xTrM_";
        presence.startTimestamp = startTimestamp;
        
		presence.details = details;
        presence.state = status;
        
        lib.Discord_UpdatePresence(presence);
	}
	
	public void ripThread() {
		thread.interrupt();
		thread = null;
	}

}