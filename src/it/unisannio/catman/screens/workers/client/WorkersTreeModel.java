package it.unisannio.catman.screens.workers.client;

import java.util.List;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.workers.client.adapters.WorkerDetailAdapter;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public class WorkersTreeModel implements TreeViewModel, HasClickHandlers{

	//FIXME Generalizzare
	//FIXME Sostituire scritta Empty quando l'albero è vuoto

	private WorkersSource workersSource;
	
	ListDataProvider<QualificationProxy> qualifications = new ListDataProvider<QualificationProxy>();;
	MasterCell<WorkerProxy> cell = new MasterCell<WorkerProxy>(new WorkerDetailAdapter());
	
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

	@Override
	public boolean isLeaf(Object value) {
		return value != null && value instanceof WorkerProxy;
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
	
	class EntityNodeInfo<T extends EntityProxy> extends DefaultNodeInfo<T>{
		
		private QueryDataProvider<T> dataProvider;

		@SuppressWarnings("unchecked")
		public EntityNodeInfo(QueryDataProvider<T> dataProvider) {
			super(dataProvider, (Cell<T>) cell);
			this.dataProvider = dataProvider;
		}
		
		@Override
		public void unsetDataDisplay() {
			super.unsetDataDisplay();
			dataProvider.reload();
		}
		
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		cell.fireEvent(event);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return cell.addClickHandler(handler);
	}
}
