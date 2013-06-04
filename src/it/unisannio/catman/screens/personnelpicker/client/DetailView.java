package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.personnelpicker.client.adapters.SelectedWorkerAdapter;
import it.unisannio.catman.screens.personnelpicker.client.widgets.SelectedWorkersCellList;
import it.unisannio.catman.screens.personnelpicker.client.widgets.WorkersTreeModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite {

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	@UiField(provided = true) SelectedWorkersCellList selectedCellList;
	@UiField(provided = true) CellTree cellTree;
	
	public DetailView() {
		selectedCellList = new SelectedWorkersCellList(new MasterCell<WorkerProxy>(new SelectedWorkerAdapter()));
		cellTree = new CellTree(new WorkersTreeModel(), null);
		cellTree.setAnimationEnabled(true);
		initWidget(uiBinder.createAndBindUi(this));
	}

}
