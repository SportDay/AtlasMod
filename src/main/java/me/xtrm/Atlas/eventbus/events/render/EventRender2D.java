package me.xtrm.Atlas.eventbus.events.render;

import me.xtrm.Atlas.eventbus.Event;

public class EventRender2D extends Event {
	
	public float partialTicks;
	
	public EventRender2D(float partialTicks) {
		this.partialTicks = partialTicks;
	}

}
