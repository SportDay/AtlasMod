package me.xtrm.Atlas.guis;

import org.lwjgl.opengl.GL11;

import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.utils.ScaledUtils;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiCreditz extends GuiScreen {
	
	private GuiScreen prev;
	
	public GuiCreditz(GuiScreen prev) {
		this.prev = prev;
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		ScaledResolution sr = ScaledUtils.gen();
		drawDefaultBackground();
		boolean hoveredQuit = (p_73863_1_ >= 0 && p_73863_1_ <= 70) && (p_73863_2_ >= 0 && p_73863_2_ <= 35);
		drawRect(0, 0, 70, 35, hoveredQuit ? 0xCC0F0F0F : 0xCC000000);
		GL11.glPushMatrix();
		GL11.glScalef(2, 2, 2);
		mc.fontRenderer.drawStringWithShadow("<---", 5, 5, -1);
		GL11.glPopMatrix();
		
		drawRect(sr.getScaledWidth() / 2 - 45, sr.getScaledHeight() / 2 - 55, sr.getScaledWidth() / 2 + 55, sr.getScaledHeight() / 2 - 10, 0xCC000000);
		String s = Atlas.instance.name + " " + EnumChatFormatting.WHITE + "v" + Atlas.instance.ver; 
		mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + s, sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(s) / 2, sr.getScaledHeight() / 2 - 50, -1);
		mc.fontRenderer.drawStringWithShadow(EnumChatFormatting.RED + "Ai" + EnumChatFormatting.AQUA + "on " + EnumChatFormatting.WHITE + "Chainloader", sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth("Aion Chainloadi") / 2, sr.getScaledHeight() / 2 - 40, -1);
		mc.fontRenderer.drawStringWithShadow("by xTrM_", sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth(" xTrM_") / 2, sr.getScaledHeight() / 2 - 25, -1);
		
		
		
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		boolean hoveredQuit = (p_73864_1_ >= 0 && p_73864_1_ <= 70) && (p_73864_2_ >= 0 && p_73864_2_ <= 35);
		if(hoveredQuit && p_73864_3_ == 0) {
			mc.displayGuiScreen(prev);
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
		}
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}

}
