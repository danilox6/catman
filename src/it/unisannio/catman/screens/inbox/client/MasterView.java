package it.unisannio.catman.screens.inbox.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.SelectAllHandler;
import it.unisannio.catman.domain.workflow.client.DossierProxy;
import it.unisannio.catman.screens.inbox.client.widget.SelectionHandlerBottomBar;
import it.unisannio.catman.screens.inbox.client.widget.DossierCellAdapter;
import it.unisannio.catman.screens.inbox.client.widget.SearchMasterHeadWidget;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class MasterView extends AbstractMasterView implements Inbox.Master.View {
	interface Presenter {}

	public MasterView() {
		
		northPanel.add(new SearchMasterHeadWidget());
		
		DossierCellAdapter cellAdapter = new DossierCellAdapter();
		CellList<DossierProxy> cellList = new CellList<DossierProxy>(new MasterCell<DossierProxy>(cellAdapter));

		MultiSelectionModel<DossierProxy> selectionModel = new MultiSelectionModel<DossierProxy>();
		cellList.setSelectionModel(selectionModel, DefaultSelectionEventManager.<DossierProxy>createCheckboxManager());
		
		//FIXME Uno dei due metodi è superfluo, scegliere quello più bello
		cellAdapter.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(cellAdapter);
		
		ListDataProvider<DossierProxy> dataProvider = new ListDataProvider<DossierProxy>();
		dataProvider.addDataDisplay(cellList);
		//dataProvider.r
		
		List<DossierProxy> values = dataProvider.getList(); //Da javadoc "Get the list that backs this model. Changes to the list will be reflected in the model."
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		
		cellList.setRowCount(values.size(), true);

		centerScrollPanel.add(cellList);
		
		SelectionHandlerBottomBar bottomBar = new SelectionHandlerBottomBar(new SelectAllHandler<DossierProxy>(selectionModel, dataProvider));
		southPanel.add(bottomBar);
	}
	

	//FIXME Solo per i test
	private static class DossierProxyMock implements DossierProxy {
	
	}

}
