package me.xtrm.Atlas.module.modules.misc;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.module.Module;

public class Test extends Module {
	
	public Test() {
		super("Test", Category.Miscellaneous);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}

}
