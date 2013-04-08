package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import it.unisannio.catman.common.client.widget.MasterItemWidget;

public class EventMasterItemWidget extends MasterItemWidget{
	
	public EventMasterItemWidget() {
		leftPanel.add(new Button("A"));
		centerPanel.add(new Label("Nome dell'evento"));
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		horizontalPanel.add(new CheckBox());
		horizontalPanel.add(new Label("ready"));
		horizontalPanel.add(new Label(" - ")); //FIXME apparentemente gli spazi bianchi sono ignorati
		horizontalPanel.add(new Label("Villa Margherita"));
		centerPanel.add(horizontalPanel);
		rightPanel.add(new CheckBox());
	}

}
