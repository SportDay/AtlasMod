package me.xtrm.Atlas.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.render.EventRender3D;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.module.modules.render.xray.Renderer;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.Modes;
import me.xtrm.Atlas.utils.xray.XData;

public class XRay extends Module {

	private Renderer renderer;
	
	public static XData blacklist;
	
	public XRay() {
		super("XRay", Keyboard.KEY_X, Category.Render);
		
		addSetting(new Setting("Mode", this, "XRay", Modes.build("XRay", "CaveFinder")));
		
		blacklist = new XData();
		// 1,2,3,4,7,12,13,17,18,24,31,32,78,87,88,106,121
		blacklist.add(1, 0);
		blacklist.add(2, 0);
		blacklist.add(3, 0);
		blacklist.add(4, 0);
		blacklist.add(7, 0);
		blacklist.add(12, 0);
		blacklist.add(13, 0);
		blacklist.add(17, 0);
		blacklist.add(18, 0);
		blacklist.add(24, 0);
		blacklist.add(31, 0);
		blacklist.add(32, 0);
		blacklist.add(78, 0);
		blacklist.add(87, 0);
		blacklist.add(88, 0);
		blacklist.add(106, 0);
		blacklist.add(121, 0);
		
		// Modded shit
		blacklist.add(225, -1);
		blacklist.add(210, -1);
		blacklist.add(254, -1);
		blacklist.add(463, -1);
		blacklist.add(452, -1);
		
		renderer = new Renderer();			
	}
	
	@EventTarget
	public void onRender3D(EventRender3D event) {
		float fx = event.partialTicks;
		
		renderer.render(fx);
	}

}
