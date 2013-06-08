package it.unisannio.catman.common.client;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.equipment.client.MaterielProxy;
import it.unisannio.catman.domain.equipment.client.MaterielRequest;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.StockRequest;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseRequest;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;
import it.unisannio.catman.domain.workflow.client.CustomerRequest;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;

public class MockEntityPersister {
	static  boolean done = false;

	public static void persist(){
		if(!done){

			final DataStore dataStore = App.getInstance().getDataStore();


			EventRequest events = dataStore.events();
			events.count().fire(new Receiver<Integer>() {

				@Override
				public void onSuccess(Integer response) {
					if(response == 0){
						EventRequest events = dataStore.events();
						EventProxy event = events.create(EventProxy.class);
						event.setTitle("Delirium Party");
						//	GWT.log("Event token: " + dataStore.getHistoryToken(event.stableId()));
						events.persist().using(event);
						EventProxy event2 = events.create(EventProxy.class);
						event2.setTitle("Erasmus Party");
						events.persist().using(event2);
						EventProxy event3 = events.create(EventProxy.class);
						event3.setTitle("Giovanni Di Muccio Party");
						events.persist().using(event3);


						events.fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {
								GWT.log("Events persisted");
							}
						});
					}
				}
			});

			CustomerRequest customers = dataStore.customers();

			customers.count().fire(new Receiver<Integer>() {

				@Override
				public void onSuccess(Integer response) {
					if(response==0){
						CustomerRequest customers = dataStore.customers();
						for(int i = 0; i < MALE_FIRST_NAMES.length; i++){
							CustomerProxy customer = customers.create(CustomerProxy.class);
							customer.setName(MALE_FIRST_NAMES[i]);
							customers.persist().using(customer);
						}
						customers.fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {
								GWT.log(MALE_FIRST_NAMES.length + " customers persisted!");
							}
							
							
						});
					}
				}
			});


			dataStore.materiels().count().fire(new Receiver<Integer>() {

				@Override
				public void onSuccess(Integer response) {
					if(true){ //if(response == 0){
						MaterielRequest materiels = dataStore.materiels();
						final MaterielProxy materiel = materiels.create(MaterielProxy.class);
						materiel.setName("Material X");
						materiel.setDescription("Kriptonite");
						materiels.persist().using(materiel).fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {
								StockRequest stocks = dataStore.stocks();
								final StockProxy stock = stocks.create(StockProxy.class);
								stock.setMateriel(materiel);
								stock.setQuantity(10);
								stocks.persist().using(stock).fire(new Receiver<Void>() {

									@Override
									public void onSuccess(Void response) {
										WarehouseRequest warehouses = dataStore.warehouses();
										final WarehouseProxy warehouse = warehouses.create(WarehouseProxy.class);
										warehouse.setName("Magazzino 1");
										warehouses.persist().using(warehouse).fire(new Receiver<Void>() {
											@Override
											public void onSuccess(Void response) {
												dataStore.warehouses().addSupply(stock).using(warehouse).fire(new Receiver<Void>() {
													@Override
													public void onSuccess(Void response) {
														GWT.log("Forse è andato tutto a buon fine");
													}
													
													public void onFailure(ServerFailure error) {
														GWT.log("Fail 3");
													};
												});
											}
											
											public void onFailure(ServerFailure error) {
												GWT.log("Fail 2");
											};
										});
									}
									
									public void onFailure(ServerFailure error) {
										GWT.log("Fail 1");
									};
								});
							}
							
							public void onFailure(ServerFailure error) {
								GWT.log("Fail 0");
							};
						});
					}
				}
			});
			

			done = true;
		}
	}

	private static final String[] MALE_FIRST_NAMES = {
		"James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph",
		"Thomas", "Christopher", "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven",
		"Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Matthew", "Gary", "Timothy",
		"Jose", "Larry", "Jeffrey", "Frank", "Scott", "Eric", "Stephen", "Andrew", "Raymond",
		"Gregory", "Joshua", "Jerry", "Dennis", "Walter", "Patrick", "Peter", "Harold", "Douglas",
		"Henry", "Carl", "Arthur", "Ryan", "Roger", "Joe", "Juan", "Jack", "Albert", "Jonathan",
		"Justin", "Terry", "Gerald", "Keith", "Samuel", "Willie", "Ralph", "Lawrence", "Nicholas",
		"Roy", "Benjamin", "Bruce", "Brandon", "Adam", "Harry", "Fred", "Wayne", "Billy", "Steve",
		"Louis", "Jeremy", "Aaron", "Randy", "Howard", "Eugene", "Carlos", "Russell", "Bobby",
		"Victor", "Martin", "Ernest", "Phillip", "Todd", "Jesse", "Craig", "Alan", "Shawn",
		"Clarence", "Sean", "Philip", "Chris", "Johnny", "Earl", "Jimmy", "Antonio", "Danny",
		"Bryan", "Tony", "Luis", "Mike", "Stanley", "Leonard", "Nathan", "Dale", "Manuel", "Rodney",
		"Curtis", "Norman", "Allen", "Marvin", "Vincent", "Glenn", "Jeffery", "Travis", "Jeff",
		"Chad", "Jacob", "Lee", "Melvin", "Alfred", "Kyle", "Francis", "Bradley", "Jesus", "Herbert",
		"Frederick", "Ray", "Joel", "Edwin", "Don", "Eddie", "Ricky", "Troy", "Randall", "Barry",
		"Alexander", "Bernard", "Mario", "Leroy", "Francisco", "Marcus", "Micheal", "Theodore",
		"Clifford", "Miguel", "Oscar", "Jay", "Jim", "Tom", "Calvin", "Alex", "Jon", "Ronnie",
		"Bill", "Lloyd", "Tommy", "Leon", "Derek", "Warren", "Darrell", "Jerome", "Floyd", "Leo",
		"Alvin", "Tim", "Wesley", "Gordon", "Dean", "Greg", "Jorge", "Dustin", "Pedro", "Derrick",
		"Dan", "Lewis", "Zachary", "Corey", "Herman", "Maurice", "Vernon", "Roberto", "Clyde",
		"Glen", "Hector", "Shane", "Ricardo", "Sam", "Rick", "Lester", "Brent", "Ramon", "Charlie",
		"Tyler", "Gilbert", "Gene"};
}
