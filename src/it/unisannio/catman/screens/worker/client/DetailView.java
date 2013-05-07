package it.unisannio.catman.screens.worker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.common.client.widget.HeadWidget;
import it.unisannio.catman.domain.contacts.client.ContactProxy;
import it.unisannio.catman.domain.humanresources.client.JobProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.worker.client.widget.ContactCell;
import it.unisannio.catman.screens.worker.client.widget.ContractCellAdapter;
import it.unisannio.catman.screens.worker.client.widget.WorkerCellAdapter;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends AbstractDetailView implements Worker.Detail.View{
	interface Presenter{}
	
	public DetailView() {
		
		northPanel.add(new HeadWidget("John Phantom"));
		
		CellWidget<WorkerProxy> workerInfoCell = new CellWidget<WorkerProxy>(new MasterCell<WorkerProxy>(new WorkerCellAdapter()));
		workerInfoCell.setValue(new MockWorkerProxy());
		workerInfoCell.setWidth("100%");
		centerVerticalPanel.add(workerInfoCell);
		
		DetailSectionWidget contactInfoSection = new DetailSectionWidget("Contact Info");
		CellWidget<ContactProxy> contactInfoCell = new CellWidget<ContactProxy>(new ContactCell());
		contactInfoCell.setValue(new MockContactProxy());
		contactInfoCell.setWidth("100%");
		contactInfoSection.add(contactInfoCell);
		centerVerticalPanel.add(contactInfoSection);
		
		DetailSectionWidget contractsSection = new DetailSectionWidget("Contracts");
		CellList<JobProxy> cellList = new CellList<JobProxy>(new MasterCell<JobProxy>(new ContractCellAdapter()));
		ListDataProvider<JobProxy> dataProvider = new ListDataProvider<JobProxy>();
		dataProvider.addDataDisplay(cellList);
		
		List<JobProxy> contracts = dataProvider.getList();
		contracts.add(new MockContractProxy());
		contracts.add(new MockContractProxy());
		contracts.add(new MockContractProxy());
		contracts.add(new MockContractProxy());
		contracts.add(new MockContractProxy());
		contracts.add(new MockContractProxy());
		
		contractsSection.add(cellList);
		centerVerticalPanel.add(contractsSection);
	}

	//FIXME solo per testing
	class MockWorkerProxy implements WorkerProxy{}
	class MockContactProxy implements ContactProxy{}
	class MockContractProxy implements JobProxy{}

}
