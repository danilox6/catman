package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Master;

public class MasterActivity extends PersonnelPickerPresenter implements PersonnelPicker.Master{

	@Override
	protected PersonnelPicker.View onViewSetup() {
		return new MasterView();
	}
	
	@Override
	protected void onLoad(PositionProxy object) {
		super.onLoad(object);
		
		getDataStore().workers().findByQualification(object.getQualification()).fire(new Receiver<List<WorkerProxy>>() {
			@Override
			public void onSuccess(List<WorkerProxy> response) {
				((Master.View)getView()).setWorkersByPosition(response);
			}
		});
		
		getDataStore().jobBoards().findByQualification(object.getQualification()).with("workers").fire(new Receiver<List<JobBoardProxy>>() {
			@Override
			public void onSuccess(List<JobBoardProxy> response) {
				((Master.View)getView()).setJobBoarsByPosition(response);
			}
		});
	}
	
}
