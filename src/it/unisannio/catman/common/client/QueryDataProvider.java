package it.unisannio.catman.common.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
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
	
	private Query<E> query;
	
	public QueryDataProvider(Query<E> query) {
		super(new EntityProxyKeyProvider<E>());
		
		selectedAll = false;
		selection = new HashMap<EntityProxyId<E>, Boolean>();
		mapping = new HashMap<EntityProxyId<E>, E>();
		cache = new ArrayList<E>();
		
		handlers = new HandlerManager(this);
		
		this.query = query;
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
	    // Prevent excessive copying while we're adding
	    list.ensureCapacity(size);
	    while (list.size() < size) {
	        list.add(null);
	    }
	}
}
