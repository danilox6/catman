package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.BaseActionBarWidget;

public class BottomMasterBar extends BaseActionBarWidget{
	
	public BottomMasterBar() {
		
		leftPanel.add(new Button("F"));
		leftPanel.add(new Button("I"));
		rightPanel.add(new Button("N"));
		rightPanel.add(new Button("T"));
		rightPanel.add(new Button("I"));
	}
}
