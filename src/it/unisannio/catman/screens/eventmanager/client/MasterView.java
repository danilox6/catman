package it.unisannio.catman.screens.eventmanager.client;

import java.util.Date;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.common.client.ui.SwitchPanel;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.event.client.widgets.DatePickerPopupPanel;
import it.unisannio.catman.screens.eventmanager.client.adapters.EventCellAdapter;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements EventManager.Master.View, ValueChangeHandler<Date>{

	private static MasterView2UiBinder uiBinder = GWT.create(MasterView2UiBinder.class);

	interface MasterView2UiBinder extends UiBinder<Widget, MasterView> {}

	@UiField DataList<EventProxy> dataList;
	@UiField Button calendarButton;
	//@UiField SelectAllButton selectButton;
	@UiField Form form;
	@UiField TextBox searchTextBox;
	@UiField Button toggleButton;
	@UiField SwitchPanel switchPanel;
	
	private Query<EventProxy> query;
	
	private DatePickerPopupPanel datePickerPanel = new DatePickerPopupPanel(true);
	private QueryDataProvider<EventProxy> dataProvider;
	
	private final EventManager.Master presenter;
	
	public MasterView(EventManager.Master presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.presenter = presenter;

		dataList.setCellAdapter(new EventCellAdapter());
		
		dataProvider = new QueryDataProvider<EventProxy>(query);
		//selectButton.setDataProvider(dataProvider);
		
		dataList.setDataProvider(dataProvider);
		
		datePickerPanel.addValueChangeHandler(this);
	}
	
	@UiHandler("calendarButton")
	protected void handleCalendarButtonClick(ClickEvent event){
		int left = calendarButton.getAbsoluteLeft() + 5 ;
		int bottom = calendarButton.getAbsoluteTop() + 0; 
		datePickerPanel.setLeftBottomPosition(left, bottom);
		datePickerPanel.show();
		
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

	@UiHandler("form")
	void handleSubmitEvent(Form.SubmitEvent e){
		presenter.executeSearch(searchTextBox.getText(),datePickerPanel.getDatePicker().getValue());
	}
	
	@UiHandler("toggleButton")
	void handleSearchToggleClick(ClickEvent e){
		if(toggleButton.isToggled())
			if(!searchTextBox.getText().trim().equals("")){
				presenter.executeSearch(null,null);
				datePickerPanel.getDatePicker().setValue(new Date()); //Today
				datePickerPanel.getDatePicker().setValue(null);
				searchTextBox.setText("");
			}
			
	}
	
	@Override
	public void onValueChange(ValueChangeEvent<Date> event) {
		presenter.executeSearch(searchTextBox.getText(),event.getValue());
//		toggleButton //FIXME toggle il bottone se non è gia toggled
	}
}
