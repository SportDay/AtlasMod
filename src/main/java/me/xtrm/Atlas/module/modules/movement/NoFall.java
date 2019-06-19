package me.xtrm.Atlas.module.modules.movement;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {
	
	public NoFall() {
		super("NoFall", Category.Movement);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(mc.thePlayer.fallDistance >= 2F) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
	}

}
