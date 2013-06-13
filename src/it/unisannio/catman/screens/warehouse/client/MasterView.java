package it.unisannio.catman.screens.warehouse.client;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.common.client.ui.SelectAllButton;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.screens.warehouse.client.Warehouse.Presenter;
import it.unisannio.catman.screens.warehouse.client.adapters.StockMasterAdapter;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements Warehouse.View{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	//TODO il bottone per cancellare deve apparire solo quando qualcosa è selezionato, altrimenti va mostrato quello per aggiungere uno Stock

	@UiField Heading titleLabel;
	@UiField SelectAllButton selectButton;
	@UiField DataList<StockProxy> dataList;
	
	private QueryDataProvider<StockProxy> dataProvider = new QueryDataProvider<StockProxy>();
	private Presenter presenter;
	
	
	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new StockMasterAdapter());
		dataList.setDataProvider(dataProvider);
		
		selectButton.setDataProvider(dataProvider);
	}

	@Override
	public void setWarehouseProxy(WarehouseProxy warehouseProxy) {
		titleLabel.setText(warehouseProxy.getName());
	}
	
	@Override
	public void setStockProxyQuery(Query<StockProxy> query) {
		dataProvider.setQuery(query);
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e){
		presenter.goToStockScreen((StockProxy) e.getSource());
	}

}
