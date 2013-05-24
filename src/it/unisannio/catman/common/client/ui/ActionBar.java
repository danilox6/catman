package it.unisannio.catman.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ActionBar extends Composite  {

	private static ActionBarUiBinder uiBinder = GWT.create(ActionBarUiBinder.class);

	interface ActionBarUiBinder extends UiBinder<Widget, ActionBar> {}

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
	public void addLeftWidget(Widget w) {
		leftPanel.add(w);
	}

	@UiChild(tagname = "right")
	public void addRightWidget(Widget w) {
		rightPanel.add(w);
	}

}
