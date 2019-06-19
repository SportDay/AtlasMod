package me.xtrm.Atlas.altlogin;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;

import me.xtrm.Atlas.utils.ScaledUtils;
import me.xtrm.Atlas.utils.SessionUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.Session;

public class GuiAltLogin extends GuiScreen {
	
    private GuiTextField usernameField;
    private GuiTextField passwordField;
    private GuiTextField usernamePasswordField;
    
    private GuiScreen prev;
    
    public GuiAltLogin(GuiScreen prev) {
    	this.prev = prev;
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        mc.fontRenderer.drawStringWithShadow("Current Account: §c" + mc.getSession().getUsername(), 2, 2, -1);
        
        ScaledResolution sr = ScaledUtils.gen();
        drawString(mc.fontRenderer, "Username:", width / 2 - 250 / 2, sr.getScaledHeight() / 2 - 92, -1);
        usernameField.drawTextBox();
        drawString(mc.fontRenderer, "Password:", width / 2 - 250 / 2, sr.getScaledHeight() / 2 - 92 + 40, -1);
        passwordField.drawTextBox();
        drawString(mc.fontRenderer, "E-Mail:Password:", width / 2 - 250 / 2, sr.getScaledHeight() / 2 - 92 + 40 + 40, -1);
        usernamePasswordField.drawTextBox();
//        usernamePasswordField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 3) {
            login();
        }

        if (button.id == 4) {
            String data;
            try {
                data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (Exception e) {
                return;
            }
            if (data != null) {
                usernamePasswordField.setText(data);
                login();
                usernamePasswordField.setText("");
            }
        }


        if (button.id == 5) {
            mc.displayGuiScreen(prev);
        }

        super.actionPerformed(button);
    }

	private void login() {    	
        if (usernamePasswordField.getText().length() != 0 && usernamePasswordField.getText().contains(":") && !usernamePasswordField.getText().endsWith(":")) {
            usernameField.setText(usernamePasswordField.getText().split(":")[0]);
            passwordField.setText(usernamePasswordField.getText().split(":")[1]);
            usernamePasswordField.setText("");
        }

        try {
            Session session = SessionUtils.createPremiumSession(usernameField.getText(), passwordField.getText());
            SessionUtils.setSession(session);
        } catch (Exception e) {
            mc.displayGuiScreen(new GuiErrorScreen("Login Error", e.getMessage()));
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        usernameField.textboxKeyTyped(typedChar, keyCode);
        passwordField.textboxKeyTyped(typedChar, keyCode);
        usernamePasswordField.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        usernameField.mouseClicked(mouseX, mouseY, mouseButton);
        passwordField.mouseClicked(mouseX, mouseY, mouseButton);
        usernamePasswordField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void initGui() {
    	ScaledResolution sr = ScaledUtils.gen();
        usernameField = new GuiTextField(mc.fontRenderer, width / 2 - 250 / 2, sr.getScaledHeight() / 2 - 80, 250, 20);
        usernameField.setMaxStringLength(100);
        passwordField = new GuiTextField(mc.fontRenderer, width / 2 - 250 / 2, sr.getScaledHeight() / 2 - 40, 250, 20);
        passwordField.setMaxStringLength(100);
        usernamePasswordField = new GuiTextField(mc.fontRenderer, width / 2 - 250 / 2, sr.getScaledHeight() / 2, 250, 20);
        usernamePasswordField.setMaxStringLength(190);
        buttonList.add(new GuiButton(3, width / 2 - 250 / 2, sr.getScaledHeight() / 2 + (25 * 1), 250, 20, "Login"));
        buttonList.add(new GuiButton(4, width / 2 - 250 / 2, sr.getScaledHeight() / 2 + (25 * 2), 250, 20, "Clipboard"));
        buttonList.add(new GuiButton(5, width / 2 - 250 / 2, sr.getScaledHeight() / 2 + (25 * 3), 250, 20, "Back"));
        super.initGui();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }
}
