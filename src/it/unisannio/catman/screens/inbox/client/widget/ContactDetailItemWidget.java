package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

public class ContactDetailItemWidget extends DetailItemWidget{
	
	public ContactDetailItemWidget() {
		this("/prova.jpg", "Cognome, Nome", "Lorem ipsum dolor sit amet");
	}
	
	ContactDetailItemWidget(String imageUrl, String title, String caption){
		super(imageUrl, title, caption);
		topRightPanel.add(new Button("X"));
	}
	
	
}
