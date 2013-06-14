package it.unisannio.catman.screens.eventmanager.client.queries;

import java.util.Date;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

import it.unisannio.catman.common.client.AbstractQuery;
import it.unisannio.catman.domain.workflow.client.EventProxy;

public class EventManagerQuery extends AbstractQuery<EventProxy>{

	String searchQuery;
	Date dateQuery;
	
	public EventManagerQuery(String searchQuery, Date dateQuery) {
		this.searchQuery = searchQuery;
		this.dateQuery = dateQuery;
	}

	@Override
	public Request<List<EventProxy>> list(int start, int length) {
		return getDataStore().events().listBy(searchQuery, dateQuery, start, length);
	}

	@Override
	public Request<Integer> count() {
		return getDataStore().events().countBy(searchQuery, dateQuery);
	}

}
