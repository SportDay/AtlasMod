package me.xtrm.Atlas.module.modules.render.xray;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import me.xtrm.Atlas.module.modules.render.XRay;
import me.xtrm.Atlas.utils.xray.ChunkRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;

public class Renderer {
	
	public static List<ChunkRender> chunkList = new ArrayList<ChunkRender>();
	private Minecraft mc = FMLClientHandler.instance().getClient();
	
	public void render(float partialTick) {		
		if (mc.theWorld == null || mc.thePlayer == null)
			return;
		
		float px = (float)mc.thePlayer.posX; 
		float py = (float)mc.thePlayer.posY; 
		float pz = (float)mc.thePlayer.posZ;
     	float mx = (float)mc.thePlayer.prevPosX;
		float my = (float)mc.thePlayer.prevPosY;
		float mz = (float)mc.thePlayer.prevPosZ;
     	float x = mx + ( px - mx ) * partialTick;
		float y = my + ( py - my ) * partialTick;
		float z = mz + ( pz - mz ) * partialTick;
		
		
		glPushMatrix();
		{
			renderXBlocks(x, y, z, partialTick);
		}		
		glPopMatrix();
    }
	
	@SuppressWarnings("unchecked")
	private void renderXBlocks(float fx, float fy, float fz, float partialTick) {
		glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
		
		glEnable(GL_TEXTURE_2D);
        glDisable(GL_FOG);
        glEnable(GL_CULL_FACE);
        glDisable(GL11.GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		RenderHelper.disableStandardItemLighting();		
		
		for (ChunkRender r : chunkList) {
			r.render(fx, fy, fz, partialTick);
		}
		glEnable(GL_TEXTURE_2D);
		
		glPushMatrix();
		glTranslatef(-fx, -fy, -fz);
		for (ChunkRender r : chunkList) {
			glCallList(r.GLid);
		}
		glPopMatrix();
		
		RenderHelper.enableStandardItemLighting();

		for (TileEntity tile : (List<TileEntity>)mc.renderGlobal.tileEntities) {
			if (tile != null && tile.blockType != null && !XRay.blacklist.contains(Block.getIdFromBlock(tile.blockType), -1))
				TileEntityRendererDispatcher.instance.renderTileEntity(tile, partialTick);
		}
		
		if (mc.renderViewEntity instanceof EntityPlayer && !this.mc.gameSettings.hideGUI && this.mc.objectMouseOver != null && !mc.renderViewEntity.isInsideOfMaterial(Material.water))
        {
            EntityPlayer entityplayer = (EntityPlayer)mc.renderViewEntity;
            glEnable(GL_BLEND);
            if (!ForgeHooksClient.onDrawBlockHighlight(mc.renderGlobal, entityplayer, mc.objectMouseOver, 0, entityplayer.inventory.getCurrentItem(), partialTick))
            {
            	mc.renderGlobal.drawSelectionBox(entityplayer, this.mc.objectMouseOver, 0, partialTick);
            }
        }
		
		mc.renderGlobal.drawBlockDamageTexture(Tessellator.instance, mc.renderViewEntity, partialTick);
		
		mc.effectRenderer.renderParticles(mc.renderViewEntity, partialTick);
		
		List<Entity> entities = mc.theWorld.loadedEntityList;
		for (Entity ent : entities) {
			
			double d0 = mc.renderViewEntity.getPosition(partialTick).xCoord;
			double d1 = mc.renderViewEntity.getPosition(partialTick).yCoord;
			double d2 = mc.renderViewEntity.getPosition(partialTick).zCoord;
			
			if (ent.isInRangeToRender3d(d0,d1,d2) && (ent != this.mc.renderViewEntity || this.mc.gameSettings.thirdPersonView != 0 || this.mc.renderViewEntity.isPlayerSleeping()))
				RenderManager.instance.renderEntitySimple(ent, partialTick);
		}
		
		RenderHelper.disableStandardItemLighting();
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_CULL_FACE);
		glDisable(GL_BLEND);
	}
}