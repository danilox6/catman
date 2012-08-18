package it.unisannio.catman.client;

import java.util.Collection;
import java.util.PriorityQueue;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class Application {
	private static Application instance;
	
	public static Application get() {
		return (instance == null) ? instance = new Application() : instance;
	}
	
	private final EventBus eventBus;
	private final PriorityQueue<Unit> units;
	
	private Application() {
		eventBus = new SimpleEventBus();
		units = new PriorityQueue<Unit>();
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}
	
	void onUnitLoaded(Unit u) {
		units.add(u);
	}
	
	public Collection<Unit> getUnits() {
		return units;
	}
}
