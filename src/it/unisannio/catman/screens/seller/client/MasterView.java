package it.unisannio.catman.screens.seller.client;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.SellerProxy;
import it.unisannio.catman.screens.seller.client.Seller.Presenter;
import it.unisannio.catman.screens.seller.client.adapters.OfferMasterAdapter;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements Seller.View{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	

	@UiField Heading titleLabel;
	@UiField DataList<OfferProxy> dataList;
	
	@UiField Form form;
	@UiField TextBox searchTextBox;
	@UiField Button toggleButton;
	
	private QueryDataProvider<OfferProxy> dataProvider = new QueryDataProvider<OfferProxy>();
	private Presenter presenter;
	
	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new OfferMasterAdapter());
		dataList.setDataProvider(dataProvider);
		
	}

	@Override
	public void setSellerProxy(SellerProxy sellerProxy) {
		titleLabel.setText(sellerProxy.getName());
	}
	
	@Override
	public void setOfferProxyQuery(Query<OfferProxy> query) {
		dataProvider.setQuery(query);
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e){
		presenter.goToOfferScreen((OfferProxy) e.getSource());
	}
	
	@UiHandler("form")
	void handleSubmitEvent(Form.SubmitEvent e){
		presenter.executeSearch(searchTextBox.getText());
	}
	
	@UiHandler("toggleButton")
	void handleSearchToggleClick(ClickEvent e){
		if(toggleButton.isToggled())
			if(!searchTextBox.getText().trim().equals("")){
				presenter.executeSearch(null);
				searchTextBox.setText("");
			}
	}

}
