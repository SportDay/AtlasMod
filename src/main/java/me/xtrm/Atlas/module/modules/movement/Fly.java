package me.xtrm.Atlas.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.MovementUtils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;

public class Fly extends Module {

	public Fly() {
		super("Fly", Keyboard.KEY_F, Category.Movement);
		
		addSetting(new Setting("Speed", this, 1D, 0D, 10D, false));	
//		addSetting(new Setting("AntiKick", this, false));
	}
	
//	private TimeHelper timer;
	private double flyHeight;
	
	@EventTarget
	public void onUpdate(EventUpdate e) {		
		double speed = gsu("Speed").doubl();
		
		if(MovementUtils.isMoving()) {
			MovementUtils.setSpeed(speed);
		}else {
			MovementUtils.setSpeed(0);
		}
		
		double motion = speed;
		if(speed > 0.58)
			motion -= 0.58;
		
		mc.thePlayer.motionY = 0;
		if(mc.gameSettings.keyBindJump.getIsKeyPressed()) {
			mc.thePlayer.motionY = Math.max(motion, 0);
		}
		if(mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
			mc.thePlayer.motionY = -Math.max(motion, 0);
		}
		if(mc.gameSettings.keyBindSneak.getIsKeyPressed() && mc.gameSettings.keyBindJump.getIsKeyPressed()) {
			mc.thePlayer.motionY = 0;
		}
		
//		if(gsu("AntiKick").bool()) {
//			updateFlyHeight();
//            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
//            if (flyHeight <= 290.0 && timer.hasReached(500)) {
//                goToGround();
//                timer.reset();
//            }
//		}
	}
	
	public void updateFlyHeight() {
        double h = 1.0;
        AxisAlignedBB box = mc.thePlayer.boundingBox.expand(0.0625, 0.0625, 0.0625);
        this.flyHeight = 0.0;
        while (this.flyHeight < mc.thePlayer.posY) {
            AxisAlignedBB nextBox = box.offset(0.0, -this.flyHeight, 0.0);
            if (mc.theWorld.checkBlockCollision(nextBox)) {
                if (h < 0.0625) {
                    break;
                }
                this.flyHeight -= h;
                h /= 2.0;
            }
            this.flyHeight += h;
        }
    }
    
    public void goToGround() {
        if (this.flyHeight > 300.0) {
            return;
        }
        double minY = mc.thePlayer.posY - this.flyHeight;
        if (minY <= 0.0) {
            return;
        }
        double y = mc.thePlayer.posY;
        while (y > minY) {
            y -= 8.0;
            if (y < minY) {
                y = minY;
            }
            C03PacketPlayer.C04PacketPlayerPosition packet = new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, y - (mc.thePlayer.posY - mc.thePlayer.boundingBox.minY), y, mc.thePlayer.posZ, true);
            mc.thePlayer.sendQueue.addToSendQueue(packet);
        }
        y = minY;
        while (y < mc.thePlayer.posY) {
            y += 8.0;
            if (y > mc.thePlayer.posY) {
                y = mc.thePlayer.posY;
            }
            C03PacketPlayer.C04PacketPlayerPosition packet = new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, y - (mc.thePlayer.posY - mc.thePlayer.boundingBox.minY), y, mc.thePlayer.posZ, true);
            mc.thePlayer.sendQueue.addToSendQueue(packet);
        }
    }
	
	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onEnable() {
		super.onEnable();
	}
	
}
