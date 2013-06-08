package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.ui.DataEditor;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseRequest;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class WarehouseEditor extends DataEditor<WarehouseProxy, WarehouseRequest, WarehouseEditor> {

	private static WarehouseEditorUiBinder uiBinder = GWT.create(WarehouseEditorUiBinder.class);

	interface WarehouseEditorUiBinder extends UiBinder<Widget, WarehouseEditor> {}
	
	interface Driver extends RequestFactoryEditorDriver<WarehouseProxy, WarehouseEditor> {}
	
	@UiField
	TextBox nameEditor;
	
	public WarehouseEditor() {
		super(App.getInstance().getDataStore().warehouses(), WarehouseProxy.class);
		setForm(uiBinder.createAndBindUi(this));
		setTitle("Add/edit Warehouse");
	}


	@Override
	protected void save(WarehouseRequest context, WarehouseProxy entity) {
		if(isNew()) 
			context.persist().using(entity);		
	}

	@Override
	protected RequestFactoryEditorDriver<WarehouseProxy, ?> init() {
		Driver driver = GWT.create(Driver.class);
		driver.initialize(App.getInstance().getDataStore(), this);
		
		return driver;
	}

}
