package it.unisannio.catman.screens.workers.client;

import it.unisannio.catman.domain.humanresources.client.WorkersSource;

public class MasterView extends PersonnelSourceMasterView implements Workers.Master.View{
	interface Presenter{}
	
	public MasterView() {
		super(new WorkersSource());
	}

}
