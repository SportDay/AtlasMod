package me.xtrm.Atlas.eventbus;

import java.lang.reflect.InvocationTargetException;

import me.xtrm.Atlas.main.Atlas;

public abstract class Event {

	private boolean cancelled;

	public enum State {
		PRE, POST;
	}

	public Event call() {
		this.cancelled = false;
		call(this);
		return this;
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean cancelled) {

		this.cancelled = cancelled;
	}

	private static void call(Event event) {
		ArrayHelper<Data> dataList = Atlas.instance.eventManager.get(event.getClass());
		if (dataList != null) {
			for (Data data : dataList) {
				try {
					data.target.invoke(data.source, event);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}
	}
}