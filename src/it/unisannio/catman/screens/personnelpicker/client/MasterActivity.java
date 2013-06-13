package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Master;
import it.unisannio.catman.screens.personnelpicker.client.queries.WorkersByPositionQuery;


public class MasterActivity extends PersonnelPickerPresenter implements PersonnelPicker.Master{

	@Override
	protected PersonnelPicker.View onViewSetup() {
		return new MasterView();
	}
	
	@Override
	protected void onLoad(PositionProxy object) {
		super.onLoad(object);
		
		((Master.View)getView()).setWorkersQuery(new WorkersByPositionQuery(object));
	}
	
}
