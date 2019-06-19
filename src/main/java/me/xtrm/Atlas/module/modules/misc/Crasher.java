package me.xtrm.Atlas.module.modules.misc;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;

public class Crasher extends Module {
	
	public Crasher() {
		super("Crasher", Category.Miscellaneous);
	}

	@EventTarget
	public void onUpdate(EventUpdate e) {
//		if(this.getModule().getSetting("TestMode").booleanValue()) {
//			for(int i = 0; i < 100; i++) {
//				mc.thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX + (i * 10), mc.thePlayer.posY + (i * 10), mc.thePlayer.posZ + (i * 10)));
//			}
//		}
		mc.thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(9999999, 100, 100, 9999999, false));
		toggle();
	}
}