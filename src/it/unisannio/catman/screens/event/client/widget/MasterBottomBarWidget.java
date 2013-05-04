package it.unisannio.catman.screens.event.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.BaseActionBarWidget;

public class MasterBottomBarWidget extends BaseActionBarWidget{

	public MasterBottomBarWidget() {
		leftPanel.add(new Button("Add"));
	}
}
