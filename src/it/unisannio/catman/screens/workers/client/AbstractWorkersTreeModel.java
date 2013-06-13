package it.unisannio.catman.screens.workers.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.CellAdapter;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.cell.MasterCell.Type;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.view.client.TreeViewModel;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public abstract class AbstractWorkersTreeModel implements TreeViewModel, HasClickHandlers{

	// XXX Sostituire scritta Empty quando l'albero è vuoto

	private MasterCell<WorkerProxy> cell;
	
	public AbstractWorkersTreeModel(CellAdapter<WorkerProxy> adapter) {
		cell = new MasterCell<WorkerProxy>();
		cell.setType(Type.STANDALONE);
		cell.setCellAdapter(adapter);
	}

	@Override
	abstract public <T> NodeInfo<?> getNodeInfo(T value);

	@Override
	public boolean isLeaf(Object value) {
		return value != null && value instanceof WorkerProxy;
	}

	protected class EntityNodeInfo<T extends EntityProxy> extends DefaultNodeInfo<T>{

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
