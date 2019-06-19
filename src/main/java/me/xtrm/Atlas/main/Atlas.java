package me.xtrm.Atlas.main;

import org.lwjgl.opengl.Display;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import me.xtrm.Atlas.command.CommandManager;
import me.xtrm.Atlas.eventbus.EventManager;
import me.xtrm.Atlas.guis.GuiConsole;
import me.xtrm.Atlas.guis.click.ClickGUI;
import me.xtrm.Atlas.module.ModuleManager;
import me.xtrm.Atlas.rpc.RPCManager;
import me.xtrm.Atlas.settings.SettingsManager;

@SuppressWarnings("all")
@Mod(modid = Referances.MOD_ID, name = Referances.MOD_NAME, version = Referances.MOD_VERSION)
public class Atlas {
	
	@SidedProxy(clientSide = Referances.CL_PROXY_PATH, serverSide = Referances.CO_PROXY_PATH)
	public static CommonProxyZ proxyz;

//	@Mod.Instance(Referances.MOD_ID)
	public static Atlas instance;
	
	public String name = "Atlas";
	public String ver = "3.30-PRE";
	public String author = "xTrM_";

	public SettingsManager settingsManager;
	public CommandManager commandManager;
	public ModuleManager moduleManager;
	public EventManager eventManager;
	public RPCManager rpcManager;
	
	public GuiConsole console;
	public ClickGUI clickgui;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Display.setTitle("K0SMOS Loading...");
		
		instance = this;
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Display.setTitle(name + " Loading...");
		
		try {
			eventManager = new EventManager();

			settingsManager = new SettingsManager();

			moduleManager = new ModuleManager();
			moduleManager.init();

			commandManager = new CommandManager();
			commandManager.init();
			
			console = new GuiConsole();

			proxyz.registerRenders();
			
			rpcManager = new RPCManager();
			rpcManager.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Display.setTitle(name + " v" + ver + " | by " + author);
	}
}
