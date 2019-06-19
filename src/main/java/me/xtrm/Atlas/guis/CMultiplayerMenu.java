package me.xtrm.Atlas.guis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import cpw.mods.fml.client.FMLClientHandler;
import me.xtrm.Atlas.utils.Wrapper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ServerListEntryLanDetected;
import net.minecraft.client.gui.ServerListEntryLanScan;
import net.minecraft.client.gui.ServerListEntryNormal;
import net.minecraft.client.gui.ServerSelectionList;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.resources.I18n;

//GuiMultiplayer.class
@SuppressWarnings("all")
public class CMultiplayerMenu extends GuiScreen implements GuiYesNoCallback {

	private static final Logger logger = LogManager.getLogger();
    private final OldServerPinger field_146797_f = new OldServerPinger();
    private GuiScreen field_146798_g;
    private ServerSelectionList field_146803_h;
    private ServerList field_146804_i;
    private GuiButton field_146810_r;
    private GuiButton field_146809_s;
    private GuiButton field_146808_t;
    private boolean field_146807_u;
    private boolean field_146806_v;
    private boolean field_146805_w;
    private boolean field_146813_x;
    private String field_146812_y;
    private ServerData field_146811_z;
    private LanServerDetector.LanServerList field_146799_A;
    private LanServerDetector.ThreadLanServerFind field_146800_B;
    private boolean field_146801_C;

    public CMultiplayerMenu(GuiScreen p_i1040_1_)
    {
        this.field_146798_g = p_i1040_1_;
        FMLClientHandler.instance().setupServerList();
    }
	
	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();

        if (!this.field_146801_C)
        {
            this.field_146801_C = true;
            this.field_146804_i = new ServerList(this.mc);
            this.field_146804_i.loadServerList();
            this.field_146799_A = new LanServerDetector.LanServerList();

            try
            {
                this.field_146800_B = new LanServerDetector.ThreadLanServerFind(this.field_146799_A);
                this.field_146800_B.start();
            }
            catch (Exception exception)
            {
                logger.warn("Unable to start LAN server detection: " + exception.getMessage());
            }

            this.field_146803_h = new ServerSelectionList(new GuiMultiplayer(new CMainMenu()), Wrapper.mc, this.width, this.height, 32, this.height - 64, 36);
            this.field_146803_h.func_148195_a(this.field_146804_i);
        }
        else
        {
            this.field_146803_h.func_148122_a(this.width, this.height, 32, this.height - 64);
        }

