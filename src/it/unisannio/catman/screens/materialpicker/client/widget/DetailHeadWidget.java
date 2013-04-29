package it.unisannio.catman.screens.materialpicker.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.HeadWidget;

public class DetailHeadWidget extends HeadWidget{
	
	public DetailHeadWidget(String title) {
		titleLabel.setText(title);
		rightPanel.add(new Button("X"));
		rightPanel.add(new Button("Completato"));
	}
	
	
}
