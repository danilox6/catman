package it.unisannio.catman.screens.event.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.BaseActionBarWidget;

//FIXME i nomi che scelgo per le classi puzzano
public class MasterBottomBarWidget extends BaseActionBarWidget{

	public MasterBottomBarWidget() {
		leftPanel.add(new Button("Add"));
	}
}
