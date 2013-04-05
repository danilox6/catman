package it.unisannio.catman.screens.main.client;

import static it.unisannio.catman.common.client.ScreenActivityMapper.Panel.*;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.ScreenActivityMapper;
import it.unisannio.catman.common.client.Path;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.dom.client.Element;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.DOM;
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
		
		DOM.getElementById("root").addClassName("loaded");
	}
	
	private void initMenu() {
		EventBus bus = App.getInstance().getEventBus();
		bus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {

			@Override
			public void onPlaceChange(PlaceChangeEvent event) {
				renderMenu((Path) event.getNewPlace());
			}
			
		});
	}
	
	private void renderMenu(Path current) {

		RootPanel root = RootPanel.get("navigation");

		Path menuPath = current.getMenuPath();
		StringBuffer buf = new StringBuffer();
		
        for(Path p = menuPath; p != null; p = p.pop()) {
        	Intent in = p.peek();
        	Screen s = in.getScreen();
        	Hyperlink a = new Hyperlink(s.getIcon().toString(), p.getToken());
        	a.setTitle(s.getTitle());
        	buf.insert(0,"</li>")
                .insert(0, a)
                .insert(0, "<li>");
        	
        	
        }
        
        buf.insert(0, "<ul id=\"trail\">");
        buf.append("</ul>");
        
		Screen menuScreen = menuPath.peek().getScreen();
		
		
		
       	buf.append("<ul id=\"navigation\">");
        for(Screen s : menuScreen.getChildren()) {
        	Hyperlink a = new Hyperlink(s.getIcon().toString(), new Path(menuPath, s.getSlug()).getToken());
        	a.setTitle(s.getTitle());
            buf
                .append("<li id=\"link-" + s.getSlug() + "\">")
                .append(a)
                .append("</li>");
        }
        buf.append("</ul>");
        
        root.clear();
        root.add(new HTML(buf.toString()));	
        
        Intent masterIntent = current.getMaster();
		if(masterIntent != null) {
			Screen masterScreen = masterIntent.getScreen();
			Element e = DOM.getElementById("link-"+masterScreen.getSlug());
			if(e != null)
				e.addClassName("active");
		}
	}
	
	@Override
	public Screen[] getChildren() {
		return new Screen[] { Screen.get("inbox")};
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
        historyHandler.register(placeController, eventBus, new Path(new Intent("")));
        
        
        // Goes to the place represented on URL else default place
        historyHandler.handleCurrentHistory();
		renderMenu((Path) App.getInstance().getPlaceController().getWhere());
	}
}
