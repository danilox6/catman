package it.unisannio.catman.screens.eventmanager.client.widget;

import it.unisannio.catman.common.client.widget.SearchActionBarWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

public class MasterHeadWidget extends SearchActionBarWidget{
	
	public MasterHeadWidget(String title) {
		leftPanel.add(new Label(title));
		Button searchModeButton = new Button("Search");
		rightPanel.add(searchModeButton);
		
		setSearchButton(searchModeButton);
	}
	
}
