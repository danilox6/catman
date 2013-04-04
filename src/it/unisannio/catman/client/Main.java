package it.unisannio.catman.client;

import static it.unisannio.catman.client.ScreenActivityMapper.Panel.*;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class Main extends Screen {

	protected Main() {
		super("Main menu", "", Icon.CIRCLES);
	}

	@Override
	public void onModuleLoad() {
		super.onModuleLoad();
		initNavigation();
		initMenu();	
	}
	
	private void initMenu() {
		// TODO
		PlaceHistoryMapper phm = App.getInstance().getPlaceHistoryMapper();
        RootPanel root = RootPanel.get("navigation");
        StringBuffer buf = new StringBuffer("<ul id=\"navigation\">");
        Trail home = new Trail(new Intent(""));
        for(Screen s : getChildren()) {
                buf
                        .append("<li>")
                        .append(new Hyperlink(s.getIcon().toString(), new Trail(home, "inbox").getToken()))
                        .append("</li>");
        }
        buf.append("</ul>");
        root.add(new HTML(buf.toString()));
	}
	
	@Override
	public Screen[] getChildren() {
		return new Screen[] { Screen.get("inbox") };
	}

	private void initNavigation() {
		App app = App.getInstance();
		EventBus eventBus = app.getEventBus();
        PlaceController placeController = app.getPlaceController();

        // Start ActivityManager for the main widget with our ActivityMapper
        
        SimplePanel master = new SimplePanel();
        RootPanel.get("master").add(master);
        
        ActivityManager masterActivityManager = new ActivityManager(new ScreenActivityMapper(MASTER), eventBus);
        masterActivityManager.setDisplay(master);

        SimplePanel detail = new SimplePanel();
        RootPanel.get("detail").add(detail);
        
        ActivityManager detailActivityManager = new ActivityManager(new ScreenActivityMapper(DETAIL), eventBus);
        detailActivityManager.setDisplay(detail);

        // Start PlaceHistoryHandler with our PlaceHistoryMapper
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(app.getPlaceHistoryMapper());
        historyHandler.register(placeController, eventBus, new Trail(new Intent("")));

        
        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();
	}
}
