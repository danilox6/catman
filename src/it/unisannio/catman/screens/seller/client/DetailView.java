package it.unisannio.catman.screens.seller.client;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.SellerProxy;
import it.unisannio.catman.screens.seller.client.adapters.OfferDetailAdapter;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements Seller.Detail.View{

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	//FIXME il titolo appare più in basso dopo aver aggiunto la barra di ricerca
	
	@UiField Heading titleLabel;
	@UiField DataList<OfferProxy> dataList;
	
	private QueryDataProvider<OfferProxy> dataProvider = new QueryDataProvider<OfferProxy>();
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new OfferDetailAdapter());
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

}
