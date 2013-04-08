package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

public class RequirementDetailItemWidget extends DetailItemWidget {
	
	public RequirementDetailItemWidget() {
		leftPanel.add(new Button("A"));
		titleLabel.setText("Merce");
		rightPanel.add(new Label("3/3"));
		rightPanel.add(new Button("X"));
	}

}
