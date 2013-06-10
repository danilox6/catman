package it.unisannio.catman.screens.warehouse.client;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.screens.warehouse.client.adapters.StockDetailAdapter;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements Warehouse.Detail.View{

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	//FIXME il titolo appare più in basso dopo aver aggiunto la barra di ricerca
	
	@UiField Heading titleLabel;
	@UiField DataList<StockProxy> dataList;
	
	private QueryDataProvider<StockProxy> dataProvider = new QueryDataProvider<StockProxy>();
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new StockDetailAdapter());
		dataList.setDataProvider(dataProvider);
	}

	@Override
	public void setWarehouseProxy(WarehouseProxy warehouseProxy) {
		titleLabel.setText(warehouseProxy.getName());
	}
	
	@Override
	public void setStockProxyQuery(Query<StockProxy> query) {
		dataProvider.setQuery(query);
	}

}
