package me.xtrm.Atlas.guis;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.GuiModList;
import me.xtrm.Atlas.altlogin.GuiAltLogin;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.RainbowUtils;
import me.xtrm.Atlas.utils.ScaledUtils;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

//@SuppressWarnings("unchecked")
public class GuiPalaClient extends GuiScreen {
	
	private GuiScreen previous;
	
	public GuiPalaClient(GuiScreen previous) {
		this.previous = previous;
	}
	
	@Override
	public void initGui() {
//		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, "Back"));
		super.initGui();
	}
	
	private float yPos = 8;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = ScaledUtils.gen();
		
		drawDefaultBackground();
		
		boolean hoveredQuit = (mouseX >= 0 && mouseX <= 70) && (mouseY >= 0 && mouseY <= 35);
		boolean hoveredMods = (mouseX >= sr.getScaledWidth() / 2 - 75 && mouseX <= sr.getScaledWidth() / 2 + 75) && (mouseY >= sr.getScaledHeight() / 2 - 80 && mouseY <= sr.getScaledHeight() / 2 - 55);
		boolean hoveredCred = (mouseX >= sr.getScaledWidth() / 2 - 75 && mouseX <= sr.getScaledWidth() / 2 + 75) && (mouseY >= sr.getScaledHeight() / 2 - 50 && mouseY <= sr.getScaledHeight() / 2 - 25);
		boolean hoveredAlt = (mouseX >= sr.getScaledWidth() / 2 - 75 && mouseX <= sr.getScaledWidth() / 2 + 75) && (mouseY >= sr.getScaledHeight() / 2 - 20 && mouseY <= sr.getScaledHeight() / 2 + 5);
		boolean hoveredTitle = (mouseX >= sr.getScaledWidth() / 2 - 75 && mouseX <= sr.getScaledWidth() / 2 + 75) && (mouseY >= 0 && mouseY <= 35);
		
		GL11.glPushMatrix();
		GL11.glScalef(2, 2, 2);
		drawCenteredString(Wrapper.fr, EnumChatFormatting.BLUE + Atlas.instance.name, sr.getScaledWidth() / 4, (int)yPos, -1);
		GL11.glPopMatrix();
		
		if(hoveredTitle) {
			if(yPos > 4)
				yPos--;
			if(yPos < 4)
				yPos = 4;
		}else {
			if(yPos < 6)
				yPos++;
			if(yPos > 6)
				yPos = 6;
		}
		
		double x = 30, y = 30;
		for(int i = (int)x*2; i > 0; i--) {
			GuiUtils.getInstance().drawRect((float)(sr.getScaledWidth() / 2 - x + i), (float)y, (float)(sr.getScaledWidth() / 2 - x + i + 1), (float)(y + 2), RainbowUtils.effect((long)(i * 500000L), 0.9F, 100).getRGB());
		}
		
		drawRect(0, 0, 70, 35, hoveredQuit ? 0xCC0F0F0F : 0xCC000000);
		GL11.glPushMatrix();
		GL11.glScalef(2, 2, 2);
		mc.fontRenderer.drawStringWithShadow("<---", 5, 5, -1);
		GL11.glPopMatrix();
		
		drawRect(sr.getScaledWidth() / 2 - 75, sr.getScaledHeight() / 2 - 80, sr.getScaledWidth() / 2 + 75, sr.getScaledHeight() / 2 - 55, hoveredMods ? 0xCC0F0F0F : 0xCC000000);
		GL11.glPushMatrix();
		GL11.glScalef(1.5f, 1.5f, 1.5f);
		mc.fontRenderer.drawStringWithShadow("Mods", (int)((sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth("Mods") / 2) / 1.5) - 2, (int)((sr.getScaledHeight() / 2 - 73) / 1.5), -1);
		GL11.glPopMatrix();
		
		drawRect(sr.getScaledWidth() / 2 - 75, sr.getScaledHeight() / 2 - 50, sr.getScaledWidth() / 2 + 75, sr.getScaledHeight() / 2 - 25, hoveredCred ? 0xCC0F0F0F : 0xCC000000);
		GL11.glPushMatrix();
		GL11.glScalef(1.5f, 1.5f, 1.5f);
		mc.fontRenderer.drawStringWithShadow("Credits", (int)((sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth("Credits") / 2) / 1.5) - 4, (int)((sr.getScaledHeight() / 2 - 43) / 1.5), -1);
		GL11.glPopMatrix();
		
		drawRect(sr.getScaledWidth() / 2 - 75, sr.getScaledHeight() / 2 - 20, sr.getScaledWidth() / 2 + 75, sr.getScaledHeight() / 2 + 5, hoveredAlt ? 0xCC0F0F0F : 0xCC000000);
		GL11.glPushMatrix();
		GL11.glScalef(1.5f, 1.5f, 1.5f);
		mc.fontRenderer.drawStringWithShadow("Alt Login", (int)((sr.getScaledWidth() / 2 - mc.fontRenderer.getStringWidth("Alt Login") / 2) / 1.5) - 4, (int)((sr.getScaledHeight() / 2 - 13) / 1.5), -1);
		GL11.glPopMatrix();
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int state) {
		ScaledResolution sr = ScaledUtils.gen();
		boolean hoveredQuit = (mouseX >= 0 && mouseX <= 70) && (mouseY >= 0 && mouseY <= 35);
		boolean hoveredMods = (mouseX >= sr.getScaledWidth() / 2 - 75 && mouseX <= sr.getScaledWidth() / 2 + 75) && (mouseY >= sr.getScaledHeight() / 2 - 80 && mouseY <= sr.getScaledHeight() / 2 - 55);
		boolean hoveredCred = (mouseX >= sr.getScaledWidth() / 2 - 75 && mouseX <= sr.getScaledWidth() / 2 + 75) && (mouseY >= sr.getScaledHeight() / 2 - 50 && mouseY <= sr.getScaledHeight() / 2 - 25);
		boolean hoveredAlt = (mouseX >= sr.getScaledWidth() / 2 - 75 && mouseX <= sr.getScaledWidth() / 2 + 75) && (mouseY >= sr.getScaledHeight() / 2 - 20 && mouseY <= sr.getScaledHeight() / 2 + 5);
		if(state == 0) {
			if(hoveredQuit) {
				mc.displayGuiScreen(previous);
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			}else if(hoveredMods) {
				mc.displayGuiScreen(new GuiModList(this));
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			}else if(hoveredCred) {
				mc.displayGuiScreen(new GuiCreditz(this));
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			}else if(hoveredAlt) {
				mc.displayGuiScreen(new GuiAltLogin(this));
				mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			}
		}
		super.mouseClicked(mouseX, mouseY, state);
	}
	
	@Override
	protected void actionPerformed(GuiButton p_146284_1_) {
		super.actionPerformed(p_146284_1_);
	}
	
}
