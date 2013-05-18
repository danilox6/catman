package it.unisannio.catman.screens.stock.client;

import java.util.List;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

import it.unisannio.catman.common.client.DataProviderSelectionSyncronizer;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.SearchTitleBarWidger;
import it.unisannio.catman.common.client.widget.SelectAllHandler;
import it.unisannio.catman.domain.equipment.client.MaterialProxy;
import it.unisannio.catman.screens.stock.client.widget.MasterBottomBarWidget;
import it.unisannio.catman.screens.stock.client.widget.MaterialMasterCellAdapter;

public class MasterView extends AbstractMasterView implements Stock.Master.View {
	interface Presenter{}
	
	public MasterView() {
		northPanel.add(new SearchTitleBarWidger("Title") {
			
			@Override
			public void handleResearch(String query) {
				
			}
		});
		
		MaterialMasterCellAdapter cellAdapter = new MaterialMasterCellAdapter();
		CellList<MaterialProxy> cellList = new CellList<MaterialProxy>(new MasterCell<MaterialProxy>(cellAdapter));
		MultiSelectionModel<MaterialProxy> selectionModel = new MultiSelectionModel<MaterialProxy>();
		cellList.setSelectionModel(selectionModel, DefaultSelectionEventManager.<MaterialProxy>createCheckboxManager());
		cellAdapter.setSelectionModel(selectionModel);
		
		ListDataProvider<MaterialProxy> dataProvider = new ListDataProvider<MaterialProxy>();
		dataProvider.addDataDisplay(cellList);
		
		List<MaterialProxy> dataList = dataProvider.getList();
		dataList.add(new MockMaterialProxy());
		dataList.add(new MockMaterialProxy());
		dataList.add(new MockMaterialProxy());
		dataList.add(new MockMaterialProxy());
		
		DataProviderSelectionSyncronizer.<MaterialProxy>sync(selectionModel, dataProvider);
		
		centerScrollPanel.add(cellList);
		
		southPanel.add(new MasterBottomBarWidget<MaterialProxy>(new SelectAllHandler<MaterialProxy>(selectionModel, dataProvider)));
		
	}
	
	class MockMaterialProxy implements MaterialProxy{}
	
}
