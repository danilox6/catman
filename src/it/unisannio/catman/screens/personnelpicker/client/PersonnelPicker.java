package it.unisannio.catman.screens.personnelpicker.client;

import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.user.client.ui.IsWidget;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.Intent;
import it.unisannio.catman.common.client.Screen;
import it.unisannio.catman.common.client.Screen.HasMaster;
import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.planning.client.PositionProxy;


public class PersonnelPicker extends Screen implements HasMaster, HasDetail{
	public static interface View extends IsWidget{
		void setPositionProxy(PositionProxy positionProxy);
		void setSelectedWorkers(List<WorkerProxy> selectedWorkers);
		void setPresenter(Presenter presenter);
	}
	
	public static interface Presenter{
		void goToWorkerScreen(WorkerProxy w);
	}
	
	public static interface Master extends Presenter{
		interface View extends PersonnelPicker.View{
			void setWorkersByPosition(List<WorkerProxy> list);
			void setJobBoarsByPosition(List<JobBoardProxy> jobBoards);
		}
	}
	
	public static interface Detail extends Presenter {
		interface View extends PersonnelPicker.View{
			void setWorkersSources(List<WorkersSource> workersSources);
		}
	}


	protected PersonnelPicker() {
		super("Personnel Picker", "personnel-picker", Icon.CHEF);
	}


	@Override
	public Activity getDetail(Intent i) {
		return new DetailActivity();
	}


	@Override
	public Activity getMaster(Intent i) {
		return new MasterActivity();
	}

}
