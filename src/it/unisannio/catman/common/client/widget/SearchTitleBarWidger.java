package it.unisannio.catman.common.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public abstract class SearchTitleBarWidger extends SearchActionBarWidget{
	
	public SearchTitleBarWidger(String title) {
		leftPanel.add(new Label(title));
		Button searchButton = new Button("Search");
		rightPanel.add(searchButton);
		setSearchButton(searchButton);
	}
}
