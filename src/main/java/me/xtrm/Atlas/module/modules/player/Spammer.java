package me.xtrm.Atlas.module.modules.player;

import java.util.Random;

import me.xtrm.Atlas.eventbus.EventTarget;
import me.xtrm.Atlas.eventbus.events.update.EventUpdate;
import me.xtrm.Atlas.main.Atlas;
import me.xtrm.Atlas.module.Module;
import me.xtrm.Atlas.settings.Setting;
import me.xtrm.Atlas.utils.TimeHelper;

public class Spammer extends Module {

	public static String message = "Atlas v" + Atlas.instance.ver + " by xTrM_ | K0SMoS > All | nano-dev(.)xyz";
	
	public Spammer() {
		super("Spammer", Category.Player);
		addSetting(new Setting("Delay", this, 5020D, 0D, 10000D, false));
		addSetting(new Setting("AntiSpam", this, true));
	}
	
	private TimeHelper timer = new TimeHelper();
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(!timer.hasReached((long)gsu("Delay").doubl()))
			return;
		
		timer.reset();
		String msg = message;
		
		if(gsu("AntiSpam").bool()) {
			Random r = new Random();
			msg = r.nextInt(100000) + " " + msg + " " + r.nextInt(100000);
		}
		
		mc.thePlayer.sendChatMessage(msg);
	}

}
