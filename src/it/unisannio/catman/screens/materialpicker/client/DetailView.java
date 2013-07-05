package it.unisannio.catman.screens.materialpicker.client;

import java.util.HashMap;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.screens.materialpicker.client.MaterialPicker.Presenter;
import it.unisannio.catman.screens.materialpicker.client.MaterialPicker.View;
import it.unisannio.catman.screens.materialpicker.client.adapters.SupplyCellAdapter;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements View{

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	@UiField Heading titleLabel;
	
	@UiField DataList<SupplyProxy> dataList;
	
	private QueryDataProvider<SupplyProxy> dataProvider = new QueryDataProvider<SupplyProxy>();
	private HashMap<SupplyProxy, Integer> spinnerValues = new HashMap<SupplyProxy, Integer>(); 
	private HashMap<SupplyProxy, Integer> quantityFilled = new HashMap<SupplyProxy, Integer>(); 
	private SupplyCellAdapter cellAdapter = new SupplyCellAdapter(quantityFilled);
	
	private ProcurementProxy procurement;
	
	private Presenter presenter;
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
	
		dataList.setCellAdapter(cellAdapter);
		dataList.setDataProvider(dataProvider);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public void setProcurementProxy(ProcurementProxy procurement) {
		titleLabel.setText(procurement.getDescription()+ " " + procurement.getQuantityFilled()+"/"+procurement.getQuantity());
		this.procurement = procurement;
		cellAdapter.setProcurement(this.procurement);
		
	}

	@Override
	public void setSupplyQuery(Query<SupplyProxy> query) {
		dataProvider.setQuery(query);
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e){
		SupplyProxy source = (SupplyProxy) e.getSource();
		if(e.getRelativeElement().hasAttribute(SupplyCellAdapter.MOVE_BUTTON_ATTIBUTE)){
			Integer value = spinnerValues.get(source);
			if (value == null || value<0)
				value = 0;
			value = value - quantityFilled.get(source);
		
			if(value > source.getQuantity())
				Window.alert("Not enough units in stock to fulfill the request");
			else if(value > procurement.getQuantity() - procurement.getQuantityFilled())
				Window.alert("Cannot overfill requirement");
			else if(value!=0)
				presenter.moveMateriel((SupplyProxy) e.getSource(), value , procurement);
		}
	}
	
	@UiHandler("dataList")
	void handleCellChange(ChangeEvent e){
		spinnerValues.put((SupplyProxy) e.getSource(), e.getRelativeElement().getPropertyInt("value"));
	}

	@Override
	public void refresh() {
//		dataList.reload();
	}
}
