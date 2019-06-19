package me.xtrm.Atlas.module;

import java.util.ArrayList;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.other.EventKeyPressed;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module.Category;
import me.xtrm.Atlas.module.modules.combat.*;
import me.xtrm.Atlas.module.modules.gui.*;
import me.xtrm.Atlas.module.modules.misc.*;
import me.xtrm.Atlas.module.modules.movement.*;
import me.xtrm.Atlas.module.modules.player.*;
import me.xtrm.Atlas.module.modules.render.*;
import me.xtrm.Atlas.module.modules.world.*;

public class ModuleManager {
	
	public ArrayList<Module> mods;
	
	public ModuleManager() {
		mods = new ArrayList<Module>();
		
		Atlas.instance.eventManager.register(this);
	}
	
	public void init() {
		addModule(new AntiAFK());
		addModule(new AntiKnockback());
		addModule(new AutoMiner());
		addModule(new AutoWalk());
		addModule(new BunnyHop());
		addModule(new ChestFinder());
		addModule(new ClickGUI());			// YEEEEEEEEEEEEEEEEEEEE
		addModule(new Crasher()); //2 bi rimoved from public lol fdp
		addModule(new DamageIndicator()); // useless nice
		addModule(new Dolphin());
		addModule(new FastBreak());
		addModule(new FastEat());
		addModule(new FastLadder());
		addModule(new FastPlace());
		addModule(new Fly());
		addModule(new Fullbright());
		addModule(new Glide());
		addModule(new HighJump());
		addModule(new HUD());
//		addModule(new ItemTP());
		addModule(new Jesus());
		addModule(new KillAura());
//		addModule(new MassTPA()); Marche pas, invalid char in chat,
		addModule(new NoFall());
		addModule(new NoClip());
		addModule(new Nuker());
//		addModule(new Phase());
//		addModule(new PalaRay()); // xray but shit niggaaaaaaaaaaa
		addModule(new Spammer());
		addModule(new Speed());
		addModule(new Spider());
		addModule(new Sprint());
		addModule(new Step());
		addModule(new Test());
		addModule(new TPAura());
		addModule(new UnclaimFinder());
		addModule(new XRay()); // OOOOOOOOWWWWWWWWWWWWWOOOOOOOO
		
//		addModule(new TPWalk());
		
//		addModule(new RainbowText());
	}
	
	private void addModule(Module m) {
		mods.add(m);
	}
	
	public ArrayList<Module> getMods(){
		return mods;
	}
	
	public Module getModule(String name){
		Module mod = null;
		for(Module m : mods) {
			if(m.getName().equalsIgnoreCase(name)) {
				mod = m;
			}
		}
		return mod;
	}
	
	public ArrayList<Module> getModsInCategory(Category cat){
		ArrayList<Module> out = new ArrayList<Module>();
		for(Module m : getMods()) {
			if(m.isCategory(cat)) {
				out.add(m);
			}
		}
		return out;
	}
	
	@EventTarget
	public void onKeyPressed(EventKeyPressed e) {
		for(Module m : mods) {
			if(m.getKey() == e.key) {
				m.toggle();
			}
		}
	}

}
