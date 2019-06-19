package me.xtrm.Atlas.module.modules.render.xray;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.utils.xray.ChunkRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class XRayTickHandler {

	private Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if(event.phase == Phase.END) {
			if(mc.thePlayer == null || mc.theWorld == null){
				return;
			}
			
			boolean isGamePaused = mc.isSingleplayer() && mc.currentScreen != null && mc.currentScreen.doesGuiPauseGame();
			
			if(!isGamePaused)
				updateBlocks(mc.theWorld, mc.thePlayer);
		}
	}
	private Chunk chunk = null;

	private boolean canAddChunk(int x, int y, int z) {
		for (ChunkRender r : Renderer.chunkList) {
			if (r.xc == x && r.zc == z && r.yc == y)
				return false;
		}
		
		return true;
	}
	
	public void updateBlocks(World w, EntityPlayer plr) {
		Minecraft mc = Minecraft.getMinecraft();
		
		if (Atlas.instance.moduleManager.getModule("XRay").isEnabled()) {
//			mc.skipRenderWorld = true;
		} else {
//			mc.skipRenderWorld = false;
			Renderer.chunkList.clear();
			chunk = null;
			ChunkRender.clearGLids();
			return;
		}
		
		try {
			WorldRenderer[] renderers = (WorldRenderer[])ObfuscationReflectionHelper.getPrivateValue(RenderGlobal.class, mc.renderGlobal, 9);
			
			for (WorldRenderer r : renderers) {
				for (ChunkRender cr : Renderer.chunkList) {
					if (r.posX / 16 == cr.xc && r.posY / 16 == cr.yc && r.posZ / 16 == cr.zc && r.isInFrustum && r.needsUpdate && r.isVisible) {
						cr.needsupdate = true;
					}
				}
			}
			
		} catch (Throwable e){}
		
		Chunk c = w.getChunkFromBlockCoords(MathHelper.floor_double(plr.posX), MathHelper.floor_double(plr.posZ));
		if (c != this.chunk) {
			this.chunk = c;

			for (int i = 0 ; i < Renderer.chunkList.size() ; i ++) {
				ChunkRender r = Renderer.chunkList.get(i);
				if (Math.abs(r.xc - this.chunk.xPosition) > 3 || Math.abs(r.zc - this.chunk.zPosition) > 3) {
					r.removeGLid();
					Renderer.chunkList.remove(i);
					i--;
				}
			}
			
			int height = w.provider.getHeight() / 16;
			for (int y = 0 ; y < height ; y++) {
				for (int x = 0 ; x < 3 ; x++) {
					for (int z = 0 ; z < 3 ; z++) {
						if (canAddChunk(c.xPosition+x, y, c.zPosition+z))
							Renderer.chunkList.add(new ChunkRender(w, c.xPosition+x, y, c.zPosition+z));
						
						if (canAddChunk(c.xPosition-x, y, c.zPosition-z))
							Renderer.chunkList.add(new ChunkRender(w, c.xPosition-x, y, c.zPosition-z));
						
						if (canAddChunk(c.xPosition-x, y, c.zPosition+z))
							Renderer.chunkList.add(new ChunkRender(w, c.xPosition-x, y, c.zPosition+z));
						
						if (canAddChunk(c.xPosition+x, y, c.zPosition-z))
							Renderer.chunkList.add(new ChunkRender(w, c.xPosition+x, y, c.zPosition-z));
					}
				}
			}
		}
		
		for (ChunkRender r : Renderer.chunkList) {
			r.updateChunk();
		}
	}
	
}
