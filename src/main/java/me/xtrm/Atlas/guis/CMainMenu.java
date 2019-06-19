package me.xtrm.Atlas.guis;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import me.xtrm.Atlas.altlogin.GuiAltLogin;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.particle.ParticleGen;
import me.xtrm.Atlas.utils.GuiUtils;
import me.xtrm.Atlas.utils.RainbowUtils;
import me.xtrm.Atlas.utils.ScaledUtils;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings("all")
public class CMainMenu extends GuiScreen {

	private ParticleGen particleGen;
	
	private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] {new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png")};
	private DynamicTexture viewportTexture;
	private ResourceLocation field_110351_G;
	private int panoramaTimer;
	
	public CMainMenu() {
		particleGen = new ParticleGen(150);
	}
	
	private String message = "Atlas PRE-release temporaire";
	
	@Override
	public void initGui() {
		this.viewportTexture = new DynamicTexture(256, 256);
        this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
//		int i = this.height / 4 + 48;
//		int j = 24;
//		
//		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, i, I18n.format("menu.singleplayer", new Object[0])));
//	    this.buttonList.add(new GuiButton(2, this.width / 2 - 100, i + j * 1, I18n.format("menu.multiplayer", new Object[0])));
//		
//		this.buttonList.add(new GuiButton(48914, this.width / 2 - 100, i + j * 2, EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "PalaClient"));
//		
//		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, i + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
//      this.buttonList.add(new GuiButton(4, this.width / 2 + 2, i + 72 + 12, 98, 20, I18n.format("menu.quit", new Object[0])));
//      this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, i + 72 + 12));
        
//        this.buttonList.add(new GuiButton(6942, 5, this.height - 35, 125, 20, EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Rejoindre Paladium"));
//        this.buttonList.add(new GuiButton(69564, 5, this.height - 60, 125, 20, EnumChatFormatting.GRAY + "./console"));
        super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.id == 69564)
		{			
			mc.displayGuiScreen(Atlas.instance.console);
			
//			AionLoadingScreen.hasFinished = false;
//			mc.displayGuiScreen(new AionLoadingScreen(new CMainMenu()));
		}
		
		if (button.id == 48914) 
		{
			mc.displayGuiScreen(new GuiPalaClient(this));
		}
		
		if (button.id == 6942)
		{
//			this.mc.displayGuiScreen(new GuiConnecting(this, mc, "proxy.paladium-pvp.fr", 25565));
//			FMLClientHandler.instance().connectToServer(this, new ServerData("motd", "proxy.paladium-pvp.fr:25565"));
			this.mc.displayGuiScreen(new GuiConnecting(this, mc, new ServerData("motd", "play.paladium.dev:25565")));	
		}
		
		if (button.id == 0)
        {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }

        if (button.id == 5)
        {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }

        if (button.id == 1)
        {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }

        if (button.id == 2)
        {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        
        if (button.id == 4)
        {
            this.mc.shutdown();
        }
		super.actionPerformed(button);
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
//		prototypeOne();		
		prototypeTwo(p_73863_1_, p_73863_2_, p_73863_3_);
		
		Wrapper.fr.drawStringWithShadow(message, 2, 50, -1);
		
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
	}
	
	private void prototypeOne() {
		this.drawDefaultBackground();
		
		particleGen.drawParticles();
		
		GL11.glPushMatrix();
		{
	    	ScaledResolution sr = ScaledUtils.gen();
	    	
//	    	this.mc.getTextureManager().bindTexture(new ResourceLocation("palaclient", "textures/background.png"));
//	    	this.drawTexturedModalRect(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight());
	    	
	    	Gui.drawRect(25, 0, 238, sr.getScaledHeight(), 0xDC000000);
	    	
	    	Gui.drawRect(24, 0, 25, sr.getScaledHeight(), 0xFFff422d);
	    	Gui.drawRect(238, 0, 239, sr.getScaledHeight(), 0xFFff422d);
	    	    	
	    	GL11.glScalef(4, 4, 4);
	    	this.mc.fontRenderer.drawStringWithShadow(Atlas.instance.name, (int)(150 * (0.125 / 2)), (int)(sr.getScaledHeight() / 2), 0xff422d);
		}
		GL11.glPopMatrix();
	}
	
	private void prototypeTwo(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		this.drawDefaultBackground();
		this.panoramaTimer++;
		
		ScaledResolution sr = ScaledUtils.gen();
		
		GL11.glDisable(GL11.GL_ALPHA_TEST);
        this.renderSkybox(p_73863_1_, p_73863_2_, p_73863_3_);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        
		particleGen.drawParticles();
		
		Gui.drawRect(0, 0, sr.getScaledWidth(), 30, 0xCE2F2F2F);
		Gui.drawRect(0, 0, 30, 30, 0xCE2F2F2F);
		if(isHovering(p_73863_1_, p_73863_2_, 0, 0, 30, 30)) {
			Gui.drawRect(0, 0, 30, 30, 0xEE2F2F2F);
		}
		
//		this.mc.getTextureManager().bindTexture(new ResourceLocation("palaclient:textures/logo.png"));
//	    this.drawTexturedModalRect(0, 0, 32, 32, 128, 128);
	    int y = 100;
	    int height = 200;
	    int buttonheight = 40;
	    
	    boolean singlehovered = isHovering(p_73863_1_, p_73863_2_, sr.getScaledWidth() / 2 - 90, y + 75 + 20, sr.getScaledWidth() / 2 + 90, y + 75 + 20 + buttonheight);
	    boolean multihovered = isHovering(p_73863_1_, p_73863_2_, sr.getScaledWidth() / 2 - 90, y + 75 + 20 + buttonheight + 5, sr.getScaledWidth() / 2 + 90, y + 75 + 20 + buttonheight + buttonheight + 5);
	    boolean optionshovered = isHovering(p_73863_1_, p_73863_2_, 30, 0, 30 + 98, 30);
	    boolean langhovered = isHovering(p_73863_1_, p_73863_2_, 30 + 98, 0, 30 + 98 + 99, 30);
	    boolean acchovered = isHovering(p_73863_1_, p_73863_2_, sr.getScaledWidth() - 200, 0, sr.getScaledWidth() - 75, 30);
	    boolean quithovered = isHovering(p_73863_1_, p_73863_2_, sr.getScaledWidth() - 75, 0, sr.getScaledWidth(), 30);
	    
	    Gui.drawRect(sr.getScaledWidth() - 200, 0, sr.getScaledWidth() - 75, 30, 0xBB0A0A0A);
	    if(acchovered) {
	    	Gui.drawRect(sr.getScaledWidth() - 200, 0, sr.getScaledWidth() - 75, 30, 0x550A0A0A);
	    }
	    
	    if(optionshovered)
	    	Gui.drawRect(30, 0, 30 + 98, 30, 0x55000000);
	    
	    if(langhovered)
	    	Gui.drawRect(30 + 98, 0, 30 + 98 + 99, 30, 0x55000000);
	    
	    GuiUtils.getInstance().drawRoundedRect(sr.getScaledWidth() / 2 - 115, y - 5, sr.getScaledWidth() / 2 + 115, height + y + 5, 0xAAE0E0E0, 0xAAE0E0E0);
	    GuiUtils.getInstance().drawRoundedRect(sr.getScaledWidth() / 2 - 110, y, sr.getScaledWidth() / 2 + 110, height + y, 0xBBE0E0E0, 0xBBE0E0E0);	    	
	    
	    GuiUtils.getInstance().drawRoundedRect(sr.getScaledWidth() / 2 - 90, y + 75 + 20, sr.getScaledWidth() / 2 + 90, y + 75 + 20 + buttonheight, singlehovered ? 0xFF272727 : 0xFF2d2d2d, singlehovered ? 0xFF272727 : 0xFF2d2d2d);
	    GuiUtils.getInstance().drawRoundedRect(sr.getScaledWidth() / 2 - 90, y + 75 + 20 + buttonheight + 5, sr.getScaledWidth() / 2 + 90, y + 75 + 20 + buttonheight + buttonheight + 5, multihovered ? 0xFF272727 : 0xFF2d2d2d, multihovered ? 0xFF272727 : 0xFF2d2d2d); //enabled button
//	    Gui.drawRect(sr.getScaledWidth() / 2 - 90, y + 75 + 20 + buttonheight + 5, sr.getScaledWidth() / 2 + 90, y + 75 + 20 + buttonheight + buttonheight + 5, multihovered ? 0xFF0b0b0b : 0xFF0b0b0b); //disabled button
	    
//	    double x = 65, yy = 160;
//		for(int i = (int)x*2; i > 0; i--) {
//			GuiUtils.getInstance().drawRect((float)(sr.getScaledWidth() / 2 - x + i), (float)yy, (float)(sr.getScaledWidth() / 2 - x + i + 1), (float)(yy + 4), RainbowUtils.effect((long)(i * 500000L), 0.9F, 100).getRGB());
//		}
	    
	    GuiUtils.getInstance().drawRoundedRect((int)(sr.getScaledWidth() / 2) - (4 * Wrapper.fr.getStringWidth(Atlas.instance.name) / 2) - 15, 157, (int)(sr.getScaledWidth() / 2) + (4 * Wrapper.fr.getStringWidth(Atlas.instance.name) / 2) + 15, 161, 0xBB0A0A0A, 0xBB0A0A0A);
	    
	    GL11.glPushMatrix();
	    GL11.glScalef(4, 4, 4);
	    this.drawCenteredString(Wrapper.fr, EnumChatFormatting.BLUE + Atlas.instance.name, (int)(sr.getScaledWidth() / 8), 30, 0xff422d);
	    GL11.glPopMatrix();	
	    
	    GL11.glPushMatrix();
	    GL11.glScalef(2, 2, 2);
	    Wrapper.fr.drawStringWithShadow(EnumChatFormatting.GRAY + "Version " + Atlas.instance.ver, (int)(sr.getScaledWidth() / 4) - Wrapper.fr.getStringWidth("Version " + Atlas.instance.ver) / 2, 42 * 2, -1);
	    GL11.glPopMatrix();
	    
	    GL11.glPushMatrix();	
	    GL11.glScalef(3, 3, 3);	    	
	    this.drawCenteredString(Wrapper.fr, "A", 5, 1, RainbowUtils.effect(1000000, 0.65F, 10000000).getRGB());
	    GL11.glPopMatrix();
	    
	    GL11.glPushMatrix();
	    GL11.glScalef(1.5f, 1.5f, 1.5f);
	    this.drawCenteredString(Wrapper.fr, I18n.format("menu.singleplayer", new Object[0]), (sr.getScaledWidth() / 3), y + 40, -1);
//	    this.drawCenteredString(Wrapper.fr, EnumChatFormatting.STRIKETHROUGH + " " + I18n.format("menu.multiplayer", new Object[0]), (sr.getScaledWidth() / 3), y + 40 + 30, -1);
	    this.drawCenteredString(Wrapper.fr, EnumChatFormatting.RED + "Rejoindre Paladium", (sr.getScaledWidth() / 3), y + 40 + 30, -1);
		GL11.glPopMatrix();
		
		if(quithovered) {
			this.drawRect(sr.getScaledWidth() - 75, 0, sr.getScaledWidth(), 30, 0x55000000);
		}
		
		int diff = (int)(75 / 2);
		Wrapper.fr.drawStringWithShadow("Quit", (sr.getScaledWidth() - diff) - (Wrapper.fr.getStringWidth("Quit") / 2), 10, -1);
		Wrapper.fr.drawStringWithShadow("" + Wrapper.mc.getSession().getUsername(), (sr.getScaledWidth() - 100 - diff) - (Wrapper.fr.getStringWidth(Wrapper.mc.getSession().getUsername()) / 2), 10, -1);
		Wrapper.fr.drawStringWithShadow("Options", 30 + (98 / 2 - Wrapper.fr.getStringWidth("Options") / 2), 10, -1);
		Wrapper.fr.drawStringWithShadow("Language", 30 + 98 + (99 / 2 - Wrapper.fr.getStringWidth("Language") / 2), 10, -1);
		
//		Color c = new Color(RainbowUtils.effect(1, 1, 1).getRed(), RainbowUtils.effect(1, 1, 1).getGreen(), RainbowUtils.effect(1, 1, 1).getBlue(), 100);
//		GuiUtils.getInstance().drawGradientRect(0, sr.getScaledHeight() / 2, sr.getScaledWidth(), sr.getScaledHeight() * 2, 0x00000000, c.getRGB());
		
		Wrapper.fr.drawStringWithShadow("Copyright Mojang AB. Do not distribute!", sr.getScaledWidth() - Wrapper.fr.getStringWidth("Copyright Mojang AB. Do not distribute!"), sr.getScaledHeight() - 10, -1);
		Wrapper.fr.drawStringWithShadow(Atlas.instance.name + " v" + Atlas.instance.ver + " by xTrM_", 2, sr.getScaledHeight() - 10, -1);
	}
	
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		ScaledResolution sr = ScaledUtils.gen();
		int y = 100;
	    int height = 200;
	    int buttonheight = 40;
	    
	    boolean singlehovered = isHovering(p_73864_1_, p_73864_2_, sr.getScaledWidth() / 2 - 90, y + 75 + 20, sr.getScaledWidth() / 2 + 90, y + 75 + 20 + buttonheight);
	    boolean multihovered = isHovering(p_73864_1_, p_73864_2_, sr.getScaledWidth() / 2 - 90, y + 75 + 20 + buttonheight + 5, sr.getScaledWidth() / 2 + 90, y + 75 + 20 + buttonheight + buttonheight + 5);
	    boolean optionshovered = isHovering(p_73864_1_, p_73864_2_, 30, 0, 30 + 98, 30);
	    boolean langhovered = isHovering(p_73864_1_, p_73864_2_, 30 + 98, 0, 30 + 98 + 99, 30);
	    boolean acchovered = isHovering(p_73864_1_, p_73864_2_, sr.getScaledWidth() - 200, 0, sr.getScaledWidth() - 75, 30);
	    boolean quithovered = isHovering(p_73864_1_, p_73864_2_, sr.getScaledWidth() - 75, 0, sr.getScaledWidth(), 30);
		
		if(isHovering(p_73864_1_, p_73864_2_, 0, 0, 30, 30) && p_73864_3_ == 0) {
			mc.displayGuiScreen(new GuiPalaClient(this));
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
		}
		if(singlehovered && p_73864_3_ == 0) {
			this.mc.displayGuiScreen(new GuiSelectWorld(this));
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
		}
		if(multihovered && p_73864_3_ == 0) {
			this.mc.displayGuiScreen(new GuiConnecting(this, mc, new ServerData("motd", "proxy.paladium-pvp.fr:25565")));	
		}
		if(quithovered && p_73864_3_ == 0) {
			mc.shutdown();
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
		}
		if(acchovered && p_73864_3_ == 0) {
			mc.displayGuiScreen(new GuiAltLogin(this));
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
		}
		if(optionshovered && p_73864_3_ == 0) {
			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
		}
		if(langhovered && p_73864_3_ == 0) {
			this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
			mc.getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
		}
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}
	
	private boolean isHovering(int mouseX, int mouseY, int x, int y, int x2, int y2) {
		return mouseX > x && mouseX < x2 && mouseY > y && mouseY < y2;
	}
	
	private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        byte b0 = 8;

        for (int k = 0; k < b0 * b0; ++k)
        {
            GL11.glPushMatrix();
            float f1 = ((float)(k % b0) / (float)b0 - 0.5F) / 64.0F;
            float f2 = ((float)(k / b0) / (float)b0 - 0.5F) / 64.0F;
            float f3 = 0.0F;
            GL11.glTranslatef(f1, f2, f3);
            GL11.glRotatef(MathHelper.sin(((float)this.panoramaTimer + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-((float)this.panoramaTimer + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int l = 0; l < 6; ++l)
            {
                GL11.glPushMatrix();

                if (l == 1)
                {
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 2)
                {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 3)
                {
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 4)
                {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (l == 5)
                {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                this.mc.getTextureManager().bindTexture(titlePanoramaPaths[l]);
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_I(16777215, 255 / (k + 1));
                float f4 = 0.0F;
                tessellator.addVertexWithUV(-1.0D, -1.0D, 1.0D, (double)(0.0F + f4), (double)(0.0F + f4));
                tessellator.addVertexWithUV(1.0D, -1.0D, 1.0D, (double)(1.0F - f4), (double)(0.0F + f4));
                tessellator.addVertexWithUV(1.0D, 1.0D, 1.0D, (double)(1.0F - f4), (double)(1.0F - f4));
                tessellator.addVertexWithUV(-1.0D, 1.0D, 1.0D, (double)(0.0F + f4), (double)(1.0F - f4));
                tessellator.draw();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        tessellator.setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    /**
     * Rotate and blurs the skybox view in the main menu
     */
    private void rotateAndBlurSkybox(float p_73968_1_)
    {
        this.mc.getTextureManager().bindTexture(this.field_110351_G);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        byte b0 = 3;

        for (int i = 0; i < b0; ++i)
        {
            tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float)(i + 1));
            int j = this.width;
            int k = this.height;
            float f1 = (float)(i - b0 / 2) / 256.0F;
            tessellator.addVertexWithUV((double)j, (double)k, (double)this.zLevel, (double)(0.0F + f1), 1.0D);
            tessellator.addVertexWithUV((double)j, 0.0D, (double)this.zLevel, (double)(1.0F + f1), 1.0D);
            tessellator.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(1.0F + f1), 0.0D);
            tessellator.addVertexWithUV(0.0D, (double)k, (double)this.zLevel, (double)(0.0F + f1), 0.0D);
        }

        tessellator.draw();
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColorMask(true, true, true, true);
    }

    /**
     * Renders the skybox in the main menu
     */
    private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_)
    {
        this.mc.getFramebuffer().unbindFramebuffer();
        GL11.glViewport(0, 0, 256, 256);
        this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.mc.getFramebuffer().bindFramebuffer(true);
        GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        float f1 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
        float f2 = (float)this.height * f1 / 256.0F;
        float f3 = (float)this.width * f1 / 256.0F;
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int k = this.width;
        int l = this.height;
        tessellator.addVertexWithUV(0.0D, (double)l, (double)this.zLevel, (double)(0.5F - f2), (double)(0.5F + f3));
        tessellator.addVertexWithUV((double)k, (double)l, (double)this.zLevel, (double)(0.5F - f2), (double)(0.5F - f3));
        tessellator.addVertexWithUV((double)k, 0.0D, (double)this.zLevel, (double)(0.5F + f2), (double)(0.5F - f3));
        tessellator.addVertexWithUV(0.0D, 0.0D, (double)this.zLevel, (double)(0.5F + f2), (double)(0.5F + f3));
        tessellator.draw();
    }
	
//	public void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
//    {
//        float f = 1.0F / textureWidth;
//        float f1 = 1.0F / textureHeight;
//        Tessellator tessellator = Tessellator.instance;
//        tessellator.startDrawingQuads();
//        tessellator.addVertexWithUV((double)x, (double)(y + height), 0.0D).tex((double)(u * f), (double)((v + (float)height) * f1));
//        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0.0D).tex((double)((u + (float)width) * f), (double)((v + (float)height) * f1));
//        tessellator.addVertexWithUV((double)(x + width), (double)y, 0.0D).tex((double)((u + (float)width) * f), (double)(v * f1));
//        tessellator.addVertexWithUV((double)x, (double)y, 0.0D).tex((double)(u * f), (double)(v * f1));
//        tessellator.draw();
//    }
	
	/*
	 public void drawTexturedModalRect(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(p_73729_1_ + 0), (double)(p_73729_2_ + p_73729_6_), (double)this.zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.addVertexWithUV((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + p_73729_6_), (double)this.zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.addVertexWithUV((double)(p_73729_1_ + p_73729_5_), (double)(p_73729_2_ + 0), (double)this.zLevel, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.addVertexWithUV((double)(p_73729_1_ + 0), (double)(p_73729_2_ + 0), (double)this.zLevel, (double)((float)(p_73729_3_ + 0) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.draw();
    }
	 */

}
