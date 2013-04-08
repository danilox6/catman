package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

public class ContactDetailItemWidget extends DetailItemWidget{
	
	public ContactDetailItemWidget() {
		leftPanel.add(new Button("C"));
		titleLabel.setText("Cognome, Nome");
		captionLabel.setText("Lorem ipsum dolor sit amet");
		topRightPanel.add(new Button("X"));
	}
}
