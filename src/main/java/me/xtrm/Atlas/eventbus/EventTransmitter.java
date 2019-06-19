package me.xtrm.Atlas.eventbus;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import me.xtrm.Atlas.eventbus.Event.State;
import me.xtrm.Atlas.eventbus.events.move.EventJump;
import me.xtrm.Atlas.eventbus.events.other.EventKeyPressed;
import me.xtrm.Atlas.eventbus.events.render.EventRender2D;
import me.xtrm.Atlas.eventbus.events.render.EventRender3D;
import me.xtrm.Atlas.eventbus.events.update.EventTick;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventTransmitter {

	@SubscribeEvent
	public void onRender2D(RenderGameOverlayEvent event) {
		if(event.type == RenderGameOverlayEvent.ElementType.TEXT) {
			EventRender2D e = new EventRender2D(event.partialTicks);
			e.call();
		}
	}
	
	@SubscribeEvent
	public void onUpdate(LivingEvent.LivingUpdateEvent event) {
		if(event.entityLiving == null)
			return;
		
		if(event.entityLiving instanceof EntityPlayerSP) {
			EntityPlayerSP p = (EntityPlayerSP) event.entityLiving;
			EventUpdate e = new EventUpdate(State.PRE, p.posX, p.posY, p.posZ, p.rotationYaw, p.rotationPitch, p.onGround);
			e.call();
			if(e.isCancelled())
				event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if (!Keyboard.getEventKeyState()) {
			return;
		}
		EventKeyPressed e = new EventKeyPressed(Keyboard.getEventKey());
		e.call();
	}
	
	@SubscribeEvent
	public void onJump(LivingEvent.LivingJumpEvent event) {
		EventJump e = new EventJump();
		e.call();
	}
	
	@SubscribeEvent
	public void onRender3D(RenderWorldLastEvent event) {
		EventRender3D e = new EventRender3D(event.partialTicks);
		e.call();
	}
	
	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		EventTick e = new EventTick((event.phase == TickEvent.Phase.START) ? State.PRE : State.POST);
		e.call();
	}
	
}
