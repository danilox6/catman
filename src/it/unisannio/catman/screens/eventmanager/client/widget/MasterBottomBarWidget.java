package it.unisannio.catman.screens.eventmanager.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;

import it.unisannio.catman.common.client.widget.BaseActionBarWidget;

public class MasterBottomBarWidget extends BaseActionBarWidget{

	public MasterBottomBarWidget() {

		final PopupPanel calendarPopupPanel = new PopupPanel(true);
		final DatePicker datePicker = new DatePicker(); 
		calendarPopupPanel.add(datePicker);
		//datePicker.setHeight("100px");

		Button calendarButton = new Button("Calendar");
		calendarButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {


				Widget source = (Widget) event.getSource();
				int left = source.getAbsoluteLeft() + 10 ;
				int top = source.getAbsoluteTop() + 10 - 180; //FIXME Hardcoded size
				calendarPopupPanel.setPopupPosition(left, top); //TODO resizando la finestra deve essere spostato
				calendarPopupPanel.show(); 
			}
		});

		leftPanel.add(calendarButton);

		final Button selectAllButton = new Button("Select All");
		selectAllButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//MasterView.getInstance().getMasterItemList().selectOrUnselectAll();
			}
		});
		/*
		App.getInstance().getEventBus().addHandler(MultiSelectionChangedEvent.TYPE,new MasterItemListPanel.MultiSelectionChangedHandler() {

			@Override
			public void onMultiSelectionChanged(MultiSelectionChangedEvent event) {
				switch (event.getState()) {
				case ALL_SELECTED:
					selectAllButton.setText("Deselect All"); //TODO Icona tutto selezionato
					break;
				case SOME_SELECTED:
					selectAllButton.setText("Deselect All"); //TODO Icona alcuni selezionati (vedi Gmail)
					break;
				case NONE_SELECTED:
					selectAllButton.setText("Select All"); //TODO Icona nessuno selezionato
					break;
				}
			}
		});*/

		rightPanel.add(selectAllButton);
	}
}
