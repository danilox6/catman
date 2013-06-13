package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Detail;


public class DetailActivity extends PersonnelPickerPresenter implements Detail{

	@Override
	protected View onViewSetup() {
		return new DetailView();
	}
	
	@Override
	protected void onLoad(PositionProxy object) {
		super.onLoad(object);
		
		WorkersSource.findWorkersSources(new Receiver<List<WorkersSource>>() {

			@Override
			public void onSuccess(List<WorkersSource> workersSources) {
				((Detail.View) getView()).setWorkersSources(workersSources);
			}
		},object.getQualification());
	}
	
}
