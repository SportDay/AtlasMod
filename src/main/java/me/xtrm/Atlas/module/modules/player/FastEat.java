package me.xtrm.Atlas.module.modules.player;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastEat extends Module {

	public FastEat() {
		super("FastEat", Category.Player);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(mc.thePlayer.isEating() && mc.thePlayer.getItemInUse() != null && mc.thePlayer.getItemInUse().getItem() instanceof ItemFood && mc.thePlayer.fallDistance < 3F) {
			for(int i = 0; i < 8; i++){
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(mc.thePlayer.onGround));
			}
		}
			
	}

}
