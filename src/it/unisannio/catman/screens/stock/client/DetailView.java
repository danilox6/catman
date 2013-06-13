package it.unisannio.catman.screens.stock.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.ui.EntityListBox;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseRequest;
import it.unisannio.catman.screens.stock.client.Stock.Presenter;
import it.unisannio.catman.screens.stock.client.Stock.View;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements View {

	private static DetailViewUiBinder uiBinder = GWT
			.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {
	}
	
	@UiField
	Button deleteButton;
	
	@UiField
	Heading titleLabel;
	
	@UiField
	Heading materialName;
	
	@UiField 
	Paragraph materialDescription;
	
	@UiField
	Heading stockQuantity;
	
	@UiField
	Button moveButton;
	
	@UiField(provided = true)
	EntityListBox<WarehouseProxy, WarehouseRequest> moveWarehouse = new EntityListBox<WarehouseProxy, WarehouseRequest>(
			App.getInstance().getDataStore().warehouses(), 
			new AbstractRenderer<WarehouseProxy>() {

				@Override
				public String render(WarehouseProxy object) {
					if(object == null)
						return null;
					
					return object.getName();
				}
			
			});
	
	@UiField
	IntegerBox moveQuantity;
	
	private StockProxy stock;
	
	private Presenter presenter;

	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(Presenter p) {
		this.presenter = p;		
	}

	@Override
	public void setStock(StockProxy st) {
		this.stock = st;
		
		String name = stock.getMateriel().getName();
		materialName.setText(name);
		titleLabel.setText(name);
		
		materialDescription.setText(stock.getMateriel().getDescription());
		stockQuantity.setText(String.valueOf(stock.getQuantity()));
	}
	
	@UiHandler("moveButton")
	void handleMoveClick(ClickEvent evt) {
		
	}
	
	@UiHandler("deleteButton")
	void handleDeleteClick(ClickEvent evt) {
		
	}
}
