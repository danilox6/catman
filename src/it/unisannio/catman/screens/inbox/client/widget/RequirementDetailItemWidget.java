package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.DetailItemWidget;

public class RequirementDetailItemWidget extends DetailItemWidget {
	
	public RequirementDetailItemWidget() {
		this("/prova.jpg","Merce","3/3");
		
	}
	
	public RequirementDetailItemWidget(String imageUrl, String title, String caption){
		super(imageUrl, title, caption);
		rightPanel.add(new Button("X"));
		image.setWidth("32px");
		image.setHeight("32px");
		topRightPanel.setVisible(false);
	}

}
