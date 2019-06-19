package me.xtrm.Atlas.guis;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.ScaledUtils;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;

public class GuiConsole extends GuiScreen {
	
	private ArrayList<String> messages;
	private GuiTextField intext;
	
	private double lastwidthheightproduct;
	
	public GuiConsole() {
		messages = new ArrayList<String>();
	}
	
	public void init() {
		ScaledResolution sr = ScaledUtils.gen();
		intext = new GuiTextField(Wrapper.fr, sr.getScaledWidth() / 2 - 150, sr.getScaledHeight() / 2 + 20, 300, 20);
		intext.setFocused(true);
		intext.setCanLoseFocus(true);
		intext.setVisible(true);
		intext.setMaxStringLength(100);
		intext.setEnableBackgroundDrawing(true);
	}
	
	public void addMessage(String msg) {
		this.messages.add(msg);
		System.out.println(msg);
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {		
		ScaledResolution sr = ScaledUtils.gen();
		
		double old = lastwidthheightproduct;
		lastwidthheightproduct = sr.getScaledWidth() + sr.getScaledHeight();
		
		if(old != lastwidthheightproduct) {
			init();
		}
		
		GuiUtils.getInstance().drawBorderedRect(50, 0, sr.getScaledWidth() - 50, sr.getScaledHeight() / 2 + 30, 0xAA000000, 0xAA0A0A0A);
		intext.drawTextBox();
		
		if(!messages.isEmpty()) {
			int count = 0;
			int baseY = ((sr.getScaledHeight() / 2 + 30) - 11) - (messages.size() * (mc.fontRenderer.FONT_HEIGHT + 1));
			for(String msg : messages) {
				mc.fontRenderer.drawStringWithShadow(msg, 53, baseY + count, -1);
				count += mc.fontRenderer.FONT_HEIGHT + 1;
			}
		}
		
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	@Override
	protected void keyTyped(char p_73869_1_, int p_73869_2_) {
		intext.textboxKeyTyped(p_73869_1_, p_73869_2_);
		
		if(p_73869_2_ == Keyboard.KEY_RETURN) {
			String text = intext.getText();
			Atlas.instance.commandManager.runCommands(text);
			
			intext.setText("");
		}
		if(p_73869_2_ == Keyboard.KEY_F5) {
			messages.clear();
		}
		super.keyTyped(p_73869_1_, p_73869_2_);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
