package it.unisannio.catman.screens.eventmanager.client;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.common.client.ui.SelectAllButton;
import it.unisannio.catman.common.client.widget.DatePickerPopupPanel;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.eventmanager.client.adapters.EventCellAdapter;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements EventManager.Master.View{

	private static MasterView2UiBinder uiBinder = GWT.create(MasterView2UiBinder.class);

	interface MasterView2UiBinder extends UiBinder<Widget, MasterView> {}

	@UiField DataList<EventProxy> dataList;
	@UiField Button calendarButton;
	@UiField SelectAllButton selectButton;
	
	private Query<EventProxy> query;
	
	private DatePickerPopupPanel datePickerPopupPanel = new DatePickerPopupPanel(true);
	private QueryDataProvider<EventProxy> dataProvider;
	
	private final EventManager.Master presenter;
	
	public MasterView(EventManager.Master presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.presenter = presenter;

		dataList.setCellAdapter(new EventCellAdapter());
		
		dataProvider = new QueryDataProvider<EventProxy>(query);
		selectButton.setDataProvider(dataProvider);
		
		dataList.setDataProvider(dataProvider);
	}
	
	@UiHandler("calendarButton")
	protected void handleCalendarButtonClick(ClickEvent event){
		int left = calendarButton.getAbsoluteLeft() + 5 ;
		int bottom = calendarButton.getAbsoluteTop() + 0; 
		datePickerPopupPanel.setLeftBottomPosition(left, bottom);
		datePickerPopupPanel.show();
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e) {
		EventProxy event = (EventProxy) e.getSource();
		presenter.goToEventScreen(event);
		
	}

	@Override
	public void setEventQuery(Query<EventProxy> query) {
		dataProvider.setQuery(query);
	}

}