        this.func_146794_g();
	}
	
	public void func_146794_g()
    {
        this.buttonList.add(this.field_146810_r = new GuiButton(7, this.width / 2 - 154, this.height - 28, 70, 20, I18n.format("selectServer.edit", new Object[0])));
        this.buttonList.add(this.field_146808_t = new GuiButton(2, this.width / 2 - 74, this.height - 28, 70, 20, I18n.format("selectServer.delete", new Object[0])));
        this.buttonList.add(this.field_146809_s = new GuiButton(1, this.width / 2 - 154, this.height - 52, 100, 20, I18n.format("selectServer.select", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 50, this.height - 52, 100, 20, I18n.format("selectServer.direct", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 4 + 50, this.height - 52, 100, 20, I18n.format("selectServer.add", new Object[0])));
        this.buttonList.add(new GuiButton(8, this.width / 2 + 4, this.height - 28, 70, 20, I18n.format("selectServer.refresh", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 76, this.height - 28, 75, 20, I18n.format("gui.cancel", new Object[0])));
        this.func_146790_a(this.field_146803_h.func_148193_k());
    }
	
	public void func_146790_a(int p_146790_1_)
    {
        this.field_146803_h.func_148192_c(p_146790_1_);
        GuiListExtended.IGuiListEntry iguilistentry = p_146790_1_ < 0 ? null : this.field_146803_h.getListEntry(p_146790_1_);
        this.field_146809_s.enabled = false;
        this.field_146810_r.enabled = false;
        this.field_146808_t.enabled = false;

        if (iguilistentry != null && !(iguilistentry instanceof ServerListEntryLanScan))
        {
            this.field_146809_s.enabled = true;

            if (iguilistentry instanceof ServerListEntryNormal)
            {
                this.field_146810_r.enabled = true;
                this.field_146808_t.enabled = true;
            }
        }
    }
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		this.field_146812_y = null;
        this.drawDefaultBackground();
        this.field_146803_h.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
        this.drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.title", new Object[0]), this.width / 2, 20, 16777215);
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

        if (this.field_146812_y != null)
        {
            this.func_146283_a(Lists.newArrayList(Splitter.on("\n").split(this.field_146812_y)), p_73863_1_, p_73863_2_);
        }
	}
	
	@Override
	protected void actionPerformed(GuiButton p_146284_1_) {
		if (p_146284_1_.enabled)
        {
            GuiListExtended.IGuiListEntry iguilistentry = this.field_146803_h.func_148193_k() < 0 ? null : this.field_146803_h.getListEntry(this.field_146803_h.func_148193_k());

            if (p_146284_1_.id == 2 && iguilistentry instanceof ServerListEntryNormal)
            {
                String s4 = ((ServerListEntryNormal)iguilistentry).func_148296_a().serverName;

                if (s4 != null)
                {
                    this.field_146807_u = true;
                    String s = I18n.format("selectServer.deleteQuestion", new Object[0]);
                    String s1 = "\'" + s4 + "\' " + I18n.format("selectServer.deleteWarning", new Object[0]);
                    String s2 = I18n.format("selectServer.deleteButton", new Object[0]);
                    String s3 = I18n.format("gui.cancel", new Object[0]);
                    GuiYesNo guiyesno = new GuiYesNo(this, s, s1, s2, s3, this.field_146803_h.func_148193_k());
                    this.mc.displayGuiScreen(guiyesno);
                }
            }
            else if (p_146284_1_.id == 1)
            {
                this.func_146796_h();
            }
            else if (p_146284_1_.id == 4)
            {
                this.field_146813_x = true;
                this.mc.displayGuiScreen(new GuiScreenServerList(this, this.field_146811_z = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "")));
            }
            else if (p_146284_1_.id == 3)
            {
                this.field_146806_v = true;
                this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.field_146811_z = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "")));
            }
            else if (p_146284_1_.id == 7 && iguilistentry instanceof ServerListEntryNormal)
            {
                this.field_146805_w = true;
                ServerData serverdata = ((ServerListEntryNormal)iguilistentry).func_148296_a();
                this.field_146811_z = new ServerData(serverdata.serverName, serverdata.serverIP);
                this.field_146811_z.func_152583_a(serverdata);
                this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.field_146811_z));
            }
            else if (p_146284_1_.id == 0)
            {
                this.mc.displayGuiScreen(this.field_146798_g);
            }
            else if (p_146284_1_.id == 8)
            {
                this.func_146792_q();
            }
        }
		super.actionPerformed(p_146284_1_);
	}
    private void func_146791_a(ServerData p_146791_1_)
    {
        FMLClientHandler.instance().connectToServer(this, p_146791_1_);
    }
	
	public void func_146796_h()
    {
        GuiListExtended.IGuiListEntry iguilistentry = this.field_146803_h.func_148193_k() < 0 ? null : this.field_146803_h.getListEntry(this.field_146803_h.func_148193_k());

        if (iguilistentry instanceof ServerListEntryNormal)
        {
            this.func_146791_a(((ServerListEntryNormal)iguilistentry).func_148296_a());
        }
        else if (iguilistentry instanceof ServerListEntryLanDetected)
        {
            LanServerDetector.LanServer lanserver = ((ServerListEntryLanDetected)iguilistentry).func_148289_a();
            this.func_146791_a(new ServerData(lanserver.getServerMotd(), lanserver.getServerIpPort(), true));
        }
    }
	
	private void func_146792_q()
	{
		this.mc.displayGuiScreen(new CMultiplayerMenu(this.field_146798_g));
	}
	
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
		this.field_146803_h.func_148179_a(p_73864_1_, p_73864_2_, p_73864_3_);
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}
	
	@Override
	protected void mouseMovedOrUp(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
		this.field_146803_h.func_148181_b(p_146286_1_, p_146286_2_, p_146286_3_);
		super.mouseMovedOrUp(p_146286_1_, p_146286_2_, p_146286_3_);
	}
	
}
