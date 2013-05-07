package it.unisannio.catman.screens.jobboards.client;

import it.unisannio.catman.domain.humanresources.client.JobBoardSource;
import it.unisannio.catman.screens.workers.client.PersonnelSourceMasterView;

public class MasterView extends PersonnelSourceMasterView implements JobBoards.Master.View{
	interface Presenter{}
	
	public MasterView() {
		super(new JobBoardSource("Board 1"));
	}

}
