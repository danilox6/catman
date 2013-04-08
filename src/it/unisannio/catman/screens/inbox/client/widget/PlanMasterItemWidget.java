package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;

import it.unisannio.catman.common.client.widget.MasterItemWidget;

public class PlanMasterItemWidget extends MasterItemWidget{
	
	public PlanMasterItemWidget() {
		leftPanel.add(new Button("C"));
		centerPanel.add(new Label("Nome del ruolo/"));
		rightPanel.add(new CheckBox());
	}
	

}
