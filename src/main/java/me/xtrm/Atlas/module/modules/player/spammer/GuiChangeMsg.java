package me.xtrm.Atlas.module.modules.player.spammer;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.module.modules.player.Spammer;
import me.xtrm.Atlas.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;

public class GuiChangeMsg extends GuiScreen {
	
	private GuiTextField hack;
	
	@Override
	public void initGui() {
		this.hack = new GuiTextField(Minecraft.getMinecraft().fontRenderer, this.width / 2 - 110, this.height / 2 - 10, 220, 20);
		this.hack.setMaxStringLength(98 - 12);
		this.hack.setText(Spammer.message);
		super.initGui();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(mc.fontRenderer, "Tapez le nouveau message du spammer", this.width / 2, this.height / 2 - 30, -1);
		this.hack.drawTextBox();
		this.hack.setFocused(true);
		this.hack.setTextColor(0xFFFFFFFF);
	}
	
	@Override
    public void updateScreen() {
        this.hack.updateCursorCounter();
    }
	
	@Override
	public void keyTyped(char typedChar, int key) {
        if(key == Keyboard.KEY_RETURN) {
	        String msg = this.hack.getText();
	        Spammer.message = msg;
	        this.mc.displayGuiScreen(null);
	        ChatUtils.sendMessage(EnumChatFormatting.GRAY + "Message set to " + EnumChatFormatting.BLUE + msg);
        } else {
        	this.hack.textboxKeyTyped(typedChar, key);
        }
        if (key == 1) {
            this.mc.displayGuiScreen(null);
        }
	}
	
}
