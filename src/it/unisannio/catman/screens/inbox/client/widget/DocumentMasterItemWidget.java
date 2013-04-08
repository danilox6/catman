package it.unisannio.catman.screens.inbox.client.widget;

import it.unisannio.catman.common.client.widget.MasterItemWidget;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DocumentMasterItemWidget extends MasterItemWidget{
	
	private Image image;
	private Label documentStateLabel;
	
	public DocumentMasterItemWidget(){
		this("/prova.jpg","Nome del documento","completato");
	}
	
	public DocumentMasterItemWidget(String imageUrl, String documentName, String documentState) { //FIXME state/checkbox
		if(imageUrl==null)
			image = new Image();
		else
			image = new Image(imageUrl);
		image.setHeight("32px");
		image.setWidth("32px"); //FIXME Le dimensioni sono sempre fisse?
		leftPanel.add(image);
		titleLabel.setText(documentName);
		captionPanel.add(new CheckBox());
		documentStateLabel = new Label(documentState);
		captionPanel.add(documentStateLabel);
		centerPanel.add(captionPanel);
	}
	
	public void setDocumentState(String state){
		documentStateLabel.setText(state);
	}

	public void setImage(String imageUrl){
		image.setUrl(imageUrl);
	}
}
