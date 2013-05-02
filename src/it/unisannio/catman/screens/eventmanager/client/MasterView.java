package it.unisannio.catman.screens.eventmanager.client;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.domain.workflow.client.DossierProxy;
import it.unisannio.catman.screens.eventmanager.client.widget.DossierCellAdapter;
import it.unisannio.catman.screens.eventmanager.client.widget.MasterBottomBarWidget;
import it.unisannio.catman.screens.inbox.client.widget.SearchMasterHeadWidget;

import java.util.List;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class MasterView extends AbstractMasterView implements EventManager.Master.View {
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
		
		List<DossierProxy> values = dataProvider.getList();
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		
		cellList.setRowCount(values.size(), true);

		centerScrollPanel.add(cellList);
		
		southPanel.add(new MasterBottomBarWidget());
		
	}

	//FIXME Solo per i test
	private static class DossierProxyMock implements DossierProxy {
	
	}

}
