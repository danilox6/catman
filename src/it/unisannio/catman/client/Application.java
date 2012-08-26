package it.unisannio.catman.client;

import java.util.Collection;
import java.util.PriorityQueue;

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
	
	private Application() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
		units = new PriorityQueue<Unit>();
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}
	
	public PlaceController getPlaceController() {
		return placeController;
	}
	
	void onUnitLoaded(Unit u) {
		units.add(u);
	}
	
	public Collection<Unit> getUnits() {
		return units;
	}
}
