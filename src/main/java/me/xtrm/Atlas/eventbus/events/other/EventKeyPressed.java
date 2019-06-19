package me.xtrm.Atlas.eventbus.events.other;

import me.xtrm.Atlas.eventbus.Event;

public class EventKeyPressed extends Event {
	
	public int key;
	
	public EventKeyPressed(int key) {
		this.key = key;
	}

}
