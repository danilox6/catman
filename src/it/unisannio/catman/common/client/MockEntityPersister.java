package it.unisannio.catman.common.client;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.domain.equipment.client.MaterielProxy;
import it.unisannio.catman.domain.equipment.client.MaterielRequest;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.OfferRequest;
import it.unisannio.catman.domain.equipment.client.SellerProxy;
import it.unisannio.catman.domain.equipment.client.SellerRequest;
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
					if(response == 0){
						MaterielRequest materiels = dataStore.materiels();
						final MaterielProxy materiel1 = materiels.create(MaterielProxy.class);
						materiel1.setName("Material X");
						materiel1.setDescription("Kriptonite");
						materiels.persist().using(materiel1);

						final MaterielProxy materiel2 = materiels.create(MaterielProxy.class);
						materiel2.setName("Sedia");
						materiel2.setDescription("Quella roba su cui la gente si siede");
						materiels.persist().using(materiel2);

						final MaterielProxy materiel3 = materiels.create(MaterielProxy.class);
						materiel3.setName("Forchetta");
						materiel3.setDescription("Quando capirò a cosa serve, aggiornerò questa descrizione");
						materiels.persist().using(materiel3);

						materiels.fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {

								WarehouseRequest warehouses = dataStore.warehouses();
								final WarehouseProxy warehouse = warehouses.create(WarehouseProxy.class); //IjEi@0@NiB6YzH5ss6oGAvNWB3UvR6z1vY=
								warehouse.setName("Magazzino 1");

								warehouses.persist().using(warehouse).fire(new Receiver<Void>()  {

									@Override
									public void onSuccess(Void response) {

										StockRequest stocks = dataStore.stocks();
										StockProxy stock1 = stocks.create(StockProxy.class);
										stock1.setMateriel(materiel3);
										stock1.setSupplier(warehouse);
										stock1.setQuantity(120);
										stocks.persist().using(stock1);

										StockProxy stock2 = stocks.create(StockProxy.class);
										stock2.setMateriel(materiel1);
										stock2.setSupplier(warehouse);
										stock2.setQuantity(10);
										stocks.persist().using(stock2);

										stocks.fire(new Receiver<Void>() {
											@Override
											public void onSuccess(Void response) {
												GWT.log("Material -> Warehouse -> Stock persited");
											}

											public void onFailure(ServerFailure error) {
												GWT.log("Fail 2 - Stock");
											};
										});

									}

									public void onFailure(ServerFailure error) {
										GWT.log("Fail 1 - Warehouse");
									};
								});

								SellerRequest sellers = dataStore.sellers();
								final SellerProxy seller = sellers.create(SellerProxy.class);
								seller.setName("Gino il venditore");

								sellers.persist().using(seller).fire(new Receiver<Void>() {

									@Override
									public void onSuccess(Void response) {
										OfferRequest offers = dataStore.offers();
										OfferProxy offer1 = offers.create(OfferProxy.class);
										offer1.setMateriel(materiel1);
										offer1.setSupplier(seller);
										offer1.setQuantity(30);
										offer1.setPrice(9999.99F);
										offers.persist().using(offer1);
										
										OfferProxy offer2 = offers.create(OfferProxy.class);
										offer2.setMateriel(materiel2);
										offer2.setSupplier(seller);
										offer2.setPrice(12);
										offer2.setQuantity(300);
										offers.persist().using(offer2);
										
										offers.fire(new Receiver<Void>() {

											@Override
											public void onSuccess(Void response) {
												GWT.log("Material -> Seller -> Offer persited");
											}

											public void onFailure(ServerFailure error) {
												GWT.log("Fail 2 - Offer");
											};
										});
										
									}
									
									public void onFailure(ServerFailure error) {
										super.onFailure(error);
										GWT.log("Fail 1 - Seller");
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
