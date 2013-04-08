package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

public class ProviderDetailItemWidget extends DetailItemWidget{
	
	public ProviderDetailItemWidget() {
		leftPanel.add(new Button("O"));
		titleLabel.setText("Seller");
		captionLabel.setText("Buy item at 600$/piece");
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(new Button("TODO"));//TODO
		verticalPanel.add(new Label("5 in stock"));
		rightPanel.add(verticalPanel);
	}

}
