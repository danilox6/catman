package it.unisannio.catman.screens.inbox.client.widget;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

import com.google.gwt.user.client.ui.Button;

public class DocumentDetailItemWidget extends DetailItemWidget{
	
	public DocumentDetailItemWidget() {
		leftPanel.add(new Button("I"));
		titleLabel.setText("Documento");
		captionLabel.setText("Lorem ipsum dolor sit amet");
		topRightPanel.add(new Button("X"));
	}

}
