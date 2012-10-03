package it.unisannio.catman.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class Application {
	private static Application instance;
	
	public static Application get() {
		return (instance == null) ? instance = new Application() : instance;
	}
	
	private final EventBus eventBus;
	private final PlaceController placeController;
	private final PriorityQueue<Unit> units;
	private final Map<String, Unit> unitsMapping;
	
	private Application() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
		units = new PriorityQueue<Unit>();
		unitsMapping = new HashMap<String, Unit>();
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}
	
	public PlaceController getPlaceController() {
		return placeController;
	}
	
	void onUnitLoaded(Unit u) {
		units.add(u);
		unitsMapping.put(u.getName(), u);
	}
	
	public Queue<Unit> getUnits() {
		return units;
	}
	
	public Unit getUnit(String name) {
		return unitsMapping.get(name);
	}
}
