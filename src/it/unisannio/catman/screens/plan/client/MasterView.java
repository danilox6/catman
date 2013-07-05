package it.unisannio.catman.screens.plan.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.common.client.ui.MultiPanel;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.screens.plan.client.Plan.Presenter;
import it.unisannio.catman.screens.plan.client.Plan.View;
import it.unisannio.catman.screens.plan.client.adapters.RequirementMasterAdapter;
import it.unisannio.catman.screens.plan.client.queries.PositionQuery;
import it.unisannio.catman.screens.plan.client.queries.ProcurementQuery;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements ChangeHandler, View {

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}

	@UiField ListBox listBox;
	
	//@UiField SelectAllButton selectButton;
	
	@UiField MultiPanel multiPanel;

	private String[] listItems = {"Ruoli", "Materiali"};
	

	@UiField
	DataList<ProcurementProxy> procurementsList;
	
	@UiField
	DataList<PositionProxy> positionsList;
	
	//@UiField Button delete;
	
	private Presenter presenter;
	

	private QueryDataProvider<ProcurementProxy> procurementsProvider;
	private QueryDataProvider<PositionProxy> positionsProvider;
	
	
	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		procurementsList.setDataProvider(procurementsProvider = new QueryDataProvider<ProcurementProxy>());
		positionsList.setDataProvider(positionsProvider = new QueryDataProvider<PositionProxy>());
		
		procurementsList.setCellAdapter(new RequirementMasterAdapter());
		positionsList.setCellAdapter(new RequirementMasterAdapter());

		listBox.addChangeHandler(this);
		
		for(String item: listItems)
			listBox.addItem(item);
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), listBox); //Fa sì che venga sparato il ChangeEvent

	}

	@Override
	public void onChange(ChangeEvent event) {
		multiPanel.setIndex(listBox.getSelectedIndex());
		//selectButton.setDataProvider(listBox.getSelectedIndex() == 0 ? positionsProvider : procurementsProvider);
	}

	@UiHandler("procurementsList")
	void handleProcurementCell(ClickEvent event) {
		ProcurementProxy procurement = (ProcurementProxy) event.getSource();
		presenter.goTo(procurement);
	}
	
	@UiHandler("positionsList")
	void handlePositionCell(ClickEvent event) {
		PositionProxy position = (PositionProxy) event.getSource();
		presenter.goTo(position);
	}
	/*
	@UiHandler("delete")
	void handleDelete(ClickEvent event) {
		if(listBox.getSelectedIndex() == 0)
			positionsProvider.deleteSelected();
		else
			procurementsProvider.deleteSelected();
		
	}*/
 
	@Override
	public void setPresenter(Presenter p) {
		this.presenter = p;
		
	}

	@Override
	public void setProcurementQuery(ProcurementQuery pq) {
		procurementsProvider.setQuery(pq);
		
	}

	@Override
	public void setPositionQuery(PositionQuery pq) {
		positionsProvider.setQuery(pq);
		
	}

	@Override
	public void refreshProcurements() {
		procurementsProvider.reload();
		
	}

	@Override
	public void refreshPositions() {
		positionsProvider.reload();		
	}

}
