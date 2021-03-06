package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.cell.MasterCell.Type;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Presenter;
import it.unisannio.catman.screens.personnelpicker.client.adapters.SelectedContractsAdapter;
import it.unisannio.catman.screens.personnelpicker.client.widgets.SelectedWorkersCellList;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends Composite implements PersonnelPicker.Detail.View, ClickHandler {

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	@UiField(provided = true) SelectedWorkersCellList selectedCellList;
	@UiField(provided = true) CellTree cellTree;
	
	@UiField Heading titleLabel;
	
	private Presenter presenter;
	
	private WorkersTreeModel treeModel = new WorkersTreeModel();
	
	ListDataProvider<ContractProxy> positionFillers = new ListDataProvider<ContractProxy>();
	
	public DetailView() {
		MasterCell<ContractProxy> cell = new MasterCell<ContractProxy>(new SelectedContractsAdapter());
		cell.setType(Type.STANDALONE);
		selectedCellList = new SelectedWorkersCellList(cell);
		cellTree = new CellTree(treeModel, null);
		cellTree.setAnimationEnabled(true);
		initWidget(uiBinder.createAndBindUi(this));
		
		positionFillers.addDataDisplay(selectedCellList);
		
		treeModel.addClickHandler(this);
	}

	@Override
	public void setPositionProxy(PositionProxy positionProxy) {
		titleLabel.setText(positionProxy.getDescription()+ " " + positionProxy.getQuantityFilled()+"/"+positionProxy.getQuantity());
		
		treeModel.setPositionProxy(positionProxy);
	}

	@Override
	public void setSelectedContracts(List<ContractProxy> fillers) {
		
		List<ContractProxy> contracts = this.positionFillers.getList();
		contracts.clear();
		contracts.addAll(fillers);
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setWorkersSources(List<WorkersSource> workersSources) {
		treeModel.setWorkersSources(workersSources);
	}
	
	@Override
	public void onClick(ClickEvent event) {
		presenter.goToWorkerScreen((WorkerProxy) event.getSource());
	}

}
