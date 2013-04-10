package it.unisannio.catman.screens.event.client.widget;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

import com.google.gwt.user.client.ui.Button;

public class DocumentDetailItemWidget extends DetailItemWidget{
	
	public DocumentDetailItemWidget() {
		this("/prova.jpg","Documento","Lorem ipsum dolor sit amet");
	}
	
	public DocumentDetailItemWidget(String imageUrl, String title, String caption){
		super(imageUrl, title, caption);
		topRightPanel.add(new Button("X"));
	}

}
