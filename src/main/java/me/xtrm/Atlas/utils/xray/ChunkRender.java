package me.xtrm.Atlas.utils.xray;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.client.FMLClientHandler;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.modules.render.XRay;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ChunkRender {
	
	public static void clearGLids() {
		ChunkRender.GLids.clear();
	}
	
	private Minecraft mc = FMLClientHandler.instance().getClient();
	private static Map<Integer, Integer> GLids = new HashMap<Integer, Integer>();
	private World w;
	public int xc = 0;
	public int zc = 0;
	public int yc = 0;
	public boolean needsupdate = true;
	public boolean needsrenderupdate = false;
	
	public int GLid = 0;
	
	private List<BlockData> renderList = new ArrayList<BlockData>();
	
	public ChunkRender(World w, int xc, int yc, int zc) {
		this.w = w;
		this.xc = xc;
		this.zc = zc;
		this.yc = yc;
		this.GLid = this.getAvailableID();
	}
	
	private int getAvailableID() {
		int i = 1;
		for (; i < 500 ; i++) {
			if (!ChunkRender.GLids.containsKey(99999 + i)) {
				ChunkRender.GLids.put(99999 + i, 99999 + i);
				return 99999 + i;
			}
		}
		return 99999;
	}
	
	public void removeGLid() {
		ChunkRender.GLids.remove(this.GLid);
	}
	
	public void render(float fx, float fy, float fz, float partialTick) {
		if (needsrenderupdate) {
			needsrenderupdate = false;
			
			Tessellator tes = Tessellator.instance;
			int ambi = mc.gameSettings.ambientOcclusion;
			mc.gameSettings.ambientOcclusion = 0;
			glNewList(GLid, GL_COMPILE);
			
			glPushMatrix();
			{
				tes.startDrawingQuads();
				tes.setBrightness(200);
				mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
				for (BlockData r : renderList) {
					RenderBlocks rb = null;
					try {
						Field f = mc.renderGlobal.getClass().getDeclaredField("renderBlocksRg");
						f.setAccessible(true);
						rb = (RenderBlocks)f.get(mc.renderGlobal);
					} catch (Exception e) {
						try {
							Field f = mc.renderGlobal.getClass().getDeclaredField("field_147592_B"); 
							f.setAccessible(true);
							rb = (RenderBlocks)f.get(mc.renderGlobal);
						} catch (Exception ex) {
							e.printStackTrace();
							ex.printStackTrace();
						}
					}
					
					if(rb != null)
						r.RenderBlock(rb);
				}
				tes.draw();
			}
			glPopMatrix();
			
			glEndList();
			mc.gameSettings.ambientOcclusion = ambi;
		}
	}
	
	public void updateChunk() {
		
		if (needsupdate) {
			needsupdate = false;
			
			renderList.clear();
			String mode = Wrapper.getSetting("Mode", Atlas.instance.moduleManager.getModule("XRay")).string();
			int xb = xc * 16;
			int zb = zc * 16;
			int yb = yc * 16;
			
			for (int y = yb ; y < yb + 16 ; y++) {
				for (int x = xb ; x < xb + 16 ; x++) {
					for (int z = zb ; z < zb + 16 ; z++) {
						int id = Block.getIdFromBlock(w.getBlock(x, y, z));
						
						if (mode.equalsIgnoreCase("XRay") && id > 0 && !XRay.blacklist.contains(id, -1)) {
							renderList.add(new BlockData(Block.getBlockById(id), x, y, z, true));
						}
						if (mode.equalsIgnoreCase("CaveFinder") && id > 0 && (Block.getBlockById(id) == Blocks.stone || !XRay.blacklist.contains(id, -1))) {
							renderList.add(new BlockData(Block.getBlockById(id), x, y, z, false));
						}
					}
				}
			}
			needsrenderupdate = true;
		}
	}
}