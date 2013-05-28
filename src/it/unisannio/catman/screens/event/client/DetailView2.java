package it.unisannio.catman.screens.event.client;

import java.util.List;

import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.event.client.widget.EventCellAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;

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
		
		logisticDataList = new DataList<EventProxy>();
		addButton.setText("+ Aggiungi");
		
		sellsDataList.setCellAdapter(new EventCellAdapter());
		ListDataProvider<EventProxy> dataProvider = new ListDataProvider<EventProxy>();
		sellsDataList.setDataProvider(dataProvider);
		//dataProvider.addDataDisplay(sellsDataList);
		
		List<EventProxy> data = dataProvider.getList();
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		
		//sells.add(sellsDataList);
	}

	@Override
	public void setEventProxy(EventProxy eventProxy) {
		this.eventProxy = eventProxy; 
		titleLabel.setText(eventProxy.getTitle());
		/*sellsDataList.setCellAdapter(new EventCellAdapter());
		ListDataProvider<EventProxy> dataProvider = new ListDataProvider<EventProxy>();
		//sellsDataList.setDataProvider(dataProvider);
		dataProvider.addDataDisplay(sellsDataList);
		
		List<EventProxy> data = dataProvider.getList();
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());
		data.add(new MockEventProxy());*/
	}

	public EventProxy getEventProxy() {
		return eventProxy;
	}
	
	@UiHandler("addButton")
	void handleAddButton(ClickEvent event){
		
	}
	
	class MockEventProxy implements EventProxy{

		@Override
		public EntityProxyId<?> stableId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTitle(String title) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
