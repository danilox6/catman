package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.personnelpicker.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.personnelpicker.client.widget.SelectedWorkerCellAdapter;
import it.unisannio.catman.screens.personnelpicker.client.widget.SelectedWorkerCellList;

import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends AbstractDetailView implements PersonnelPicker.Detail.View {

	public DetailView() {

		northPanel.add(new DetailHeadWidget("Ruolo Y 3/5"));

		DetailSectionWidget selectedSection = new DetailSectionWidget("Selected");
		
		SelectedWorkerCellList selectedWorkerCellList = new SelectedWorkerCellList(new MasterCell<WorkerProxy>(new SelectedWorkerCellAdapter()));
		ListDataProvider<WorkerProxy> selectedWorkerProvider = new ListDataProvider<WorkerProxy>();
		selectedWorkerProvider.addDataDisplay(selectedWorkerCellList);
		
		List<WorkerProxy> dataList = selectedWorkerProvider.getList();
		dataList.add(new WorkerProxyMock());
		dataList.add(new WorkerProxyMock());
		dataList.add(new WorkerProxyMock());
		dataList.add(new WorkerProxyMock());
		dataList.add(new WorkerProxyMock());
		
		
		
		selectedSection.add(selectedWorkerCellList);
		centerVerticalPanel.add(selectedSection);

		DetailSectionWidget availableSection = new DetailSectionWidget("Available");
		CellTree cellTree = new CellTree(new WorkerViewModel(), null);
		cellTree.setAnimationEnabled(true);
		availableSection.add(cellTree);
		centerVerticalPanel.add(availableSection);
	}

	//FIXME solo per testing
	static class WorkerProxyMock implements WorkerProxy{

	}
	
}
