package it.unisannio.catman.screens.jobboards.client;

import it.unisannio.catman.domain.humanresources.client.JobBoardSource;
import it.unisannio.catman.screens.workers.client.PersonnelSourceDetailView;

public class DetailView extends PersonnelSourceDetailView implements JobBoards.Detail.View{
	interface Presenter{}
	
	public DetailView() {
		super(new JobBoardSource("Board 1"));
	}

}
