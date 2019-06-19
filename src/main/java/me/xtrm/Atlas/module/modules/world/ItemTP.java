package me.xtrm.Atlas.module.modules.world;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.Location;
import me.xtrm.Atlas.utils.tpaura.TPCore;
import me.xtrm.Atlas.utils.tpaura.TPPath;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.network.play.client.C03PacketPlayer;

public class ItemTP extends Module {

	public ItemTP() {
		super("ItemTP_BETA", Category.World);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		try {
			for(Object o : mc.theWorld.loadedEntityList) {
				if(o instanceof EntityItem) {
					EntityItem ei = (EntityItem)o;
					
					if(ei.delayBeforeCanPickup > 1 || !ei.onGround)
						return;
					
					Location ppos = new Location(mc.thePlayer);
					Location ipos = new Location(ei.boundingBox);
					
					if(ipos.getDistanceToEntity(mc.thePlayer) > 20)
						return;
					
					TPPath path = TPCore.getTPPathToEntity(ei);
					
					for(int i = 0; i < path.getTPTimes(); i++) {
						Location pos = path.getLatestTPPos();
						mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(pos.getX(), pos.getY() - (mc.thePlayer.posY - mc.thePlayer.boundingBox.minY), pos.getY(), pos.getZ(), true));
					}
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(ipos.getX(), ipos.getY() - (mc.thePlayer.posY - mc.thePlayer.boundingBox.minY), ipos.getY(), ipos.getZ(), true));
					
					for(int i = 0; i < 10; i++)
						mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(ipos.getX(), ipos.getY() - (mc.thePlayer.posY - mc.thePlayer.boundingBox.minY), ipos.getY(), ipos.getZ(), true));
					
					for(int i = 0; i < path.getTPTimes(); i++) {
						Location pos = path.getLatestBackPos();
						mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(pos.getX(), pos.getY() - (mc.thePlayer.posY - mc.thePlayer.boundingBox.minY), pos.getY(), pos.getZ(), true));
					}
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(ppos.getX(), ppos.getY() - (mc.thePlayer.posY - mc.thePlayer.boundingBox.minY), ppos.getY(), ppos.getZ(), true));
				}
			}
		} catch (Exception e) {}
	}

}
