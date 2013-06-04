package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.documents.client.DocumentProxy;
import it.unisannio.catman.screens.event.client.adapters.DocumentMasterAdapter;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite {

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField Heading titleLabel;
	@UiField DataList<DocumentProxy> dataList;
	@UiField Button addButton;
	
	private QueryDataProvider<DocumentProxy> dataProvider;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new DocumentMasterAdapter());
		
		dataProvider = new QueryDataProvider<DocumentProxy>();
		dataList.setDataProvider(dataProvider);
	}
	
	@UiHandler("addButton")
	void handleAddButonClick(ClickEvent e){
		
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e) {
		
	}

}
