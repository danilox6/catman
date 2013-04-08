package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Image;

import it.unisannio.catman.common.client.widget.MasterItemWidget;

public class PlanMasterItemWidget extends MasterItemWidget{
	
	private Image image;
	
	public PlanMasterItemWidget() {
		this("/prova.jpg","Nome del ruolo/");
	}
	
	public PlanMasterItemWidget(String imageUrl, String title){
		if(imageUrl==null)
			image = new Image();
		else
			image = new Image(imageUrl);
		image.setHeight("32px");
		image.setWidth("32px"); //FIXME Le dimensioni sono sempre fisse?
		leftPanel.add(image);
		titleLabel.setText(title);
		rightPanel.add(new CheckBox());
	}
	
	public void setImage(String imageUrl){
		image.setUrl(imageUrl);
	}
	

}
