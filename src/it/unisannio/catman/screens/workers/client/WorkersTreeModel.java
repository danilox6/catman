package it.unisannio.catman.screens.workers.client;

import java.util.List;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.workers.client.adapters.WorkerDetailAdapter;
import it.unisannio.catman.screens.workers.client.queries.WorkersByQualificationQuery;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;

public class WorkersTreeModel extends AbstractWorkersTreeModel{

	public WorkersTreeModel() {
		super(new WorkerDetailAdapter());
	}

	private WorkersSource workersSource;
	
	private ListDataProvider<QualificationProxy> qualifications = new ListDataProvider<QualificationProxy>();;
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(value == null)
			return new DefaultNodeInfo<QualificationProxy>(qualifications, new QualificationCell());
		if(value instanceof QualificationProxy){
			QueryDataProvider<WorkerProxy> dataProvider = new QueryDataProvider<WorkerProxy>();
			dataProvider.setQuery(new WorkersByQualificationQuery((QualificationProxy) value,workersSource));
			return new EntityNodeInfo<WorkerProxy>(dataProvider);
		}
		return null;
	}

	
	public void setQualifications(List<QualificationProxy> qualifications) {
		this.qualifications.setList(qualifications);
	}
	
	public void setWorkersSource(WorkersSource workersSource) {
		this.workersSource = workersSource;
	}
	
	class QualificationCell extends AbstractCell<QualificationProxy>{

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, QualificationProxy value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.getName());
		}

	}
	
}
