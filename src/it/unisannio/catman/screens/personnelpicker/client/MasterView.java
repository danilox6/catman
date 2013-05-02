package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.HeadWidget;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.personnelpicker.client.DetailView.WorkerProxyMock;
import it.unisannio.catman.screens.personnelpicker.client.widget.EmployeCellAdapter;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView extends AbstractMasterView implements PersonnelPicker.Master.View {
	interface Presenter {}

	public MasterView() {
		
		northPanel.add(new HeadWidget("Ruolo Y 3/5"));
		
		//FIXME Proxy adatto
		CellList<WorkerProxy> cellList = new CellList<WorkerProxy>(new MasterCell<WorkerProxy>(new EmployeCellAdapter()));
		
		ListDataProvider<WorkerProxy> dataProvider = new ListDataProvider<WorkerProxy>();
		dataProvider.addDataDisplay(cellList);	
		List<WorkerProxy> dataList = dataProvider.getList();
		dataList.add(new WorkerProxyMock());
		dataList.add(new WorkerProxyMock());
		dataList.add(new WorkerProxyMock());

		centerScrollPanel.add(cellList);
		
		
	}
	


}
