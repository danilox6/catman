package it.unisannio.catman.common.client;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;
import it.unisannio.catman.domain.workflow.client.CustomerRequest;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;

public class MockEntityPersister {

	public static void persist(){

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
		
		
		CustomerRequest customers = dataStore.customers();
		CustomerProxy customer1 = customers.create(CustomerProxy.class);
		customer1.setName("Marcello");
		customers.persist().using(customer1);
		
		CustomerProxy customer2 = customers.create(CustomerProxy.class);
		customer2.setName("Pio");
		customers.persist().using(customer2);
		
		CustomerProxy customer3 = customers.create(CustomerProxy.class);
		customer3.setName("Filippo");
		customers.persist().using(customer3);
		
		CustomerProxy customer4 = customers.create(CustomerProxy.class);
		customer4.setName("Roberto");
		customers.persist().using(customer4);
		
		CustomerProxy customer5 = customers.create(CustomerProxy.class);
		customer5.setName("Massimo");
		customers.persist().using(customer5);
		
		CustomerProxy customer6 = customers.create(CustomerProxy.class);
		customer6.setName("Andrea");
		customers.persist().using(customer6);
		
		CustomerProxy customer7 = customers.create(CustomerProxy.class);
		customer7.setName("Marco");
		customers.persist().using(customer7);
		
		CustomerProxy customer8 = customers.create(CustomerProxy.class);
		customer8.setName("Cosimo");
		customers.persist().using(customer8);
		
		CustomerProxy customer9 = customers.create(CustomerProxy.class);
		customer9.setName("Gino");
		customers.persist().using(customer9);
		
		CustomerProxy customer10 = customers.create(CustomerProxy.class);
		customer10.setName("Damiano");
		customers.persist().using(customer10);
		
		customers.fire(new Receiver<Void>() {

			@Override
			public void onSuccess(Void response) {
				GWT.log("Created!");
			}
		});
	}
}
