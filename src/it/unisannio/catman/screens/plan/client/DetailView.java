package it.unisannio.catman.screens.plan.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.screens.plan.client.Plan.Presenter;
import it.unisannio.catman.screens.plan.client.Plan.View;
import it.unisannio.catman.screens.plan.client.adapters.PositionCellAdapter;
import it.unisannio.catman.screens.plan.client.adapters.ProcurementCellAdapter;
import it.unisannio.catman.screens.plan.client.queries.PositionQuery;
import it.unisannio.catman.screens.plan.client.queries.ProcurementQuery;

import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements View {

	private static DetaillViewUiBinder uiBinder = GWT.create(DetaillViewUiBinder.class);

	interface DetaillViewUiBinder extends UiBinder<Widget, DetailView> {
	}
	
	private Presenter presenter;

	@UiField
	DataList<ProcurementProxy> procurementsList;
	
	@UiField
	DataList<PositionProxy> positionsList;
	
	@UiField
	NavLink addProcurement;
	
	@UiField
	NavLink addPosition;
	
	private QueryDataProvider<ProcurementProxy> procurementsProvider;
	private QueryDataProvider<PositionProxy> positionsProvider;
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
		procurementsList.setDataProvider(procurementsProvider = new QueryDataProvider<ProcurementProxy>());
		positionsList.setDataProvider(positionsProvider = new QueryDataProvider<PositionProxy>());
		
		procurementsList.setCellAdapter(new ProcurementCellAdapter());
		positionsList.setCellAdapter(new PositionCellAdapter());
	}

	@UiHandler("addProcurement")
	void handleAddProcurement(ClickEvent event) {
		presenter.addProcurement();
	}
	
	@UiHandler("addPosition")
	void handleAddPosition(ClickEvent event) {
		presenter.addPosition();
	}
	
	@UiHandler("procurementsList")
	void handleProcurementCell(ClickEvent event) {
		presenter.goTo((ProcurementProxy) event.getSource());
	}
	
	@UiHandler("positionsList")
	void handlePositionCell(ClickEvent event) {
		presenter.goTo((PositionProxy) event.getSource());
	}
 
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
