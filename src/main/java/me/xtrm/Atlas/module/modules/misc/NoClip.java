package me.xtrm.Atlas.module.modules.misc;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.utils.MovementUtils;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public class NoClip extends Module {
	
	public NoClip() {
		super("NoClip", Category.Miscellaneous);
	}
	
//	private int delay = 0;
	
	@EventTarget
	public void onUpdate(EventUpdate e) {				
//		double nextYOff = 0;
//		if(mc.gameSettings.keyBindJump.getIsKeyPressed()) {
//			mc.thePlayer.motionY = 0;
//			nextYOff += 0.2;
//		}
//		if(mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
//			mc.thePlayer.motionY = 0;
//			nextYOff -= 0.2;
//		}
		
		double offset = 0.2;
		if(MovementUtils.isMoving()) {
			if(mc.thePlayer.isCollidedHorizontally) {
				mc.thePlayer.setPosition(mc.thePlayer.posX + -MathHelper.sin(MovementUtils.getDirection()) * offset, mc.thePlayer.posY, mc.thePlayer.posZ + MathHelper.cos(MovementUtils.getDirection()) * offset);
		    	mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + -MathHelper.sin(MovementUtils.getDirection()) * offset, mc.thePlayer.boundingBox.minY, mc.thePlayer.posY, mc.thePlayer.posZ + MathHelper.cos(MovementUtils.getDirection()) * offset, mc.thePlayer.onGround));
		    	MovementUtils.setSpeed(0.1);
			}
		}
//		mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + nextYOff, mc.thePlayer.posZ);
    	
//		float direction = mc.thePlayer.rotationYaw + (mc.thePlayer.moveForward < 0.0F ? 180 : 0) + (mc.thePlayer.moveStrafing > 0.0F ? -90.0F * (mc.thePlayer.moveForward > 0.0F ? 0.5F : mc.thePlayer.moveForward < 0.0F ? -0.5F : 1.0F) : 0.0F) - (mc.thePlayer.moveStrafing < 0.0F ? -90.0F * (mc.thePlayer.moveForward > 0.0F ? 0.5F : mc.thePlayer.moveForward < 0.0F ? -0.5F : 1.0F) : 0.0F);
//	    float xDir = (float)Math.cos((direction + 90.0F) * Math.PI / 180.0D) * 0.3F;
//	    float zDir = (float)Math.sin((direction + 90.0F) * Math.PI / 180.0D) * 0.3F;
//	    double[] yOff = { -0.02500000037252903D, -0.028571428997176036D, -0.033333333830038704D, -0.04000000059604645D, -0.05000000074505806D, -0.06666666766007741D, -0.10000000149011612D, -0.20000000298023224D, -0.04000000059604645D, -0.033333333830038704D, -0.028571428997176036D, -0.02500000037252903D };
//	    
//	    double y
//	    
//	    if (mc.thePlayer.isCollidedHorizontally) {
//	    	this.delay += 1;
//	    }
//	    
//	    if (this.delay >= 3){
//	    	for (int i = 0; i < yOff.length; i++){
//	    	  	mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + xDir * 4, mc.thePlayer.boundingBox.minY, mc.thePlayer.posY, mc.thePlayer.posZ + zDir * 4, mc.thePlayer.onGround));
//	      	}
//	    	mc.thePlayer.setPosition(mc.thePlayer.posX + xDir * 4, mc.thePlayer.posY, mc.thePlayer.posZ + zDir * 4);
//	    	this.delay = 0;
//	    }
	}

}
