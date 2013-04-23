package it.unisannio.catman.screens.plan.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import it.unisannio.catman.common.client.widget.BaseActionBarWidget;

public class MasterBottomBarWidget extends BaseActionBarWidget{

	public MasterBottomBarWidget() {


		leftPanel.add(new Button("Cancella"));

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
		});
*/
		rightPanel.add(selectAllButton);
	}
}
