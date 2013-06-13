package it.unisannio.catman.screens.event.client.queries;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.domain.workflow.client.EventDocumentProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;

public class EventDocumentQuery extends AbstractQuery<EventDocumentProxy> {
	private static final DataStore store = App.getInstance().getDataStore();
	
	private EventProxy event;
	
	public EventDocumentQuery(EventProxy event) {
		this.event = event;
	}

	@Override
	public Request<List<EventDocumentProxy>> list(int start, int length) {
		return store.eventDocuments().listByEvent(event, start, length);
	}

	@Override
	public Request<Integer> count() {
		return store.eventDocuments().countByEvent(event);
	}
}
