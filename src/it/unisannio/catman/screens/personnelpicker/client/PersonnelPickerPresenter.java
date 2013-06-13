package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.PositionRequest;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.View;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Presenter;

public abstract class PersonnelPickerPresenter extends LoadingScreenActivity<PositionRequest, PositionProxy, View> implements Presenter {

	public PersonnelPickerPresenter() {
		super(getDataStore().positions(),"qualification");
	}

	@Override
	protected abstract View onViewSetup();

	@Override
	protected void onLoad(PositionProxy object) {
		View view = getView();
		view.setPresenter(this);
		
		view.setPositionProxy(object);
		
		getDataStore().workers().findFillersInPosition(object).fire(new Receiver<List<WorkerProxy>>() {

			@Override
			public void onSuccess(List<WorkerProxy> response) {
				getView().setSelectedWorkers(response);
			}
		});
		
	}

	@Override
	public void goToWorkerScreen(WorkerProxy workerProxy) {
		String id = App.getInstance().getDataStore().getHistoryToken(workerProxy.stableId());
		goTo(new Intent("worker").withParams(id));
	}
}