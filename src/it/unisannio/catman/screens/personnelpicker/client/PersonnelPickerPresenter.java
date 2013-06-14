package it.unisannio.catman.screens.personnelpicker.client;

import java.util.ArrayList;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.LoadingScreenActivity;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.PositionRequest;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.View;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Presenter;

public abstract class PersonnelPickerPresenter extends LoadingScreenActivity<PositionRequest, PositionProxy, View> implements Presenter {

	public PersonnelPickerPresenter() {
		super(getDataStore().positions(),"qualification");
	}
	
	private PositionProxy position;

	@Override
	protected abstract View onViewSetup();

	@Override
	protected void onLoad(PositionProxy object) {
		View view = getView();
		view.setPresenter(this);
		this.position = object;
		view.setPositionProxy(position);
		
		getDataStore().positions().getFillers().using(position).with("piecework","piecework.worker").fire(new Receiver<List<ContractProxy>>() {

			@Override
			public void onSuccess(List<ContractProxy> fillers) {
				List<WorkerProxy> workers = new ArrayList<WorkerProxy>();
				for(ContractProxy c : fillers)
					workers.add(c.getPiecework().getWorker());
				getView().setSelectedContracts(fillers);
			}
		});
		
	}

	@Override
	public void goToWorkerScreen(WorkerProxy workerProxy) {
		String wId = App.getInstance().getDataStore().getHistoryToken(workerProxy.stableId());
		String pId = App.getInstance().getDataStore().getHistoryToken(position.stableId());
		goTo(new Intent("worker").withParams(wId,pId));
	}
}