package it.unisannio.catman.screens.offer.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.ui.EntityListBox;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.screens.offer.client.Offer.Presenter;
import it.unisannio.catman.screens.offer.client.Offer.View;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
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
	Heading offerQuantity;
	
	@UiField
	Heading offerPrice;
	
	@UiField
	Button buyButton;
	
	@UiField
	EntityListBox<WarehouseProxy> moveWarehouse;
	
	@UiField
	IntegerBox moveQuantity;
	
	@UiField
	ControlGroup quantityGroup;
	
	private OfferProxy offer;
	
	private Presenter presenter;
	
	private int quantity = 0;
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public void setPresenter(Presenter p) {
		this.presenter = p;		
	}

	@Override
	public void setOffer(OfferProxy o) {
		this.offer = o;
		
		moveWarehouse.setRequest(App.getInstance().getDataStore().warehouses().findAll());
		buyButton.setEnabled(false);
		
		String name = offer.getMateriel().getName();
		materialName.setText(name);
		titleLabel.setText(name);
		offerPrice.setText(offer.getPrice() + "&euro;");
		
		materialDescription.setText(offer.getMateriel().getDescription());
		
		setQuantity(offer.getQuantity());
	}
	
	@UiHandler("moveWarehouse")
	void handleWarehouseChange(ValueChangeEvent<WarehouseProxy> evt) {
		changeButtonStatus();
	}
	
	@UiHandler("moveQuantity")
	void handleQuantityChange(KeyUpEvent evt) {
		int val = 0;
		try {
			val = moveQuantity.getValueOrThrow();
			quantityGroup.setType(isQuantityOk(val) ? ControlGroupType.NONE : ControlGroupType.WARNING);
		} catch (Exception e) {
			quantityGroup.setType(ControlGroupType.ERROR);
		}
			 
		changeButtonStatus();
	}
	
	private boolean isQuantityOk(int val) {
		return val > 0 && val <= quantity;
	}
	
	private void changeButtonStatus() {
		int val = 0;
		try {
			val = moveQuantity.getValueOrThrow();
		} catch (Exception pe) {
			val = 0;
		}
		
		buyButton.setEnabled(moveWarehouse.getValue() != null && isQuantityOk(val));
	}
	
	@UiHandler("buyButton")
	void handleMoveClick(ClickEvent evt) {
		presenter.buy(offer, moveWarehouse.getValue(), moveQuantity.getValue());
	}
	
	@UiHandler("deleteButton")
	void handleDeleteClick(ClickEvent evt) {
		presenter.delete(offer);
	}
	
	@UiFactory
	EntityListBox<WarehouseProxy> makeWarehouseList() {
		return new EntityListBox<WarehouseProxy>(new AbstractRenderer<WarehouseProxy>() {

			@Override
			public String render(WarehouseProxy object) {
				if(object == null)
					return "--";
				
				return object.getName();
			}
		
		});
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		offerQuantity.setText(String.valueOf(quantity));
	}
}
