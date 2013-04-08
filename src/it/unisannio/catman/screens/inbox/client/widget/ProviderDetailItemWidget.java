package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

public class ProviderDetailItemWidget extends DetailItemWidget{
	
	public ProviderDetailItemWidget() {
		this("/prova.jpg","Seller","Buy item at 600$/piece");
		
	}

	public ProviderDetailItemWidget(String imageUrl, String title, String caption){
		super(imageUrl, title, caption);
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(new Button("TODO"));//TODO Implementare widget per la selezione del numero
		verticalPanel.add(new Label("5 in stock"));
		rightPanel.add(verticalPanel);
		topRightPanel.setVisible(false);
	}
}
