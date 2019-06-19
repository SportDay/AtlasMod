package me.xtrm.Atlas.module.modules.combat;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.EnumChatFormatting;

public class KillAura extends Module {
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.Combat);
		
		addSetting(new Setting("CPS", this, 10D, 0D, 20D, true));
		addSetting(new Setting("Reach", this, 6D, 0D, 6D, true));
		addSetting(new Setting("BlockHit", this, true));
	}
	
	private int delay = 0;
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if(gsu("CPS").doubl() == 0)
			return;
		
		try {
			int i = 0;
			
			for(Object o : mc.theWorld.loadedEntityList) {
				if(o instanceof EntityLivingBase) {
					EntityLivingBase e = (EntityLivingBase)o;
					if(e != null && e != mc.thePlayer && !e.isInvisible() && e.isEntityAlive()) {
						if(mc.thePlayer.getDistanceToEntity(e) <= gsu("Reach").doubl() + 1) {
							if ((gsu("BlockHit").bool() && (mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword)) || mc.thePlayer.isBlocking()) {
//								mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement());
					            mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.getHeldItem());
					        }
							if(mc.thePlayer.getDistanceToEntity(e) <= gsu("Reach").doubl()) {
								delay++;
								if(delay >= 20 / gsu("CPS").doubl()) {
									delay = 0;
									
									mc.thePlayer.swingItem();
									mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(e, C02PacketUseEntity.Action.ATTACK));
									
									i++;
								}
							}
						}
					}
				}
			}
			
			this.setDisplayName(this.getName() + EnumChatFormatting.GRAY + " " + i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
