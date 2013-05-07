package it.unisannio.catman.screens.candidates.client;

import it.unisannio.catman.domain.humanresources.client.CandidatesSource;
import it.unisannio.catman.screens.workers.client.PersonnelSourceMasterView;

public class MasterView extends PersonnelSourceMasterView implements Candidates.Master.View{
	interface Presenter{}
	
	public MasterView() {
		super(new CandidatesSource());
	}

}
