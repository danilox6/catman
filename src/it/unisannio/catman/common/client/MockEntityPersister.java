package it.unisannio.catman.common.client;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;

public class MockEntityPersister {
	private static boolean done = false;
	
	public static void persist(){
		if(!done){
			
			DataStore dataStore = App.getInstance().getDataStore();
	
			EventRequest events = dataStore.events();
			EventProxy event = events.create(EventProxy.class);
			event.setTitle("Delirium Party");
			GWT.log(event.getId() + " - " + dataStore.getHistoryToken(event.stableId()));
			events.persist().using(event).fire(new Receiver<Void>() {
	
				@Override
				public void onSuccess(Void response) {
					GWT.log("Persisted");
				}
			});
			done = true;
		}
	}
}
