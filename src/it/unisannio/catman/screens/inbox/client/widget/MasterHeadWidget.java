package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import it.unisannio.catman.common.client.widget.SearchActionBarWidget;


public class MasterHeadWidget extends SearchActionBarWidget{

	public MasterHeadWidget(String title) {
		leftPanel.add(new Label(title));
		Button searchModeButton = new Button("Search");
		rightPanel.add(searchModeButton);
		
		setSearchButton(searchModeButton);
	}
}
