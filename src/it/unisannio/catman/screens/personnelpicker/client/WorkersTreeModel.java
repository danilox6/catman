package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.workers.client.AbstractWorkersTreeModel;
import it.unisannio.catman.screens.workers.client.adapters.WorkerDetailAdapter;
import it.unisannio.catman.screens.workers.client.queries.WorkersByQualificationQuery;

public class WorkersTreeModel extends AbstractWorkersTreeModel{

	public WorkersTreeModel() {
		super(new WorkerDetailAdapter());
	}

	private PositionProxy positionProxy;
	private ListDataProvider<WorkersSource> workersSources = new ListDataProvider<WorkersSource>();
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(value == null)
			return new DefaultNodeInfo<WorkersSource>(workersSources, new WorkersSourceCell());
		if(value instanceof WorkersSource){
			QueryDataProvider<WorkerProxy> dataProvider = new QueryDataProvider<WorkerProxy>();
			dataProvider.setQuery(new WorkersByQualificationQuery(positionProxy.getQualification(),(WorkersSource) value));
			return new EntityNodeInfo<WorkerProxy>(dataProvider);
		}
		return null;
	}

	public void setPositionProxy(PositionProxy positionProxy) {
		this.positionProxy = positionProxy;
	}

	public void setWorkersSources(List<WorkersSource> workersSources) {
		this.workersSources.setList(workersSources);
	}
	
	class WorkersSourceCell extends AbstractCell<WorkersSource>{

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, WorkersSource value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.getName() + " ("+value.getCount()+")"); //FIXME Aggiungere iconcina?
		}
		
	}
}
