package it.unisannio.catman.screens.personnelpicker.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.TitleHeadWidget;

public class DetailHeadWidget extends TitleHeadWidget{
	
	public DetailHeadWidget(String title) {
		titleLabel.setText(title);
		rightPanel.add(new Button("X"));
		rightPanel.add(new Button("+ Aggiungi"));
		
	}
	
	
}
