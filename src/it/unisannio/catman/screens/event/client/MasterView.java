package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.workflow.client.EventDocumentProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.event.client.Event.Presenter;
import it.unisannio.catman.screens.event.client.Event.View;
import it.unisannio.catman.screens.event.client.adapters.DocumentCellAdapter;
import it.unisannio.catman.screens.event.client.queries.EventDocumentQuery;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class MasterView extends Composite implements View {

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField Heading titleLabel;
	@UiField DataList<EventDocumentProxy> dataList;
	@UiField Button addButton;
	
	private Presenter presenter;
	
	private QueryDataProvider<EventDocumentProxy> dataProvider;
	
//	private EventProxy eventProxy;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new DocumentCellAdapter());
		
		dataProvider = new QueryDataProvider<EventDocumentProxy>();
		dataList.setDataProvider(dataProvider);
		
		addButton.setEnabled(false);
	}
	
	public void setPresenter(Presenter p) {
		this.presenter = p;
	}
	
	@UiHandler("addButton")
	void handleAddButtonClick(ClickEvent e){
		presenter.addPlan();
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e) {
		presenter.goToPlan((PlanProxy) e.getSource());
	}

	@Override
	public void refresh() {
		dataProvider.reload();
		
	}

	@Override
	public void setEventProxy(EventProxy eventProxy) {

//		this.eventProxy = eventProxy; 
		titleLabel.setText(eventProxy.getTitle());
	}

	@Override
	public void setDocumentQuery(EventDocumentQuery edq) {
		dataProvider.setQuery(edq);
		
		edq.count().fire(new Receiver<Integer>() {
			@Override
			public void onSuccess(Integer response) {
				if(response==0)
					addButton.setEnabled(true); //FIXME meglio mettere un vincolo
			}
		});
	}

}
