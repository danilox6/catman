package it.unisannio.catman.common.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;

public class App {
	private static App instance;
	
	public static void goTo(Path path) {
		App.getInstance().getPlaceController().goTo(path);
	}
	
	public static App getInstance() {
		return (instance == null) ? instance = new App() : instance;
	}
	
	private final EventBus eventBus;
	private final PlaceController placeController;
	private final PlaceHistoryMapper placeHistoryMapper;
	private final DataStore dataStore;
	
	private App() {
		eventBus = new SimpleEventBus();
		placeController = new PlaceController(eventBus);
		placeHistoryMapper = new  ScreenPlaceHistoryMapper();
		dataStore = GWT.create(DataStore.class);
		dataStore.initialize(eventBus);
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
	
	public DataStore getDataStore() {
		return dataStore;
	}
}
