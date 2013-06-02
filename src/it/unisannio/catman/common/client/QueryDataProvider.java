package it.unisannio.catman.common.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.web.bindery.requestfactory.gwt.ui.client.EntityProxyKeyProvider;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class QueryDataProvider<E extends EntityProxy> extends AsyncDataProvider<E> implements SelectionModel<E> {
	private ArrayList<E> cache;
	private Map<EntityProxyId<E>, Boolean> selection;
	private Map<EntityProxyId<E>, E> mapping;
	private boolean selectedAll;
	private HandlerManager handlers;
	private int count = -1;

	public static enum SelectionState {ALL_SELECTED, SOME_SELECTED, NONE_SELECTED};

	private Query<E> query;

	public QueryDataProvider(Query<E> query) {
		super(new EntityProxyKeyProvider<E>());

		selectedAll = false;
		selection = new HashMap<EntityProxyId<E>, Boolean>();
		mapping = new HashMap<EntityProxyId<E>, E>();
		cache = new ArrayList<E>();

		handlers = new HandlerManager(this);

		this.query = query;
		
		queryForCount();
	}


	@Override
	protected void onRangeChanged(HasData<E> display) {
		// Get the new range.
		final Range range = display.getVisibleRange();
		int start = range.getStart();
		int length = range.getLength();

		final int loadStart = getLoadStart(start);
		final int loadLength = length - (Math.max(0, loadStart - start));

		if(loadLength > 0) {
			query.list(loadStart, loadLength).fire(new Receiver<List<E>>() {


				@Override
				public void onSuccess(List<E> response) {
					ensureSize(cache, loadStart + loadLength);
					for(int i = 0; i < response.size(); ++i) {
						E entity = response.get(i);
						cache.set(loadStart + i, entity);
						mapping.put((EntityProxyId<E>) entity.stableId(), entity);
					}

					updateRowData(loadStart, response);
					updateRowCount(loadStart + loadLength, false);

				}

			});
		}

	}

	private int getLoadStart(int start) {
		int cacheSize = cache.size();

		// we have nothing in cache after that point
		if(cacheSize < start)
			return start;

		// we may have "holes" in the cache, let's find the first element not yet loaded
		for(int i=start; i < cacheSize; ++i) {
			if(cache.get(i) == null)
				return i;
		}

		// everything is already loaded!
		return cacheSize;
	}


	public void reload() {
		cache.clear();
		selectedAll = false;
		selection.clear();
		mapping.clear();
		SelectionChangeEvent.fire(this);

		queryForCount();
		
		for(HasData<E> display : getDataDisplays()) {
			display.setVisibleRangeAndClearData(display.getVisibleRange(), true);
		}

	}

	public void setQuery(Query<E> q) {
		this.query = q;
		reload();
	}

	public void deleteSelected(final Receiver<Void> receiver) {

		List<E> list = new ArrayList<E>();
		for(Map.Entry<EntityProxyId<E>, Boolean> entry : selection.entrySet()) {
			if(entry.getValue() ^ selectedAll) // XOR
				list.add(mapping.get(entry.getKey()));
		}

		Request<Void> request = selectedAll ? query.deleteAll(list) : query.deleteSet(list);
		request.fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				reload();
				receiver.onSuccess(response);

			}

			@Override
			public void onFailure(ServerFailure error) {
				super.onFailure(error);
				receiver.onFailure(error);
			}
		});
	}

	@Override
	public boolean isSelected(E object) {
		EntityProxyId<E> key = (EntityProxyId<E>)object.stableId();
		if(selection.containsKey(key))
			return selection.get(key);
		
		return selectedAll;
	}

	@Override
	public HandlerRegistration addSelectionChangeHandler(Handler handler) {
		return handlers.addHandler(SelectionChangeEvent.getType(), handler);
	}

	@Override
	public void setSelected(E object, boolean selected) {
		selection.put((EntityProxyId<E>) object.stableId(), selected);
		if (selected && areAllSelected())
			setSelectedAll(true);
		if (!selected && areAllUnselected())
			setSelectedAll(false);
		else
			SelectionChangeEvent.fire(this);

	}

	public boolean isSelectedAll() {
		return selectedAll;
	}

	public void setSelectedAll(boolean selected) {
		selectedAll = selected;
		selection.clear();
		SelectionChangeEvent.fire(this);
	}


	@Override
	public void fireEvent(GwtEvent<?> event) {
		handlers.fireEvent(event);

	}

	private void ensureSize(ArrayList<?> list, int size) {
		list.ensureCapacity(size);
		while (list.size() < size) {
			list.add(null);
		}
	}

	private int getCount(){
		return count;
	}

	public void toggleSelection(E object) {
		setSelected(object, !isSelected(object));
	}
	
	public SelectionState getSelectionState(){
		if(selectedAll)
			if (selection.isEmpty())
				return SelectionState.ALL_SELECTED;
			else
				return SelectionState.SOME_SELECTED;
		if(selection.size() == 0 && !selectedAll)
			return SelectionState.NONE_SELECTED;
		for(Map.Entry<EntityProxyId<E>, Boolean> entry : selection.entrySet())
			if(entry.getValue()==true)
				return SelectionState.SOME_SELECTED;
		return SelectionState.NONE_SELECTED;
	}
	
	private void queryForCount(){
		query.count().fire(new Receiver<Integer>(){

			@Override
			public void onSuccess(Integer response) {
				count = response;
			}
			
		});
	}
	
	/**
	 * Returns <code>true</code> if all query results were selected manually
	 * @return <code>true</code> if all query results were selected manually
	 */
	private boolean areAllSelected(){
		if(selection.size() == getCount()){
			for(Map.Entry<EntityProxyId<E>, Boolean> entry : selection.entrySet())
				if(entry.getValue()==false)
					return false;
			return true;
		}
		return false;
	}
	private boolean areAllUnselected(){
		if(selection.size() == getCount()){
			for(Map.Entry<EntityProxyId<E>, Boolean> entry : selection.entrySet())
				if(entry.getValue()==true)
					return false;
			return true;
		}
		return false;
	}
}
