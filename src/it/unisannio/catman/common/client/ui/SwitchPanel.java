package it.unisannio.catman.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class SwitchPanel extends Composite {

	private static SwitchPanelUiBinder uiBinder = GWT
			.create(SwitchPanelUiBinder.class);

	interface SwitchPanelUiBinder extends UiBinder<Widget, SwitchPanel> {
	}
	
	@UiField SimplePanel left;
	@UiField SimplePanel right;
	
	private IsWidget normal;
	private IsWidget alternate;
	private HasClickHandlers switchButton;
	
	private boolean isNormal = true;

	public SwitchPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public SwitchPanel(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiChild(tagname = "normal")
	public void setNormal(IsWidget w) {
		normal = w;
		left.setWidget(w);
	}

	@UiChild(tagname = "alternate")
	public void setAlternate(IsWidget w) {
		alternate = w;
	}
	
	public void toggle() {
		isNormal = !isNormal;
		left.setWidget(isNormal ? normal : alternate);
	}
	
	@UiChild(tagname = "switch")
	public void setSwitch(HasClickHandlers sw) {
		switchButton = sw;
		switchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				toggle();
			}
			
		});
		
		if(sw instanceof IsWidget)
			right.setWidget((IsWidget) sw);
	}

}
