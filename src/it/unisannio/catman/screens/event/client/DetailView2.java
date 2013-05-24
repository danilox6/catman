package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.workflow.client.EventProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailView2 extends Composite implements Event.Detail.View {
	interface Presenter{}

	private static DetailView2UiBinder uiBinder = GWT.create(DetailView2UiBinder.class);

	interface DetailView2UiBinder extends UiBinder<Widget, DetailView2> {}
	
	private EventProxy eventProxy;
	
	@UiField Label titleLabel;
	@UiField Button addButton;
	@UiField DataList<EventProxy> sellsDataList;
	@UiField DataList<EventProxy> logisticDataList;

	public DetailView2() {
		initWidget(uiBinder.createAndBindUi(this));
		
		sellsDataList = new DataList<EventProxy>();
		logisticDataList = new DataList<EventProxy>();
		addButton.setText("+ Aggiungi");
	}

	@Override
	public void setEventProxy(EventProxy eventProxy) {
		this.eventProxy = eventProxy;
		titleLabel.setText(eventProxy.getTitle());
	}

	public EventProxy getEventProxy() {
		return eventProxy;
	}
	
	@UiHandler("addButton")
	void handleAddButton(ClickEvent event){
		
	}

}
