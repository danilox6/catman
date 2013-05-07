package it.unisannio.catman.screens.workers.client;

import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.domain.humanresources.client.PersonnelSource;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.worker.client.widget.WorkerCellAdapter;
import it.unisannio.catman.screens.workers.client.widget.WorkersListBox;

public abstract class PersonnelSourceMasterView extends AbstractMasterView implements ChangeHandler {

	protected WorkersListBox listBox; 
	protected ListDataProvider<WorkerProxy> dataProvider = new ListDataProvider<WorkerProxy>();
	protected PersonnelSource source;

	public PersonnelSourceMasterView(PersonnelSource source) {
		this.source = source;
		
		listBox = new WorkersListBox(source); 
		northPanel.add(listBox);

		CellList<WorkerProxy> cellList = new CellList<WorkerProxy>(new MasterCell<WorkerProxy>(new WorkerCellAdapter()));
		dataProvider.addDataDisplay(cellList);
		
		centerScrollPanel.add(cellList);
		
		listBox.addChangeHandler(this);
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), listBox); //Fa sì che venga sparato il ChangeEvent
		
	}

	@Override
	public void onChange(ChangeEvent event) {
		if(event.getSource().equals(listBox)){
			List<WorkerProxy> dataList = dataProvider.getList();
			
			//TODO Query selectedJob in Source (Workers, Job_Boards, Candidates)
			listBox.getSelectedJob();
			
			dataList.clear();
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			
		}
	}
	
	//FIXME Solo per i test
	class WorkerProxyMock implements WorkerProxy{}

}
