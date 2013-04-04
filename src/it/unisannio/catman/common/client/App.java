package it.unisannio.catman.common.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;

public class App {
	private static App instance;
	
	public static void goTo(Trail trail) {
		App.getInstance().getPlaceController().goTo(trail);
	}
	
	public static App getInstance() {
		return (instance == null) ? instance = new App() : instance;
	}
	
	private final EventBus eventBus;
	private final PlaceController placeController;
	private final PlaceHistoryMapper placeHistoryMapper;
	
	private App() {
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
