package me.xtrm.Atlas.eventbus.events.render;

import me.xtrm.Atlas.eventbus.Event;

public class EventRender3D extends Event {
	
	public float partialTicks;
	
	public EventRender3D(float partialTicks) {
		this.partialTicks = partialTicks;
	}

}
