package it.unisannio.catman.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ActionBar extends Composite  {

	private static ActionBarUiBinder uiBinder = GWT
			.create(ActionBarUiBinder.class);

	interface ActionBarUiBinder extends UiBinder<Widget, ActionBar> {
	}

	public ActionBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	HorizontalPanel leftPanel;
	
	@UiField
	HorizontalPanel rightPanel;

	public ActionBar(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiChild(tagname = "left")
	public void addLeftButton(Button b) {
		leftPanel.add(b);
	}

	@UiChild(tagname = "right")
	public void addRightButton(Button b) {
		rightPanel.add(b);
	}

}
