package me.xtrm.Atlas.module.modules.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.TimeHelper;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.C01PacketChatMessage;

@SuppressWarnings("unchecked")
public class MassTPA extends Module {

	public MassTPA() {
		super("MassTPA", Category.Miscellaneous);
		addSetting(new Setting("Delay", this, 100D, 0D, 5000D, false));
		addSetting(new Setting("Infinite", this, false));
	}
	
	private List<GuiPlayerInfo> players;
	private TimeHelper timer = new TimeHelper();
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		boolean inf = gsu("Infinite").bool();
		if(players.size() == 0) {
			if(!inf) {
				toggle();
			}else {
				players = new ArrayList<GuiPlayerInfo>();
				
				NetHandlerPlayClient nethandlerplayclient = this.mc.thePlayer.sendQueue;
		        players = nethandlerplayclient.playerInfoList;
		        
		        GuiPlayerInfo removable = null;
		        
		        for(GuiPlayerInfo g : players) {
		        	if(g.name.equalsIgnoreCase(mc.thePlayer.getCommandSenderName())) {
		        		removable = g;
		        	}
		        }
		        
		        if(removable != null)
		        	players.remove(removable);
			}
		}
		
		if(!timer.hasReached((long)gsu("Delay").doubl()))
			return;
		
		timer.reset();
		
		Random r = new Random();
		
		GuiPlayerInfo one = players.get(r.nextInt(players.size()));
		players.remove(one);
		
		String msg = "/tpa " + one.name;
		mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(msg));
	}
	
	@Override
	public void onEnable() {
players = new ArrayList<GuiPlayerInfo>();
		
		NetHandlerPlayClient nethandlerplayclient = this.mc.thePlayer.sendQueue;
        players = nethandlerplayclient.playerInfoList;
        
        GuiPlayerInfo removable = null;
        
        for(GuiPlayerInfo g : players) {
        	if(g.name.equalsIgnoreCase(mc.thePlayer.getCommandSenderName())) {
        		removable = g;
        	}
        }
        
        if(removable != null)
        	players.remove(removable);
        
		super.onEnable();
	}

}
