package it.unisannio.catman.screens.candidates.client;

import it.unisannio.catman.domain.humanresources.client.CandidatesSource;
import it.unisannio.catman.screens.workers.client.PersonnelSourceDetailView;

public class DetailView extends PersonnelSourceDetailView implements Candidates.Detail.View{
	interface Presenter{}
	
	public DetailView() {
		super(new CandidatesSource());
	}

}
