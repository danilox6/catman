package it.unisannio.catman.common.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public abstract class SearchTitleBarWidger extends SearchActionBarWidget{
	
	Button searchButton;
	
	public SearchTitleBarWidger(String title) {
		leftPanel.add(new Label(title));
		searchButton = new Button("Search");
		rightPanel.add(searchButton);
		setSearchButton(searchButton);
	}
	
}
