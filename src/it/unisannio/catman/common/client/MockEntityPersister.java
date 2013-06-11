package it.unisannio.catman.common.client;

import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.requestfactory.shared.Receiver;

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
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.JobBoardRequest;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationRequest;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerRequest;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;
import it.unisannio.catman.domain.workflow.client.CustomerRequest;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;

public class MockEntityPersister {
	static boolean done = false;

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
						for(int i = 0; i < 50; i++){
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
										});

									}
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
										});

									}
								});

							}
						});
					}
				}
			});


			dataStore.workers().count().fire(new Receiver<Integer>() {

				@Override
				public void onSuccess(Integer response) {
					if(response == 0){

						JobBoardRequest jobBoards = dataStore.jobBoards();
						final JobBoardProxy jobBoard1 = jobBoards.create(JobBoardProxy.class);
						jobBoard1.setName("Job Board 1");
						jobBoards.persist().using(jobBoard1);
						final JobBoardProxy jobBoard2 = jobBoards.create(JobBoardProxy.class);
						jobBoard2.setName("Job Board 2");
						jobBoards.persist().using(jobBoard2);
						jobBoards.fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {
								WorkerRequest workers = dataStore.workers();
								final WorkerProxy worker1 = workers.create(WorkerProxy.class);
								worker1.setName("Robertino");
								worker1.setCandidate(false);
								workers.persist().using(worker1);

								final WorkerProxy worker2 = workers.create(WorkerProxy.class);
								worker2.setName("Filippino");
								worker2.setCandidate(false);
								workers.persist().using(worker2);

								final WorkerProxy worker3 = workers.create(WorkerProxy.class);
								worker3.setName("Carlino");
								worker3.setCandidate(false);
								workers.persist().using(worker3);

								final WorkerProxy worker4 = workers.create(WorkerProxy.class);
								worker4.setName("Marino");
								worker4.setCandidate(false);
								workers.persist().using(worker4);

								final WorkerProxy worker5 = workers.create(WorkerProxy.class);
								worker5.setName("Gino");
								worker5.setCandidate(true);
								workers.persist().using(worker5);

								final WorkerProxy worker6 = workers.create(WorkerProxy.class);
								worker6.setName("Pino");
								worker6.setCandidate(true);
								workers.persist().using(worker6);

								final WorkerProxy worker7 = workers.create(WorkerProxy.class);
								worker7.setName("Mino");
								worker7.setCandidate(true);
								workers.persist().using(worker7);

								final WorkerProxy worker8 = workers.create(WorkerProxy.class);
								worker8.setName("Salvatore");
								worker8.setCandidate(false);
								workers.persist().using(worker8);

								final WorkerProxy worker9 = workers.create(WorkerProxy.class);
								worker9.setName("Francesco");
								worker9.setCandidate(false);
								workers.persist().using(worker9);

								final WorkerProxy worker10 = workers.create(WorkerProxy.class);
								worker10.setName("Matteo");
								worker10.setCandidate(false);
								workers.persist().using(worker10);

								workers.fire(new Receiver<Void>() {

									@Override
									public void onSuccess(Void response) {
										JobBoardRequest jobBoards = dataStore.jobBoards();
										jobBoards.addWorker(worker7).using(jobBoard1);
										jobBoards.addWorker(worker8).using(jobBoard1);
										jobBoards.addWorker(worker9).using(jobBoard1);
										jobBoards.addWorker(worker10).using(jobBoard2);

										jobBoards.fire(new Receiver<Void>() {

											@Override
											public void onSuccess(Void response) {
												
												QualificationRequest qualifications = dataStore.qualifications();
												final QualificationProxy qualification1 = qualifications.create(QualificationProxy.class);
												qualification1.setName("Cameriere");
												qualifications.persist().using(qualification1);
												
												final QualificationProxy qualification2 = qualifications.create(QualificationProxy.class);
												qualification2.setName("Cuoco");
												qualifications.persist().using(qualification2);
												
												final QualificationProxy qualification3 = qualifications.create(QualificationProxy.class);
												qualification3.setName("Sommelier");
												qualifications.persist().using(qualification3);
												
												final QualificationProxy qualification4 = qualifications.create(QualificationProxy.class);
												qualification4.setName("Animatore");
												qualifications.persist().using(qualification4);
												
												final QualificationProxy qualification5 = qualifications.create(QualificationProxy.class);
												qualification5.setName("Sguattero");
												qualifications.persist().using(qualification5);

												qualifications.fire(new Receiver<Void>() {

													@Override
													public void onSuccess(Void response) {
														WorkerRequest workers = dataStore.workers();
														workers.addQualification(qualification1).using(worker1);
														workers.addQualification(qualification2).using(worker7);
														workers.addQualification(qualification3).using(worker9);
														workers.addQualification(qualification4).using(worker10);
														workers.addQualification(qualification2).using(worker1);
														workers.addQualification(qualification4).using(worker5);
														workers.addQualification(qualification5).using(worker7);
														workers.addQualification(qualification1).using(worker4);
														workers.addQualification(qualification1).using(worker2);
														workers.addQualification(qualification4).using(worker8);
														workers.addQualification(qualification1).using(worker8);
														workers.addQualification(qualification5).using(worker2);
														workers.addQualification(qualification2).using(worker10);
														workers.addQualification(qualification1).using(worker6);
														workers.addQualification(qualification5).using(worker5);
														workers.addQualification(qualification3).using(worker6);
														
														workers.fire(new Receiver<Void>() {

															@Override
															public void onSuccess(Void response) {
																GWT.log("Workers & JobBoards & Qualifications persisted");
															}
														});
													}
												});
												
												
											}
										});
									}
								});
							}
						});
					}
				}
			});
			done = true;
			
		}
		
		/*
			final DataStore dataStore = App.getInstance().getDataStore();
			WorkerRequest workers = dataStore.workers();
			final WorkerProxy worker = workers.create(WorkerProxy.class);
			worker.setName("Danilo");
			workers.persist().using(worker).fire(new Receiver<Void>() {

				@Override
				public void onSuccess(Void response) {
					EntityProxyId<WorkerProxy> id = dataStore.getProxyId(dataStore.getHistoryToken(worker.stableId()));
					dataStore.workers().find(id).fire(new Receiver<WorkerProxy>() {

						@Override
						public void onSuccess(WorkerProxy response) {
							GWT.log("Trovato "+response.getName());
						}
						
						@Override
						public void onFailure(ServerFailure error) {
							GWT.log("Non trovato");
						}
					});
				}
			});
			*/
			
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
