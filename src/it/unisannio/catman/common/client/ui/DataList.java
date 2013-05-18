package it.unisannio.catman.common.client.ui;

import it.unisannio.catman.common.client.DataProviderSelectionSyncronizer;
import it.unisannio.catman.common.client.cell.CellAdapter;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.domain.documents.client.DossierProxy;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class DataList<T> extends Composite implements HasClickHandlers {

	private MasterCell<T> cell;
	private CellList<T> list;
	
	private MultiSelectionModel<T> selectionModel;
	
	private SelectableCellAdapter<T> adapter;
	private AbstractDataProvider<T> provider;
	
	public DataList() {
		cell = new MasterCell<T>(null); // FIXME
		list = new CellList<T>(cell); 

		
		selectionModel = new MultiSelectionModel<T>();
		list.setSelectionModel(selectionModel, DefaultSelectionEventManager.<T>createCheckboxManager());		
		
		initWidget(list);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return cell.addClickHandler(handler);
	}

	public void setDataProvider(AbstractDataProvider<T> provider) {
		this.provider = provider;
		this.provider.addDataDisplay(list);
		//DataProviderSelectionSyncronizer.<T>sync(selectionModel, provider);
	}
	
	public void setCellAdapter(SelectableCellAdapter<T> adapter) {
		this.adapter = adapter;
		this.adapter.setSelectionModel(selectionModel);
		cell.setCellAdapter(adapter);
	}
	
}
