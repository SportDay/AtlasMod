package me.xtrm.Atlas.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

public class RenderUtils {
	
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static void init3D() {
		GL11.glDisable( GL11.GL_TEXTURE_2D );
		GL11.glDisable( GL11.GL_DEPTH_TEST );
		GL11.glDisable( GL11.GL_CULL_FACE );
		GL11.glDepthMask(false);
		GL11.glEnable( GL11.GL_BLEND );
		GL11.glBlendFunc( GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA );
		GL11.glLineWidth( 1f );
	}
	
	public static void reset3D() {
		GL11.glDepthMask(true);
		GL11.glDisable( GL11.GL_BLEND );
		GL11.glEnable( GL11.GL_TEXTURE_2D );
		GL11.glEnable( GL11.GL_DEPTH_TEST );
		GL11.glEnable( GL11.GL_CULL_FACE );
	}
	
	public static void drawOutlinedBlock(float fx, double bx, double by, double bz, int color) {
		float dx = (float)mc.thePlayer.posX;
		float dy = (float)mc.thePlayer.posY;
		float dz = (float)mc.thePlayer.posZ;
		float mx = (float)mc.thePlayer.prevPosX;
		float my = (float)mc.thePlayer.prevPosY;
		float mz = (float)mc.thePlayer.prevPosZ;
		float px = mx + ( dx - mx ) * fx;
		float py = my + ( dy - my ) * fx;
		float pz = mz + ( dz - mz ) * fx;
		
		float f = 0.0f;
		float f1 = 1.0f;
		
		Tessellator tes = Tessellator.instance;
		
		tes.startDrawing( GL11.GL_LINES );
		tes.setColorRGBA_I(color, 255);
		tes.setBrightness( 200 );
		
		// Bottom
		tes.addVertex( bx-px + f, by-py + f1, bz-pz + f); tes.addVertex( bx-px + f1,  by-py + f1, bz-pz + f);
		tes.addVertex( bx-px + f1,  by-py + f1, bz-pz + f); tes.addVertex( bx-px + f1,  by-py + f1, bz-pz + f1); 
		tes.addVertex( bx-px + f1,  by-py + f1, bz-pz + f1); tes.addVertex( bx-px + f,  by-py + f1,  bz-pz + f1);
		tes.addVertex( bx-px + f,  by-py + f1,  bz-pz + f1); tes.addVertex( bx-px + f,  by-py + f1,  bz-pz + f);

		// Top
		tes.addVertex( bx-px + f1,  by-py + f,  bz-pz + f); tes.addVertex( bx-px + f1,  by-py + f,  bz-pz + f1);
		tes.addVertex( bx-px + f1,  by-py + f,  bz-pz + f1); tes.addVertex( bx-px + f,  by-py + f,  bz-pz + f1);
		tes.addVertex( bx-px + f,  by-py + f,  bz-pz + f1); tes.addVertex( bx-px + f,  by-py + f,  bz-pz + f);
		tes.addVertex( bx-px + f,  by-py + f,  bz-pz + f); tes.addVertex( bx-px + f1,  by-py + f,  bz-pz + f);
		
		// Corners
		tes.addVertex( bx-px + f1,  by-py + f,  bz-pz + f1); tes.addVertex( bx-px + f1,  by-py + f1,  bz-pz + f1); // Top Left
		tes.addVertex( bx-px + f1,  by-py + f,  bz-pz + f); tes.addVertex( bx-px + f1,  by-py + f1,  bz-pz + f); // Bottom Left
		tes.addVertex( bx-px + f,  by-py + f,  bz-pz + f1); tes.addVertex( bx-px + f,  by-py + f1,  bz-pz + f1); // Top Right
		tes.addVertex( bx-px + f,  by-py + f,  bz-pz + f); tes.addVertex( bx-px + f,  by-py + f1,  bz-pz + f); // Bottom Right
		
		tes.draw();
	}
	
	public static void drawCircle(int x, int y, float radius, int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;

        GL11.glColor4f(red, green, blue, alpha);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glPushMatrix();
        GL11.glLineWidth(1F);
        GL11.glBegin(GL11.GL_POLYGON);
        for (int i = 0; i <= 360; i++)
            GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

    public static void connectPoints(int xOne, int yOne, int xTwo, int yTwo) {
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(0.5F);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2i(xOne, yOne);
        GL11.glVertex2i(xTwo, yTwo);
        GL11.glEnd();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

}
