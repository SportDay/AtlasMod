package me.xtrm.Atlas.eventbus.events.update;

import me.xtrm.Atlas.eventbus.Event;

public class EventTick extends Event {
	
	public State state;
	
	public EventTick(State state) {
		this.state = state;
	}

}
