package me.xtrm.Atlas.module.modules.combat;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.Location;
import me.xtrm.Atlas.utils.TimeHelper;
import me.xtrm.Atlas.utils.tpaura.TPCore;
import me.xtrm.Atlas.utils.tpaura.TPPath;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public class TPAura extends Module {
	
	public TPAura() {
		super("TPAura", Category.Combat);
		
		addSetting(new Setting("Label", this, "Reach>20 = tp"));
		addSetting(new Setting("CPS", this, 10D, 0D, 20D, true));
		addSetting(new Setting("Reach", this, 10D, 0D, 100D, true));
	}
	
	private TimeHelper timer = new TimeHelper();
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if(gsu("CPS").doubl() == 0)
			return;
		
		if(timer.hasReached((long) (1000 / gsu("CPS").doubl()))) { // Every tick 
			timer.reset();
			
			try {
				for(Object o : mc.theWorld.loadedEntityList) {
					if(o instanceof EntityLivingBase) {
						EntityLivingBase e = (EntityLivingBase)o;
						if(e != null && e != mc.thePlayer && !e.isInvisible() && !e.isEntityInvulnerable() && mc.thePlayer.getDistanceToEntity(e) <= gsu("Reach").doubl() && e.isEntityAlive()) {
							TPPath path = TPCore.getTPPathToEntity(e);
							if(path != null) {
								
								//Locations
								Location ppos = new Location(mc.thePlayer);
								Location epos = new Location(e);
								
								//1st TP Wave
								for(int i = 0; i < path.getTPTimes(); i++) {
									Location pos = path.getLatestTPPos();
									mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(pos.getX(), pos.getY(), pos.getY(), pos.getZ(), true));
								}
								
								//1st TP Final
								mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(epos.getX(), epos.getY(), epos.getY(), epos.getZ(), true));
								
								//Attack
								mc.thePlayer.swingItem();
								mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(e, C02PacketUseEntity.Action.ATTACK));
								
								//2nd TP Wave
								for(int i = 0; i < path.getTPTimes(); i++) {
									Location pos = path.getLatestBackPos();
									mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(pos.getX(), pos.getY(), pos.getY(), pos.getZ(), true));
								}
								
								//2nd TP Final
								mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(ppos.getX(), ppos.getY(), ppos.getY(), ppos.getZ(), true));
							}
						}
					}
				}
			} catch (Exception e) {}
		}
	}

}
