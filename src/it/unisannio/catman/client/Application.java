package it.unisannio.catman.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;

public class Application {
	private static Application instance;
	
	public static Application getInstance() {
		return (instance == null) ? instance = new Application() : instance;
	}
	
	private final EventBus eventBus;
	private final PlaceController placeController;
	private final PlaceHistoryMapper placeHistoryMapper;
	
	private Application() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
		placeHistoryMapper = GWT.create(ScreenPlaceHistoryMapper.class);
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}
	
	public PlaceController getPlaceController() {
		return placeController;
	}
	
	public PlaceHistoryMapper getPlaceHistoryMapper() {
		return placeHistoryMapper;
	}
}
