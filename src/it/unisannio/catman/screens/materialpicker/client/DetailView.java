package it.unisannio.catman.screens.materialpicker.client;

import java.util.HashMap;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.domain.planning.client.SourceProxy;
import it.unisannio.catman.screens.materialpicker.client.MaterialPicker.Presenter;
import it.unisannio.catman.screens.materialpicker.client.MaterialPicker.View;
import it.unisannio.catman.screens.materialpicker.client.adapters.SupplyCellAdapter;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements View{

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	@UiField Heading titleLabel;
	
	@UiField DataList<SupplyProxy> dataList;
	
	@UiField SimplePanel alertContainer;
	
	private QueryDataProvider<SupplyProxy> dataProvider = new QueryDataProvider<SupplyProxy>();
	private HashMap<SupplyProxy, Integer> spinnerValues = new HashMap<SupplyProxy, Integer>(); 
	private SupplyCellAdapter cellAdapter = new SupplyCellAdapter();
	
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
		if(e.getRelativeElement().hasAttribute(SupplyCellAdapter.ADD_BUTTON_ATTIBUTE) ||
				e.getRelativeElement().hasAttribute(SupplyCellAdapter.REMOVE_BUTTON_ATTIBUTE)){
			Integer value = spinnerValues.get(source);
			if (value == null || value<=0){
				showErrorAlert("Quantity must be a number greater than 0");
				return;
			}

			if(e.getRelativeElement().hasAttribute(SupplyCellAdapter.ADD_BUTTON_ATTIBUTE)){
				if(value > source.getQuantity()){
					showErrorAlert("Not enough units in stock to fulfill the request");
					return;
				}
				else if(value > procurement.getQuantity() - procurement.getQuantityFilled()){
					showErrorAlert("Cannot overfill requirement");
					return;
				}
			}else{
				if(value > getAlreadyFilledBy(source)){
					showErrorAlert("Cannot unassign more than assigned units");
					return;
				}
				value = -value;
			}
			presenter.moveMateriel((SupplyProxy) e.getSource(), value, procurement);
			spinnerValues.clear(); // spinnerValues.put(source,null);
		}
	}
	
	@UiHandler("dataList")
	void handleCellChange(ChangeEvent e){
		spinnerValues.put((SupplyProxy) e.getSource(), e.getRelativeElement().getPropertyInt("value"));
	}
	
	public void showErrorAlert(String message) {
		Alert a = new Alert(message,AlertType.ERROR,false);
		a.setAnimation(true);
		alertContainer.setWidget(a);
	}

	@Override
	public void refresh() {
		alertContainer.clear();
//		dataList.reload();
	}
	
	private int getAlreadyFilledBy(SupplyProxy supply){
		int count = 0;
		for(SourceProxy source : procurement.getSources())
			if(source.getSupply().equals(supply))
				count += source.getQuantity();
		return count;
	}
}
