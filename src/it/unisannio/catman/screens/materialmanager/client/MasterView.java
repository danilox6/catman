package it.unisannio.catman.screens.materialmanager.client;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
import it.unisannio.catman.screens.materialmanager.client.adapters.SupplierCellAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("rawtypes") 
public class MasterView extends Composite implements MaterialManager.Master.View{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}

	@UiField DataList<SupplierProxy> dataList;
	
	private QueryDataProvider<SupplierProxy> dataProvider = new QueryDataProvider<SupplierProxy>();
	
	private MaterialManager.Master presenter;
	
	public MasterView(MaterialManager.Master presenter) {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new SupplierCellAdapter());
		dataList.setDataProvider(dataProvider);
		
		this.presenter = presenter;
		
	}

	@Override
	public void setSupplierQuery(Query<SupplierProxy> query) {
		dataProvider.setQuery(query);
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e){
		presenter.goToSupplyScreen((SupplierProxy) e.getSource());
	}

}
