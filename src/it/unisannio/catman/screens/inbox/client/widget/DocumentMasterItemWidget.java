package it.unisannio.catman.screens.inbox.client.widget;

import it.unisannio.catman.common.client.widget.MasterItemWidget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class DocumentMasterItemWidget extends MasterItemWidget{
	
	public DocumentMasterItemWidget() {
		leftPanel.add(new Button("B"));
		centerPanel.add(new Label("Nome del documento"));
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		horizontalPanel.add(new CheckBox());
		horizontalPanel.add(new Label("completato"));
		centerPanel.add(horizontalPanel);
	}

}
