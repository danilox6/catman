package it.unisannio.catman.common.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;

public class SelectAllButton<T> extends Button implements SelectionChangeEvent.Handler, ClickHandler{

	 SelectAllHandler<T> selectAllHandler;
	
	public SelectAllButton(String html, MultiSelectionModel<T> selectionModel, ListDataProvider<T> dataProvider) {
		this(html, new SelectAllHandler<T>(selectionModel, dataProvider));
	}
	
	public SelectAllButton(String html, SelectAllHandler<T> selectAllHandler){
		super(html);
		selectAllHandler.getMultiSelectionModel().addSelectionChangeHandler(this);
		this.selectAllHandler = selectAllHandler;
		this.addClickHandler(this);
	}
	
	@Override
	public void onClick(ClickEvent event) {
		selectAllHandler.selectOrDeselect();
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		switch (selectAllHandler.getSelectionState()) {
		case ALL_SELECTED:
			setText("Deselect All"); //TODO Icona tutto selezionato
			break;
		case SOME_SELECTED:
			setText("Deselect All"); //TODO Icona alcuni selezionati (vedi Gmail)
			break;
		case NONE_SELECTED:
			setText("Select All"); //TODO Icona nessuno selezionato
			break;
		}
	}
	
	public MultiSelectionModel<T> getMultiSelectionModel(){
		return selectAllHandler.getMultiSelectionModel();
	}

}
