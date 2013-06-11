package it.unisannio.catman.screens.personnelpicker.client.widgets;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.workers.client.adapters.WorkerDetailAdapter;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class WorkersTreeModel implements TreeViewModel{

	//TODO effettuare le query, creare un NodeInfo che supporti la paginazione e che funzioni similmente al QueryDataProvider

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(value == null){
			ListDataProvider<QualificationProxy> dataProvider = new ListDataProvider<QualificationProxy>();
			return new DefaultNodeInfo<QualificationProxy>(dataProvider, new QualificationCell());
		}
		if(value instanceof WorkerProxy){
			ListDataProvider<WorkerProxy> dataProvider = new ListDataProvider<WorkerProxy>();
			return new DefaultNodeInfo<WorkerProxy>(dataProvider, new MasterCell<WorkerProxy>(new WorkerDetailAdapter()));
		}
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		return value != null && value instanceof WorkerProxy;
	}


	class QualificationCell extends AbstractCell<QualificationProxy>{

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, QualificationProxy value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.getName());
		}

	}
}
