package it.unisannio.catman.screens.workers.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.domain.humanresources.client.JobProxy;
import it.unisannio.catman.domain.humanresources.client.PersonnelSource;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.personnelpicker.client.DetailView.WorkerProxyMock;
import it.unisannio.catman.screens.personnelpicker.client.widget.PersonnelCellTreeAdapter;
import it.unisannio.catman.screens.workers.client.widget.WorkersListBox.JobProxyMock;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class PersonnelTreeModel implements TreeViewModel {
	
	@SuppressWarnings("unused")
	private PersonnelSource source;
	
	public PersonnelTreeModel(PersonnelSource source) {
		this.source = source;
		
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(value == null){
			ListDataProvider<JobProxy> dataProvider = new ListDataProvider<JobProxy>();
			List<JobProxy> jobs = dataProvider.getList();
			//TODO query jobs by source
			jobs.add(new JobProxyMock("Waiter"));
			jobs.add(new JobProxyMock("Chef"));
			jobs.add(new JobProxyMock("Servi della gleba")); 
			jobs.add(new JobProxyMock("Schiavi"));
			return new DefaultNodeInfo<JobProxy>(dataProvider, new JobCell() );
		}
		if(value instanceof JobProxy){
			ListDataProvider<WorkerProxy> dataProvider = new ListDataProvider<WorkerProxy>();
			List<WorkerProxy> dataList = dataProvider.getList();
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());

			return new DefaultNodeInfo<WorkerProxy>(dataProvider,new MasterCell<WorkerProxy>(new PersonnelCellTreeAdapter()));
		}
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		return value instanceof WorkerProxy;
	}
	
	class JobCell extends AbstractCell<JobProxy>{
		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, JobProxy value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.toString());
		}
	}

}
