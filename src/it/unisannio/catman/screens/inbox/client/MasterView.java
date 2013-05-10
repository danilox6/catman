package it.unisannio.catman.screens.inbox.client;

import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataProviderSelectionSyncronizer;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.SelectAllHandler;
import it.unisannio.catman.domain.documents.client.DossierProxy;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;
import it.unisannio.catman.domain.workflow.client.CustomerRequest;
import it.unisannio.catman.screens.inbox.client.widget.DossierCellAdapter;
import it.unisannio.catman.screens.inbox.client.widget.MasterHeadWidget;
import it.unisannio.catman.screens.inbox.client.widget.SelectionHandlerBottomBar;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class MasterView extends AbstractMasterView implements Inbox.Master.View {
	interface Presenter {}

	public MasterView() {

		northPanel.add(new MasterHeadWidget("Title"));

		DossierCellAdapter cellAdapter = new DossierCellAdapter();
		CellList<DossierProxy> cellList = new CellList<DossierProxy>(new MasterCell<DossierProxy>(cellAdapter));

		MultiSelectionModel<DossierProxy> selectionModel = new MultiSelectionModel<DossierProxy>();
		cellList.setSelectionModel(selectionModel, DefaultSelectionEventManager.<DossierProxy>createCheckboxManager());

		cellAdapter.setSelectionModel(selectionModel);

		ListDataProvider<DossierProxy> dataProvider = new ListDataProvider<DossierProxy>();
		dataProvider.addDataDisplay(cellList);
		DataProviderSelectionSyncronizer.<DossierProxy>sync(selectionModel, dataProvider);

		List<DossierProxy> values = dataProvider.getList(); //Da javadoc "Get the list that backs this model. Changes to the list will be reflected in the model."
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());

		cellList.setRowCount(values.size(), true);

		centerScrollPanel.add(cellList);

		southPanel.add(new SelectionHandlerBottomBar(new SelectAllHandler<DossierProxy>(selectionModel, dataProvider)));

		try {
			final DataStore dataStore = App.getInstance().getDataStore();

			CustomerRequest customers = dataStore.customers();
			CustomerProxy customer = customers.create(CustomerProxy.class);

			customer.setName("Mario");
			customers.persist().using(customer).fire(new Receiver<Void>() {

				@Override
				public void onSuccess(Void response) {
					GWT.log("Created!");

					dataStore.customers().findByName("Mario").fire(new Receiver<CustomerProxy>() {

						@Override
						public void onSuccess(CustomerProxy response) {
							GWT.log("Success!");
							//GWT.log("Hello " + response.getName());
						}

						@Override
						public void onFailure(ServerFailure error) {
							//super.onFailure(error);
							GWT.log("Server fail: " + error.getMessage() + error.getStackTraceString());
						}

					});

				}

			});

		} catch (Exception e) {
			GWT.log("fail", e);
		}
	}


	//FIXME Solo per i test
	private static class DossierProxyMock implements DossierProxy {

	}

}