package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import it.unisannio.catman.common.client.MultiSelectable;
import it.unisannio.catman.common.client.widget.MasterItemWidget;

public class EventMasterItemWidget extends MasterItemWidget implements MultiSelectable{

	private Image image;
	private Label eventStateLabel;
	private Label eventPlaceLabel;
	private CheckBox multipleSelectionCheckBox;

	public EventMasterItemWidget() {
		this("/prova.jpg","Nome dell'evento","ready","Villa Margherita");
	}

	public EventMasterItemWidget(String imageUrl, String name, String eventState, String eventPlace){ //@UiConstructor se si elimina il costruttere con 0 parametri
		if(imageUrl==null)
			image = new Image();
		else
			image = new Image(imageUrl);
		image.setHeight("32px");
		image.setWidth("32px"); //FIXME Le dimensioni sono sempre fisse?
		leftPanel.add(image);
		titleLabel.setText(name);
		captionPanel.add(new CheckBox());
		eventStateLabel = new Label(eventState);
		captionPanel.add(eventStateLabel);
		captionPanel.add(new HTML("&nbsp;-&nbsp;"));
		eventPlaceLabel = new Label(eventPlace);
		captionPanel.add(eventPlaceLabel);
		multipleSelectionCheckBox = new CheckBox();
		rightPanel.add(multipleSelectionCheckBox);
	}

	public void setEventState(String eventState){
		eventStateLabel.setText(eventState);
	}

	public void setEventPlace(String eventPlace){
		eventStateLabel.setText(eventPlace);
	}

	public void setImage(String imageUrl){
		image.setUrl(imageUrl);
	}
	
	@Override
	public String toString() {
		return this.getClass().getName();
	}
	
	@Override
	public void setSelected(boolean selected) {
		multipleSelectionCheckBox.setValue(selected);
	}

	@Override
	public boolean isSelected() {
		return multipleSelectionCheckBox.getValue();
	}
}
