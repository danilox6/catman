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
import it.unisannio.catman.domain.humanresources.client.ContractRequest;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.JobBoardRequest;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.humanresources.client.PieceworkRequest;
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
	private static EventProxy event1;
	private static final DateFormat DATE_F = new DateFormat();

	public static void persist(){
		if(!done){

			final DataStore dataStore = App.getInstance().getDataStore();
			
			EventRequest events = dataStore.events();
			events.count().fire(new Receiver<Integer>() {

				@Override
				public void onSuccess(Integer response) {
					if(response == 0){
						EventRequest events = dataStore.events();
						event1 = events.create(EventProxy.class);
						event1.setTitle("Ricevimento aziendale");
						event1.setStartDate(DATE_F.parse("11/08/2013"));
						event1.setEndDate(DATE_F.parse("12/08/2013"));
						events.persist().using(event1);
						EventProxy event2 = events.create(EventProxy.class);
						event2.setTitle("Banchetto");
						event2.setStartDate(DATE_F.parse("01/08/2013"));
						event2.setEndDate(DATE_F.parse("03/08/2013"));
						events.persist().using(event2);
						EventProxy event3 = events.create(EventProxy.class);
						event3.setTitle("Brunch all'aperto");
						event3.setStartDate(DATE_F.parse("20/09/2013"));
						event3.setEndDate(DATE_F.parse("25/09/2013"));
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
						for(int i = 0; i < 5; i++){
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
						materiel1.setName("Gazebo");
						materiel1.setDescription("Gazebo larghezza 10x5m, colore verde");
						materiels.persist().using(materiel1);

						final MaterielProxy materiel2 = materiels.create(MaterielProxy.class);
						materiel2.setName("Sedia");
						materiel2.setDescription("Sedia pieghevole da giardino");
						materiels.persist().using(materiel2);

						final MaterielProxy materiel3 = materiels.create(MaterielProxy.class);
						materiel3.setName("Set posate");
						materiel3.setDescription("Forchetta + coltello + tovagliolo, in bustina monouso");
						materiels.persist().using(materiel3);
						
						final MaterielProxy materiel4 = materiels.create(MaterielProxy.class);
						materiel4.setName("Tavolo");
						materiel4.setDescription("Tavolo 1x2m, legno");
						materiels.persist().using(materiel4);
						
						final MaterielProxy materiel5 = materiels.create(MaterielProxy.class);
						materiel5.setName("Tovaglia");
						materiel5.setDescription("Tovaglia ricamata, bianca, 2x3m");
						materiels.persist().using(materiel5);
						
						final MaterielProxy materiel6 = materiels.create(MaterielProxy.class);
						materiel6.setName("Set calici");
						materiel6.setDescription("Set di calici da 6");
						materiels.persist().using(materiel6);
						
						final MaterielProxy materiel7 = materiels.create(MaterielProxy.class);
						materiel7.setName("Set piatti");
						materiel7.setDescription("Set da 10 piatti da portata in porcellana decorata");
						materiels.persist().using(materiel7);
						
						final MaterielProxy materiel8 = materiels.create(MaterielProxy.class);
						materiel8.setName("Tovaglioli");
						materiel8.setDescription("Pacco da 500 tovaglioli di carta");
						materiels.persist().using(materiel8);
						
						final MaterielProxy materiel9 = materiels.create(MaterielProxy.class);
						materiel9.setName("Bicchieri di carta");
						materiel9.setDescription("Confezione da 50 bicchieri monouso");
						materiels.persist().using(materiel9);

						materiels.fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {

								WarehouseRequest warehouses = dataStore.warehouses();
								final WarehouseProxy warehouse = warehouses.create(WarehouseProxy.class); 
								warehouse.setName("Magazzino Roma");
								
								final WarehouseProxy warehouse2 = warehouses.create(WarehouseProxy.class); 
								warehouse2.setName("Magazzino Milano");
								warehouses.persist().using(warehouse2);
								warehouses.persist().using(warehouse).fire(new Receiver<Void>()  {

									@Override
									public void onSuccess(Void response) {

										StockRequest stocks = dataStore.stocks();
										StockProxy stock1 = stocks.create(StockProxy.class);
										stock1.setMateriel(materiel3);
										stock1.setSupplier(warehouse);
										stock1.setQuantity(20);
										stocks.persist().using(stock1);

										StockProxy stock2 = stocks.create(StockProxy.class);
										stock2.setMateriel(materiel2);
										stock2.setSupplier(warehouse2);
										stock2.setQuantity(10);
										stocks.persist().using(stock2);
										
										StockProxy stock3 = stocks.create(StockProxy.class);
										stock3.setMateriel(materiel3);
										stock3.setSupplier(warehouse2);
										stock3.setQuantity(5);
										stocks.persist().using(stock3);

										StockProxy stock4 = stocks.create(StockProxy.class);
										stock4.setMateriel(materiel9);
										stock4.setSupplier(warehouse2);
										stock4.setQuantity(6);
										stocks.persist().using(stock4);
										
										StockProxy stock5 = stocks.create(StockProxy.class);
										stock5.setMateriel(materiel5);
										stock5.setSupplier(warehouse);
										stock5.setQuantity(30);
										stocks.persist().using(stock5);

										StockProxy stock6 = stocks.create(StockProxy.class);
										stock6.setMateriel(materiel4);
										stock6.setSupplier(warehouse2);
										stock6.setQuantity(20);
										stocks.persist().using(stock6);
										
										StockProxy stock7 = stocks.create(StockProxy.class);
										stock7.setMateriel(materiel1);
										stock7.setSupplier(warehouse);
										stock7.setQuantity(120);
										stocks.persist().using(stock7);

										StockProxy stock8 = stocks.create(StockProxy.class);
										stock8.setMateriel(materiel1);
										stock8.setSupplier(warehouse2);
										stock8.setQuantity(10);
										stocks.persist().using(stock8);

										stocks.fire(new Receiver<Void>() {
											@Override
											public void onSuccess(Void response) {
												GWT.log("Material -> Warehouse -> Stock persited");
											}
											@Override
											public void onFailure(ServerFailure error) {
												GWT.log("Fail 3");
											}
										});

									}
								
								});

								SellerRequest sellers = dataStore.sellers();
								final SellerProxy seller = sellers.create(SellerProxy.class);
								seller.setName("Arredi S.p.A.");
								
								final SellerProxy seller2 = sellers.create(SellerProxy.class);
								seller2.setName("Piatti & Bicchieri s.r.l.");
								sellers.persist().using(seller2);

								sellers.persist().using(seller).fire(new Receiver<Void>() {

									@Override
									public void onSuccess(Void response) {
										OfferRequest offers = dataStore.offers();
										OfferProxy offer1 = offers.create(OfferProxy.class);
										offer1.setMateriel(materiel1);
										offer1.setSupplier(seller);
										offer1.setQuantity(5);
										offer1.setPrice(299.99F);
										offers.persist().using(offer1);

										OfferProxy offer2 = offers.create(OfferProxy.class);
										offer2.setMateriel(materiel2);
										offer2.setSupplier(seller);
										offer2.setPrice(15);
										offer2.setQuantity(50);
										offers.persist().using(offer2);
										
										OfferProxy offer3 = offers.create(OfferProxy.class);
										offer3.setMateriel(materiel3);
										offer3.setSupplier(seller);
										offer3.setPrice(2.50F);
										offer3.setQuantity(300);
										offers.persist().using(offer3);
										
										OfferProxy offer4 = offers.create(OfferProxy.class);
										offer4.setMateriel(materiel4);
										offer4.setSupplier(seller);
										offer4.setPrice(35);
										offer4.setQuantity(10);
										offers.persist().using(offer4);
										
										OfferProxy offer5 = offers.create(OfferProxy.class);
										offer5.setMateriel(materiel5);
										offer5.setSupplier(seller);
										offer5.setPrice(20);
										offer5.setQuantity(100);
										offers.persist().using(offer5);
										
										OfferProxy offer6 = offers.create(OfferProxy.class);
										offer6.setMateriel(materiel6);
										offer6.setSupplier(seller2);
										offer6.setPrice(12);
										offer6.setQuantity(300);
										offers.persist().using(offer6);
										
										OfferProxy offer7 = offers.create(OfferProxy.class);
										offer7.setMateriel(materiel7);
										offer7.setSupplier(seller2);
										offer7.setPrice(0.9F);
										offer7.setQuantity(1000);
										offers.persist().using(offer7);
										
										OfferProxy offer8 = offers.create(OfferProxy.class);
										offer8.setMateriel(materiel8);
										offer8.setSupplier(seller2);
										offer8.setPrice(1.5F);
										offer8.setQuantity(1000);
										offers.persist().using(offer8);
										
										OfferProxy offer9 = offers.create(OfferProxy.class);
										offer9.setMateriel(materiel9);
										offer9.setSupplier(seller2);
										offer9.setPrice(1.5F);
										offer9.setQuantity(1000);
										offers.persist().using(offer9);							

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
						jobBoard1.setName("Monster.com");
						jobBoards.persist().using(jobBoard1);
						final JobBoardProxy jobBoard2 = jobBoards.create(JobBoardProxy.class);
						jobBoard2.setName("ACME Recruiting s.r.l.");
						jobBoards.persist().using(jobBoard2);
						jobBoards.fire(new Receiver<Void>() {

							@Override
							public void onSuccess(Void response) {
								WorkerRequest workers = dataStore.workers();
								final WorkerProxy worker1 = workers.create(WorkerProxy.class);
								worker1.setName("Guarino Greco");
								worker1.setResume("Guarino Greco fa la sua prima apparizione nell'aprile del 1951 presso Enna e vive fino al giurassico superiore la propria giovinezza nella Germania occidentale.");
								worker1.setEmail("mario.rossi@email.it");
								worker1.setCandidate(false);
								workers.persist().using(worker1);

								final WorkerProxy worker2 = workers.create(WorkerProxy.class);
								worker2.setName("Lisandro Bergamaschi");
								worker2.setResume("Lisandro Bergamaschi, nato in Turkmenistan approssimativamente nell'inverno del 1920, vive fino al Carnevale del 1913 presso Berlino in Polinesia occidentale la propria infanzia...");
								//worker2.setEmail("l.bergamaschi@gmail.com");
								worker2.setCandidate(false);
								workers.persist().using(worker2);

								final WorkerProxy worker3 = workers.create(WorkerProxy.class);
								worker3.setName("Costante Fiorentini");
								//worker3.setResume("Costante Fiorentini nasce in Principato di Monaco orientale nel giurassico e trascorre una spensierata giovinezza fino al San Valentino del 1979 in Citta' del Vaticano....");
								//worker3.setEmail("costante.fio82@yahoo.it");
								worker3.setCandidate(false);
								workers.persist().using(worker3);

								final WorkerProxy worker4 = workers.create(WorkerProxy.class);
								worker4.setName("Settimo Boni");
								//worker4.setResume("Settimo Boni si dice sia nato presso Treviso tra maggio e Natale del 1995 e trascorre a Timbuctu la propria infanzia fino all'agosto del 1914....");
								//worker4.setEmail("7boni@hotmail.it");
								worker4.setCandidate(false);
								workers.persist().using(worker4);

								final WorkerProxy worker5 = workers.create(WorkerProxy.class);
								worker5.setName("Geraldina Giordano");
								//worker5.setResume("Geraldina Giordano, nata nell'estate del 1980 a Teramo in Lichtenstein, trascorre una trista giovinezza a Timbuctu fino all'agosto del 1941....");
								//worker5.setEmail("giordano.g@gmail.com");
								worker5.setCandidate(true);
								workers.persist().using(worker5);

								final WorkerProxy worker6 = workers.create(WorkerProxy.class);
								worker6.setName("Ada Milano");
								//worker6.setResume("Ada Milano, nata nella Turchia occidentale nel 1920, trascorre la propria giovinezza fino al maggio del 1951 in Cile orientale....");
								//worker6.setEmail("miada@yahoo.com");
								worker6.setCandidate(true);
								workers.persist().using(worker6);

								final WorkerProxy worker7 = workers.create(WorkerProxy.class);
								worker7.setName("Teresa Toscano");
								//worker7.setResume("Teresa Toscano, nata in Thailandia tra gennaio e aprile del 1940, trascorre una giovinezza mesta fino ad una data ignota presso Helsinki in Macedonia settentrionale....");
								//worker7.setEmail("teresa.toscano7@outlook.com");
								worker7.setCandidate(true);
								workers.persist().using(worker7);

								final WorkerProxy worker8 = workers.create(WorkerProxy.class);
								worker8.setName("Federica Rizzo");
								//worker8.setResume("Federica Rizzo, si dice sia nato il 29 giugno 1959 a Berlino in Turkmenistan e vive presso Cinisello Balsamo fino alla primavera del 1926 una adolescenza felice. In data da stabilirsi lascia i luoghi natii per approdare a Carbonera, dove si converte a seguito di un evento folgorante alla politica maltese. Conquista il diploma di tecnico industriale e ottiene finalmente la laurea in filomania. Il dramma che ricordiamo tutti con sagacia (la improvvisa perdita del suo studiato aplomb) lo costringe a rivolgere la propria esistenza all'occulto e alla onnigogia. Assai fortunato nelle questioni di cuore, nel 1926 conosce l'amore della sua vita, con la quale si ritira presso Gubbio (Macedonia), ma nell'ottobre del 1913 e' stato tragicamente rinvenuto il suo corpo inanimato al largo dell'Argentina settentrionale e si trova ora nel campo santo di Sant'Antonio della sua amata Venezia. A tutt'oggi al di la' di ogni altra considerazione con sommo rispetto lo portiamo nei nostri cuori come modello raggiante di castita'.");
								//worker8.setEmail("rizzo.f@yahoo.com");
								worker8.setCandidate(false);
								workers.persist().using(worker8);

								final WorkerProxy worker9 = workers.create(WorkerProxy.class);
								worker9.setName("Vito Pisano");
								//worker9.setResume("Vito Pisano nasce in data ignota presso Oriago e spende fino al pleistocene nel Ciad meridionale la propria infanzia. Il 2 luglio 1989 abbandona il tetto materno per mettere igni ferrique Colonnata, dove dapprima, per diletto, si iscrive alla facolta di sociomania, ma dopo la perdita dei capelli decide di intraprendere l'ignobile gesto di partecipare al festival canoro di Calcutta. Ma quando, a seguito di un interminabile periodo trascorso a giocare una schedina con tutti 2 in Micronesia, decide di dedicarsi all'arte di cucinare budella il suo sogno di compiere l'ingiustificabile cimento di spacciare automobili a pedali viene infine soddisfatto. Conosce nel 1994 la donna della sua vita, che sposera' tra Ferragosto del 1951 e Ferragosto del 1903 e che lo introduce all'arte del safari. Solo dopo il vaiolo che lo ha afflitto per tutta la vita il 5 marzo 1937 decide di dedicarsi alla caccia di frodo di cerbiatti e pecore nane, mentre con devozione si iscrive alla facolta di idrografia, e ancora oggi non trova pace e non ha una residenza fissa.");
								//worker9.setEmail("pisanovito@email.it");
								worker9.setCandidate(false);
								workers.persist().using(worker9);

								final WorkerProxy worker10 = workers.create(WorkerProxy.class);
								worker10.setName("Dorotea Marino");
								//worker10.setResume("Dorotea Marino si dice sia nata nella Thailandia meridionale all'incirca il 24 aprile 1965 e spende fino al 1940 in Dalmazia la propria giovinezza. Si mangia i calli di ambo i piedi per conquistare Enna, dove commette l'esecrabile atto di trasformarsi in un razzo missile. Studia scienze della comunicazione per l'autunno e poi decide di entrare nel mondo della musica provenzale e della videofobia solo dopo l'acerrimo duello tra Papa Wojtila e Corrado Augias, mentre si iscrive ad un corso di lettere antiche. Dopo un intenso conflitto interiore si scopre uomo a soli 45 anni nel 1931 e fugge da Velletri per ritirarsi tra le nevi del Rwanda nei pressi di Teheran. Non e' che tra Natale del 1984 e San Valentino del 1951 che ha modo di dedicarsi all'arte della bracconeria e realizzare finalmente il proprio sogno di andare a vivere a Zacinto, ma nella notte dei tempi e' stato tragicamente ritrovato il suo corpo gia' parzialmente decomposto nei prati dell'Argentina e riposa ora nella cappella di San Matteo della sua Roncobilaccio. Ancora oggi volentieri lo esaltiamo come esempio di cultura parlamentare.");
								//worker10.setEmail("d.marino@gmail.com");
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
												qualification5.setName("Lavapiatti");
												qualifications.persist().using(qualification5);

												qualifications.fire(new Receiver<Void>() {

													@Override
													public void onSuccess(Void response) {
														PieceworkRequest pieceworks = dataStore.pieceworks();

														final PieceworkProxy piecework1 = pieceworks.create(PieceworkProxy.class);
														piecework1.setWorker(worker1);
														piecework1.setQualification(qualification1);
														piecework1.setPay(50);
														piecework1.setFreelance(true);
														pieceworks.persist().using(piecework1);

														final PieceworkProxy piecework2 = pieceworks.create(PieceworkProxy.class);
														piecework2.setWorker(worker7);
														piecework2.setQualification(qualification2);
														piecework2.setPay(1000);
														piecework2.setFreelance(true);
														pieceworks.persist().using(piecework2);

														PieceworkProxy piecework3 = pieceworks.create(PieceworkProxy.class);
														piecework3.setWorker(worker9);
														piecework3.setQualification(qualification3);
														piecework3.setPay(10);
														piecework3.setFreelance(false);
														pieceworks.persist().using(piecework3);

														PieceworkProxy piecework4 = pieceworks.create(PieceworkProxy.class);
														piecework4.setWorker(worker10);
														piecework4.setQualification(qualification4);
														piecework4.setPay(10);
														piecework4.setFreelance(true);
														pieceworks.persist().using(piecework4);

														final PieceworkProxy piecework5 = pieceworks.create(PieceworkProxy.class);
														piecework5.setWorker(worker1);
														piecework5.setQualification(qualification2);
														piecework5.setPay(1500);
														piecework5.setFreelance(false);
														pieceworks.persist().using(piecework5);


														PieceworkProxy piecework6 = pieceworks.create(PieceworkProxy.class);
														piecework6.setWorker(worker5);
														piecework6.setQualification(qualification4);
														piecework6.setPay(10);
														piecework6.setFreelance(true);
														pieceworks.persist().using(piecework6);

														PieceworkProxy piecework7 = pieceworks.create(PieceworkProxy.class);
														piecework7.setWorker(worker7);
														piecework7.setQualification(qualification5);
														piecework7.setPay(10);
														piecework7.setFreelance(false);
														pieceworks.persist().using(piecework7);

														PieceworkProxy piecework8 = pieceworks.create(PieceworkProxy.class);
														piecework8.setWorker(worker4);
														piecework8.setQualification(qualification1);
														piecework8.setPay(10);
														piecework8.setFreelance(true);
														pieceworks.persist().using(piecework8);

														PieceworkProxy piecework9 = pieceworks.create(PieceworkProxy.class);
														piecework9.setWorker(worker2);
														piecework9.setQualification(qualification1);
														piecework9.setPay(10);
														piecework9.setFreelance(false);
														pieceworks.persist().using(piecework9);

														PieceworkProxy piecework10 = pieceworks.create(PieceworkProxy.class);
														piecework10.setWorker(worker8);
														piecework10.setQualification(qualification4);
														piecework10.setPay(10);
														piecework10.setFreelance(true);
														pieceworks.persist().using(piecework10);

														PieceworkProxy piecework11 = pieceworks.create(PieceworkProxy.class);
														piecework11.setWorker(worker8);
														piecework11.setQualification(qualification1);
														piecework11.setPay(10);
														piecework11.setFreelance(false);
														pieceworks.persist().using(piecework11);

														PieceworkProxy piecework12 = pieceworks.create(PieceworkProxy.class);
														piecework12.setWorker(worker2);
														piecework12.setQualification(qualification5);
														piecework12.setPay(10);
														piecework12.setFreelance(false);
														pieceworks.persist().using(piecework12);

														PieceworkProxy piecework13 = pieceworks.create(PieceworkProxy.class);
														piecework13.setWorker(worker10);
														piecework13.setQualification(qualification2);
														piecework13.setPay(10);
														piecework13.setFreelance(true);
														pieceworks.persist().using(piecework13);

														PieceworkProxy piecework14 = pieceworks.create(PieceworkProxy.class);
														piecework14.setWorker(worker6);
														piecework14.setQualification(qualification1);
														piecework14.setPay(10);
														piecework14.setFreelance(true);
														pieceworks.persist().using(piecework14);

														PieceworkProxy piecework15 = pieceworks.create(PieceworkProxy.class);
														piecework15.setWorker(worker5);
														piecework15.setQualification(qualification5);
														piecework15.setPay(10);
														piecework15.setFreelance(false);
														pieceworks.persist().using(piecework1);

														PieceworkProxy piecework16 = pieceworks.create(PieceworkProxy.class);
														piecework16.setWorker(worker5);
														piecework16.setQualification(qualification3);
														piecework16.setPay(10);
														piecework16.setFreelance(false);
														pieceworks.persist().using(piecework16);

														pieceworks.fire(new Receiver<Void>() {

															@Override
															public void onSuccess(Void response) {
																
																ContractRequest contracts = dataStore.contracts();
																/*if(event1!=null){
																	FreelanceContractProxy contract1 = contracts.create(FreelanceContractProxy.class);
																	contract1.setPiecework(piecework1);
																	contract1.setEvent(event1);
																	contracts.persist().using(contract1);
																}*/
																
																EmploymentContractProxy contract2 = contracts.create(EmploymentContractProxy.class);
																contract2.setPiecework(piecework5);
																contract2.setStartDate(DATE_F.parse("17/07/2012"));
																contracts.persist().using(contract2);
																
																contracts.fire(new Receiver<Void>(){
																	public void onSuccess(Void response) {
																		GWT.log("Workers & JobBoards & Pieceworks & Qualifications & Contracts persisted");
																	};
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
