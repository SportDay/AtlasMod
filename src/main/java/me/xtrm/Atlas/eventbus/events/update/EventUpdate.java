package me.xtrm.Atlas.eventbus.events.update;

import me.xtrm.Atlas.eventbus.Event;

public class EventUpdate extends Event {
	
	public State state;
	public double x, y, z;
	public float yaw, pitch;
	public boolean onGround;
	
	public EventUpdate(State state, double x, double y, double z, float yaw, float pitch, boolean onGround) {
		this.state = state;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
		this.onGround = onGround;
	}
}
