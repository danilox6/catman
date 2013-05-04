package it.unisannio.catman.screens.eventmanager.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

import it.unisannio.catman.common.client.widget.BaseActionBarWidget;
import it.unisannio.catman.common.client.widget.DatePickerPopupPanel;
import it.unisannio.catman.common.client.widget.SelectAllButton;
import it.unisannio.catman.common.client.widget.SelectAllHandler;

public class MasterBottomBarWidget<T> extends BaseActionBarWidget{

	public MasterBottomBarWidget(SelectAllHandler<T> selectAllHandler) {

		final DatePickerPopupPanel datePickerPopupPanel = new DatePickerPopupPanel(true);
		
		Button calendarButton = new Button("Calendar");
		calendarButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				Widget source = (Widget) event.getSource();
				int left = source.getAbsoluteLeft() + 5 ;
				int bottom = source.getAbsoluteTop() + 0; 
				datePickerPopupPanel.setLeftBottomPosition(left, bottom);
				datePickerPopupPanel.show();
			}
		});

		leftPanel.add(calendarButton);
		rightPanel.add(new SelectAllButton<T>(selectAllHandler));
	}
	
}
